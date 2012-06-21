package de.bht.mme2.icart.ocp.recipe.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.bht.mme2.icart.ocp.recipe.Recipe;
import de.bht.mme2.icart.ocp.recipe.RecipeDTO;
import de.bht.mme2.icart.ocp.recipe.RecipeDao;
import de.bht.mme2.icart.ocp.user.User;
import de.bht.mme2.icart.ocp.user.UserDao;
import de.bht.mme2.icart.ocp.utils.Status;

public class ImportRecipesServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8733424498901681101L;
	
	private UserDao userDao = new UserDao();
	private RecipeDao recipeDao = new RecipeDao();
	
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("application/json");
		User user = (User) request.getSession().getAttribute("user");
		user = userDao.findById(user.getId());
		
		Status status = new Status();
		Gson gson = new Gson();
		if(user == null){
			status.setSuccess(false);
			status.setDescription("Unknown User");
		}else {
			

			try {
				StringBuilder sb = new StringBuilder();
				String s;
				while ((s = request.getReader().readLine()) != null) {
					sb.append(s);
				}
				
				RecipeDTO[] importRecipesDTO = (RecipeDTO[]) gson.fromJson(sb.toString(), RecipeDTO[].class);
				
				if(importRecipesDTO.length == 0){
					status.setSuccess(false);
					status.setDescription("No Recipes found in file");
				}
				else {
					int num = 0;
					for(int i=0;i < importRecipesDTO.length;i++){
						RecipeDTO r = importRecipesDTO[i];
						Recipe recipe = null;
						List<User> users;
						if(r.getId() != null){
							recipe = recipeDao.findById(r.getId());
						}
						
						if(recipe == null){
							recipe = new Recipe();
							recipe.setName(r.getName());
							recipe.setAmountPortion(r.getAmountPortion());
							recipe.setIngredients(r.getIngredients());
							users = new ArrayList<User>();
							users.add(user);
							recipe.setUsers(users);
						} else {
							users = recipe.getUsers();
							if(!users.contains(user))
							{
								users.add(user);
								recipe.setUsers(users);
							}
						}
						recipeDao.save(recipe);
						num++;
					}
					status.setSuccess(true);
					status.setDescription(num+" Recipes imported");
				}
			} catch(Exception ex){
				ex.printStackTrace();
				status.setSuccess(false);
				status.setDescription(ex.getMessage());
			}
			
			try {
				response.getOutputStream().print(gson.toJson(status));
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
