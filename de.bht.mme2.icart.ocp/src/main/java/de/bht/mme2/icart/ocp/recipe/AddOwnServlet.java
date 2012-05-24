package de.bht.mme2.icart.ocp.recipe;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.bht.mme2.icart.ocp.parsers.ChefkochParser;
import de.bht.mme2.icart.ocp.parsers.IParser;
import de.bht.mme2.icart.ocp.user.User;
import de.bht.mme2.icart.ocp.user.UserDao;
import de.bht.mme2.icart.ocp.utils.Status;
import de.bht.mme2.icart.ocp.utils.UrlDTO;

public class AddOwnServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();
	private RecipeDao recipeDao = new RecipeDao();
	
	
	/*
	 * based on how to by edwin (http://edwin.baculsoft.com/2011/11/how-to-create-a-simple-servlet-to-handle-json-requests/)
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		User user = (User) request.getSession().getAttribute("user");
		user = userDao.findById(user.getId());
		Gson gson = new Gson();

		try {
			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = request.getReader().readLine()) != null) {
				sb.append(s);
			}
			RecipeDTO recipeDTO = (RecipeDTO) gson.fromJson(sb.toString(), RecipeDTO.class);

			Status status = new Status();

			if(recipeDTO.getName() != null){
				Recipe recipe = new Recipe();
				recipe.setName(recipeDTO.getName());
				recipe.setAmountPortion(recipeDTO.getAmountPortion());
				recipe.setIngredients(recipeDTO.getIngredients());
				HashSet<User> users = new HashSet<User>();
				users.add(user);
				recipe.setUsers(users);
				recipeDao.save(recipe);
				status.setSuccess(true);
			}else{
				status.setSuccess(false);
				status.setDescription("Parsen des DTOs hat nicht funktioniert!");
			}
			
			
			response.getOutputStream().print(gson.toJson(status));
			response.getOutputStream().flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			Status status = new Status();
			status.setSuccess(false);
			status.setDescription(ex.getMessage());
			response.getOutputStream().print(gson.toJson(status));
			response.getOutputStream().flush();
		}		
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
