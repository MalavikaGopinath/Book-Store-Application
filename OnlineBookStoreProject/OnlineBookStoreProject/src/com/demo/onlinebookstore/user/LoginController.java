package com.demo.onlinebookstore.user;

import java.io.IOException;

import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.onlinebookstore.BookDAO;
import com.demo.onlinebookstore.OnlineBookStoreBean;

/**
 * 
 * This controller is user for checking the user validations. It also retrieve
 * the books available in the table and is dispatched to the home page.
 *
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This doGet() of LoginController is used to request the database to retrieve
	 * the values in the book table and the list will be given back to the user in
	 * the home.jsp view.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creating the object of the OnlineBookStoreBean
		OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

		// Calling the bookList() of BookDAO
		BookDAO.bookList(onlineBookStoreBean);

		// Retrieving the result set value of book table using its getter method
		ResultSet bookrs = onlineBookStoreBean.getBookrs();

		// Setting the result set value as additional vales to be passed as response
		request.setAttribute("resultset", bookrs);

		// Dispatching the request to home.jsp view
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * This doPost() of LoginController check whether it username and password
	 * entered by the user are valid. If its a valid user the controll will pass to
	 * the home.jsp view else an error message is shown in the login.jsp page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Getting the user name and password values from the login and storing it in
		 * userName and password.
		 */
		String userName = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();

		// Creating the object of OnlineBookStoreBean
		OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

		// Setting the values of user name and password into its respective setter
		// methods
		onlineBookStoreBean.setUserName(userName);
		onlineBookStoreBean.setPassword(password);

		/*
		 * Calling the authenticateUser() of LoginDAO by passing onlineBookStoreBean as
		 * argument to check if the user has a registered if the value returned is
		 * success then the control will go to home.jsp view.
		 */
		String userValidate = LoginDAO.authenticateUser(onlineBookStoreBean);

		// For storing the hello message to the user once logged in
		String user = "Hello " + userName;

		// checks if the returned value of authenticateUser() gives a valid user
		if (userValidate.equals("SUCCESS")) {

			// Creating a session for the current user
			HttpSession session = request.getSession();

			// Setting the user name and password to the current session
			session.setAttribute("userName", userName);
			session.setAttribute("password", password);

			// calling the bookList() of BookDAO by passing onlineBookStoreBean as argument
			BookDAO.bookList(onlineBookStoreBean);
			
			// Retrieving the result set value of book table using its getter method
			ResultSet bookrs = onlineBookStoreBean.getBookrs();

			// Setting the result set value as additional vales to be passed as response
			request.setAttribute("resultset", bookrs);

			// Setting the hello message for the current user to the session
			session.setAttribute("user", user);

			// Dispatching the request to home.jsp view
			request.getRequestDispatcher("/home.jsp").forward(request, response);

		} else {
			
			// If the user is not valid an error message is shown in the login.jsp view
			request.setAttribute("errMessage", userValidate);
			
			// Dispatching the request to login.jsp view
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
