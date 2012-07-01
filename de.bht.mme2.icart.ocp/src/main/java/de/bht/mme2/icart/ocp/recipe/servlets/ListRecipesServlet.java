package de.bht.mme2.icart.ocp.recipe.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.bht.mme2.icart.ocp.recipe.Recipe;
import de.bht.mme2.icart.ocp.recipe.RecipeDTO;
import de.bht.mme2.icart.ocp.user.User;
import de.bht.mme2.icart.ocp.user.UserDao;
import de.bht.mme2.icart.ocp.utils.Status;

public class ListRecipesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5600493930214681697L;

	private UserDao userDao = new UserDao();
	

	/*
	 * based on how to by edwin
	 * (http://edwin.baculsoft.com/2011/11/how-to-create
	 * -a-simple-servlet-to-handle-json-requests/)
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		User user = (User) request.getSession().getAttribute("user");
		user = userDao.findById(user.getId());
		Gson gson = new Gson();
		Status status = new Status();
		if (user == null) {
			status.setSuccess(false);
			status.setDescription("User not found!");
			response.getOutputStream().print(gson.toJson(status));
			response.getOutputStream().flush();
		} else {
			List<Recipe> recipes = user.getRecipes();
			List<RecipeDTO> recipesDTO = new ArrayList<RecipeDTO>();
			Type type = new TypeToken<List<RecipeDTO>>(){}.getType();
			for (Recipe myRecipe : recipes){
				RecipeDTO rDTO = new RecipeDTO();
				rDTO.setName(myRecipe.getName());
				rDTO.setId(myRecipe.getId());
				rDTO.setIngredients(myRecipe.getIngredients());
				rDTO.setAmountPortion(myRecipe.getAmountPortion());
				recipesDTO.add(rDTO);
			}
			try {
				System.out.println("Recipes are pushed out.");
				response.getOutputStream().print(gson.toJson(recipesDTO, type));
				response.getOutputStream().flush();
			} catch (Exception ex) {
				ex.printStackTrace();
				status.setSuccess(false);
				status.setDescription(ex.getMessage());
				response.getOutputStream().print(gson.toJson(status));
				response.getOutputStream().flush();
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
