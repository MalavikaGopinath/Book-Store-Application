package com.demo.onlinebookstore.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.onlinebookstore.Validation;

/**
 * This Controller is used for registering the admin by adding the admin details
 * to the table
 */
@WebServlet("/AdminSignUpController")
public class AdminSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This doPost() of AdminSignUpController is used for registering the admin by
	 * adding the admin details to the table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Getting the admin details from the adminSignUp.jsp view
			String adminId = request.getParameter("adminid");
			String adminPassword = request.getParameter("adminpassword");
			String adminFirstName = request.getParameter("adminfirstname");
			String adminLastName = request.getParameter("adminlastname");
			String adminEmail = request.getParameter("adminemail");
			double adminPhone = Double.parseDouble(request.getParameter("adminphone"));

			// Creating the object of AdminLoginBean
			AdminLoginBean adminLoginBean = new AdminLoginBean();

			// Setting the admin details to the AdminLoginBean
			adminLoginBean.setAdminId(adminId);
			adminLoginBean.setAdminPassword(adminPassword);
			adminLoginBean.setAdminFirstName(adminFirstName);
			adminLoginBean.setAdminLastName(adminLastName);
			adminLoginBean.setAdminEmail(adminEmail);
			adminLoginBean.setAdminPhone(adminPhone);

			// Creating the object of Validation class
			Validation validation = new Validation();

			/*
			 * Calling the different Validate() of validation class by passing
			 * AdminLoginBean as argument ..
			 * 
			 */
			String passwordValue = validation.passValidation(adminLoginBean);
			String emailValidation = validation.emailValidation(adminLoginBean);
			String idValue = validation.idValidation(adminLoginBean);

			// Checks if the value returned by idValidate() is invalid
			if (idValue.equals("invalid")) {

				// dispatching the request to adminSignUp.jsp veiw along with the error message
				request.setAttribute("err", "Max five character");
				request.getRequestDispatcher("/adminSignUp.jsp").forward(request, response);

				// Checks if the value returned by passValidate() is invalid
			} else if (passwordValue.equals("invalid")) {

				// dispatching the request to adminSignUp.jsp veiw along with the error message
				request.setAttribute("Message", "Invalid password");
				request.getRequestDispatcher("/adminSignUp.jsp").forward(request, response);

				// Checks if the value returned by emailValidate() is invalid
			} else if (emailValidation.equals("invalid")) {

				// dispatching the request to adminSignUp.jsp veiw along with the error message
				request.setAttribute("error", "Invalid Email Id... ");
				request.getRequestDispatcher("/adminSignUp.jsp").forward(request, response);
			} else {

				// creating object of adminDao
				AdminDAO adminDao = new AdminDAO();

				// Calling the adminsignup() of validation class by passing AdminLoginBean as
				// argument and stored it as an value reference
				String value = adminDao.adminSignUp(adminLoginBean);

				// Checks if the value returned by adminSignUp() is success
				if (value.equals("success")) {

					// dispatching the request to admin.jsp veiw along with the error message
					request.setAttribute("errMessage", "You are successfully Registerd...");
					request.getRequestDispatcher("/admin.jsp").forward(request, response);

					// Checks if the value returned by adminSignUp() is UniqueAdmin
				} else if (value.equals("UniqueAdmin")) {

					// dispatching the request to adminSignUp.jsp veiw along with the error message
					request.setAttribute("err1", "Unique Admin Id....");
					request.getRequestDispatcher("/adminSignUp.jsp").forward(request, response);
				} else {
					request.setAttribute("errMessage", value);
					request.getRequestDispatcher("/adminSignUp.jsp").forward(request, response);

				}
			}
			// NumberFormatException is catched by using try catch block
		} catch (NumberFormatException e) {

			// dispatching the request to adminSignUp.jsp veiw along with the error message
			request.setAttribute("err2", "Enter valid phone number... ");
			request.getRequestDispatcher("/adminSignUp.jsp").forward(request, response);

			// nullPoint Exception is catched by using try catch block
		} catch (NullPointerException e) {
			// dispatching the request to adminSignUp.jsp veiw along with the error message
			request.setAttribute("err3", "Enter valid Admin Details..... ");
			request.getRequestDispatcher("/adminSignUp.jsp").forward(request, response);

		}

	}

}
