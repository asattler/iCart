package de.bht.mme2.icart.ocp.recipe;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.bht.mme2.icart.ocp.parsers.TestParser;
import de.bht.mme2.icart.ocp.user.User;
import de.bht.mme2.icart.ocp.utils.Status;

public class ListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * based on how to by edwin
	 * (http://edwin.baculsoft.com/2011/11/how-to-create
	 * -a-simple-servlet-to-handle-json-requests/)
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		User user = (User) request.getSession().getAttribute("user");
		Gson gson = new Gson();

		if (user == null) {

		} else {
			Set<Recipe> recipes = user.getRecipes();

			try {

				response.getOutputStream().print(gson.toJson(recipes));
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
		TestParser.mytest();
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
