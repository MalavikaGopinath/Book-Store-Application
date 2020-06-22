package com.demo.onlinebookstore.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * This LogoutController is just used to print the successfully logged out
 * message to the user in the login.jsp view and the session will be invalidated
 * here.
 *
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This doGet() of LogoutController invalidates the current session and the
	 * correct logout message will the shown in the login.jsp view.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting the current session
		HttpSession session = request.getSession(false);

		// Checking whether the session is null or not
		if (session != null) {

			// Invalidating the session
			session.invalidate();

			// Setting the logout message as additional parameter for the RequestDispatcher
			request.setAttribute("logMessage", "Logged out successfully");

			// Dispatching the request to login.jsp view
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

}
