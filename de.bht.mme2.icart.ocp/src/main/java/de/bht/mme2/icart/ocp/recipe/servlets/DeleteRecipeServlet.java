package de.bht.mme2.icart.ocp.recipe.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.bht.mme2.icart.ocp.recipe.DeleteRecipeDTO;
import de.bht.mme2.icart.ocp.recipe.RecipeDao;
import de.bht.mme2.icart.ocp.user.User;
import de.bht.mme2.icart.ocp.user.UserDao;
import de.bht.mme2.icart.ocp.utils.Status;

public class DeleteRecipeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2882861191127400172L;
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
		Status status = new Status();
		
		if(user != null){
		try {
			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = request.getReader().readLine()) != null) {
				sb.append(s);
			}
			System.out.println(sb.toString());
			DeleteRecipeDTO deleteRecipeDTO = (DeleteRecipeDTO) gson.fromJson(sb.toString(), DeleteRecipeDTO.class);
			recipeDao.deleteRecipeById(deleteRecipeDTO.getId());
			status.setSuccess(true);
			
			
		
		}catch (Exception e){
			e.printStackTrace();
			status.setSuccess(false);
			status.setDescription(e.getMessage());
		}
		} else {
			status.setSuccess(false);
			status.setDescription("Not loggedin!");
		}
		
		try {
			response.getOutputStream().print(gson.toJson(status));
			response.getOutputStream().flush();
		} catch (Exception ex) {
			ex.printStackTrace();
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
