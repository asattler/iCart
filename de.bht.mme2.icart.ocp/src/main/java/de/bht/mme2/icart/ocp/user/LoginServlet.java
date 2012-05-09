package de.bht.mme2.icart.ocp.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.bht.mme2.icart.ocp.parsers.TestParser;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();

	/*
	 * based on how to by edwin (http://edwin.baculsoft.com/2011/11/how-to-create-a-simple-servlet-to-handle-json-requests/)
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Gson gson = new Gson();

		try {
			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = request.getReader().readLine()) != null) {
				sb.append(s);
			}
			User user = (User) gson.fromJson(sb.toString(), User.class);

			Status status = new Status();
			if (user.getEmail() != null) {
				User dbUser = userDao.findByEmail(user.getEmail());
				if (dbUser != null) {
					if (dbUser.getPassword().equals(user.getPassword())) {
						status.setSuccess(true);
						status.setDescription("success");
						request.getSession().setAttribute("user", dbUser);
					} else {
						status.setSuccess(false);
						status.setDescription("User or Password incorrect.");
					}
				} else {
					status.setSuccess(false);
					status.setDescription("User or Password incorrect.");
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
