package de.bht.mme2.icart.ocp.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.bht.mme2.icart.ocp.utils.Status;

public class LogoutServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3582768187182604051L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		
		Gson gson = new Gson();
		
		request.getSession().invalidate();
		
		Status status = new Status();
		status.setSuccess(true);
		status.setDescription("Logout successful.");
		
		response.getOutputStream().print(gson.toJson(status));
		response.getOutputStream().flush();
	}
	
}
