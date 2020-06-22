package com.demo.onlinebookstore.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.onlinebookstore.OnlineBookStoreBean;
import com.demo.onlinebookstore.Validation;
import com.demo.onlinebookstore.admin.AdminDAO;

/**
 * This Controller is used for registering the user by adding the user details
 * to the table
 */
@WebServlet("/UserSignUpController")
public class UserSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This doPost() of UserSignUpController is used for registering the user by
	 * adding the user details to the table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// Getting the user details from the userSignUp.jsp view
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String emailId = request.getParameter("userEmail");
			long phone = Long.parseLong(request.getParameter("phone"));
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			// Creating the object of OnlineBookStoreBean
			OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

			// Setting the user details to the OnlineBookStoreBean
			onlineBookStoreBean.setFirstName(firstName);
			onlineBookStoreBean.setLastName(lastName);
			onlineBookStoreBean.setEmail(emailId);
			onlineBookStoreBean.setPhone(phone);
			onlineBookStoreBean.setGender(gender);
			onlineBookStoreBean.setDob(dob);
			onlineBookStoreBean.setUserName(userName);
			onlineBookStoreBean.setPassword(password);

			// Creating the object of Validation class
			Validation validation = new Validation();

			// Calling the userPasswordValidation() of the Validation class
			String passwordValue = validation.userPasswordValidation(onlineBookStoreBean);

			// Calling the userEmailValidation() of the Validation class
			String emailValidation = validation.userEmailValidation(onlineBookStoreBean);

			// Checking if the password is valid
			if (passwordValue.equals("invalid")) {

				// Setting the error message
				request.setAttribute("Message", "Invalid password");

				// Passing the control to the userSignUp.jsp view
				request.getRequestDispatcher("/userSignUp.jsp").forward(request, response);

				// Checking if the email is valid
			} else if (emailValidation.equals("invalid")) {

				// Setting the error message
				request.setAttribute("error", "Invalid Email Id... ");

				// Passing the control to the userSignUp.jsp view
				request.getRequestDispatcher("/userSignUp.jsp").forward(request, response);

			} else {

				// Calling the setUserDetails() of UserDAO class
				String state = UserDAO.setUserDetails(onlineBookStoreBean);

				// Storing the hello message for the user
				String user = "Hello " + userName;

				// Checking the value of state
				if (state == "Username already exits, try a new user name") {

					// Setting additional parameters
					request.setAttribute("fail", state);
					request.setAttribute("user", user);

					// Passing the control to the userSignUp.jsp view
					request.getRequestDispatcher("/userSignUp.jsp").forward(request, response);
				}

				// Checking the value of state
				if (state == "success") {

					// Setting additional parameters
					request.setAttribute("success", "User Registered");
					request.setAttribute("user", user);

					// Passing the control to the login.jsp view
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				} else {

					// Setting additional parameters
					request.setAttribute("errMessage", "Unable to register the user,try again!");

					// Passing the control to the userSignUp.jsp view
					request.getRequestDispatcher("/userSignUp.jsp").forward(request, response);
				}
			}

			// Catching the NumberFormatException
		} catch (NumberFormatException e) {

			// Setting additional parameters
			request.setAttribute("notvalid", "Enter valid user details");

			// Passing the control to the userSignUp.jsp view
			request.getRequestDispatcher("/userSignUp.jsp").forward(request, response);
		}
	}

}
