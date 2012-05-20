package de.bht.mme2.icart.ocp.recipe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.bht.mme2.icart.ocp.parsers.ChefkochParser;
import de.bht.mme2.icart.ocp.parsers.IParser;
import de.bht.mme2.icart.ocp.user.User;
import de.bht.mme2.icart.ocp.utils.Status;
import de.bht.mme2.icart.ocp.utils.UrlDTO;

public class AddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RecipeDao recipeDao = new RecipeDao();
	
	private IParser parser;

	/*
	 * based on how to by edwin (http://edwin.baculsoft.com/2011/11/how-to-create-a-simple-servlet-to-handle-json-requests/)
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		User user = (User) request.getSession().getAttribute("user");
		Gson gson = new Gson();

		try {
			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = request.getReader().readLine()) != null) {
				sb.append(s);
			}
			UrlDTO url = (UrlDTO) gson.fromJson(sb.toString(), UrlDTO.class);

			Status status = new Status();
			if (url.getUrl() != null) {
				Recipe dbRecipe = recipeDao.findByURL(url.getUrl());
				if (dbRecipe != null) {
					dbRecipe.getUsers().add(user);
					status.setSuccess(true);
					status.setDescription("Recipe already in DB.");
				} else {
					if(user.getEmail().contains("chefkoch.de")){
						parser = new ChefkochParser();	
					}
					
					if(parser != null){
						Recipe recipe = parser.parseFromURL(url.getUrl(), user);
						recipeDao.save(recipe);
						status.setSuccess(true);
						status.setDescription("Recipe added to DB.");
					} else{
						status.setSuccess(false);
						status.setDescription("Unknown Source.");
					}
					
				}
			} else {
				status.setSuccess(false);
				status.setDescription("error");
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
