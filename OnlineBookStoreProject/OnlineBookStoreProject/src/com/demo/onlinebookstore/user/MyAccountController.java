package com.demo.onlinebookstore.user;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.onlinebookstore.OnlineBookStoreBean;
import com.demo.onlinebookstore.buyandrent.CartDAO;

/**
 * This controller is used for getting the user details from the table and
 * passing it to the myAccount.jsp view
 */
@WebServlet("/MyAccountController")
public class MyAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This doGet() of MyAccountController is used for getting the user details from
	 * the table and passing it to the myAccount.jsp view
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creating the object of OnlineBookStoreBean
		OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

		// Get parameters from session object.
		HttpSession session = request.getSession(false);

		// Getting the user name from the session
		String userName = (String) session.getAttribute("userName");

		// setting the user name to the OnlineBookStoreBean
		onlineBookStoreBean.setUserName(userName);

		// Calling the getUserDetails() of UserDAO
		UserDAO.getUserDetails(onlineBookStoreBean);

		// Storing the result set of user
		ResultSet userrs = onlineBookStoreBean.getUserrs();

		// setting the hello message for the user
		session.setAttribute("userrs", userrs);

		// Calling the getRentList() of CartDAO
		CartDAO.getRentList(onlineBookStoreBean);

		// Storing the result set of rent table
		ResultSet rentrs = onlineBookStoreBean.getRentrs();

		// Setting the result set of rent table to the session
		session.setAttribute("rentrs", rentrs);

		// Passing the control to the myAccount.jsp view
		request.getRequestDispatcher("/myAccount.jsp").forward(request, response);

	}
}
