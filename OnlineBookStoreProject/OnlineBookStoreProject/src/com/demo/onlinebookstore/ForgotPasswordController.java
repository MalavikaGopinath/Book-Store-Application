package com.demo.onlinebookstore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.onlinebookstore.OnlineBookStoreBean;
import com.demo.onlinebookstore.user.LoginDAO;

/**
 * Controller used to change the password of a user using email id
 */
@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Controller doPost() is used to change the password of a user using his email
	 * id.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting the email id of the user from forgotPassword.jsp view and storing it.
		String email = request.getParameter("emailid");

		// Getting the new password from the user from forgotPassword.jsp view and
		// storing it.
		String password = request.getParameter("password");

		// Getting the confirm password from the user from forgotPassword.jsp view and
		// storing it.
		String confirmpassword = request.getParameter("confirmpassword");

		// Creating the object of OnlineBookStoreBean
		OnlineBookStoreBean onlinebookstore = new OnlineBookStoreBean();

		// Setting the email id to the OnlineBookStoreBean
		onlinebookstore.setEmail(email);

		// Setting the password to the OnlineBookStoreBean
		onlinebookstore.setPassword(password);

		onlinebookstore.setConfirmpassword(confirmpassword);

		// setting the new password for existing user with his email id
		onlinebookstore.setPassword(password);

		// calling the forgotpassword() of LoginDAO with the object of
		// OnlineBookStoreBean as argument
		String value = LoginDAO.forgotpassword(onlinebookstore);

		// Checks if the value returned by forgotpassword() is success
		if (value.equals("Password Changed..")) {

			// Dispatching the request to login.jsp view
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else if(value.equals("passwords not matching")) {

			// Setting the error message which is to be shown if the password was not able
			// to be updated successfully
			request.setAttribute("errMessage", value);

			// Dispatching the request to forgotPassword.jsp view
			request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
		}else
		{
			// Setting the error message which is to be shown if the password was not able
						// to be updated successfully
						request.setAttribute("errMessage", value);

						// Dispatching the request to forgotPassword.jsp view
						request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
		}
	}
}
