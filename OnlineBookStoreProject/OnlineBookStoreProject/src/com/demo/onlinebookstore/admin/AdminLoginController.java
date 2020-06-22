package com.demo.onlinebookstore.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * This controller is user for checking the admin validations.
 *
 */
@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This doPost() of AdminLoginController check whether it admin_id and
	 * admin_pass entered by the admin are valid. If its a valid user the control
	 * will pass to the adminproperties.jsp view else an error message is shown in
	 * the admin.jsp page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Getting the adminId and adminpass values from the adminlogin and storing it
		 * in adminuserid and adminpassword.
		 */

		String adminUserID = request.getParameter("adminId");
		String adminPassword = request.getParameter("adminpass");

		// Creating a session for the current admin
		HttpSession session = request.getSession();

		// Creating the object of AdminLogicBean
		AdminLoginBean adminLoginBean = new AdminLoginBean();

		// Setting the values of user name and password into its respective setter
		// methods
		adminLoginBean.setAdminUserID(adminUserID);
		adminLoginBean.setAdminPassword(adminPassword);

		// calling the authenticateAdmin() of adminDAO by passing onlineBookStoreBean as
		// argument and store as userValidate
		String userValidate = AdminDAO.authenticateAdmin(adminLoginBean);

		// checks if the returned value of authenticateAdmin() gives a success or not
		if (userValidate.equals("success")) {

			// For storing the hello message to the admin once logged in
			session.setAttribute("adminid", "Hello  " + adminUserID);

			// Dispatching the request to adminProperties.jsp view
			request.getRequestDispatcher("/adminProperties.jsp").forward(request, response);

		} else {

			// If the admin is not valid an error message is shown in the admin.jsp view
			request.setAttribute("errmessage", "Invalid Admin Credentials......");

			// Dispatching the request to admin.jsp view
			request.getRequestDispatcher("/admin.jsp").forward(request, response);

		}

	}

}
