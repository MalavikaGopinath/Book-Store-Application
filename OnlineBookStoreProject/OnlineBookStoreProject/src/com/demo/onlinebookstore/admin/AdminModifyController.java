package com.demo.onlinebookstore.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Controller for modifying the price of a book
 *
 */
@WebServlet("/AdminModifyController")
public class AdminModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * method doPost() for modifying the price of a book
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			/*
			 * Getting the values from the adminModify.jsp and storing it in button and
			 * bookid
			 */
			String button = request.getParameter("buttonCheck");
			int bookID = Integer.parseInt(request.getParameter("bookid"));
		

			// creating the object of adminLogicBean
			AdminLoginBean adminLoginBean = new AdminLoginBean();

			// Checking the value in the button returns the submit from AdminDao class
			if (button.equals("SUBMIT")) {

				// set the value of book id from AdminModify.jsp by using object reference of
				// AdminLoginBean Class
				adminLoginBean.setBookID(bookID);

				// Calling the method enterBookDetails in the class AdminDao
				adminLoginBean = AdminDAO.enterBookDetails(adminLoginBean);

				// Check the value of AdminloginBean whether there is book which has the
				// id similar to bookId from the adminModify.jsp
				if (adminLoginBean == null) {

					// Passing the message to AdminModify.jsp page and dispatching to the
					// AdminModify.jsp
					// page
					request.setAttribute("error", "No such Book...");
					request.getRequestDispatcher("/adminModify.jsp").forward(request, response);
				} else {
					// Setting the Attributes which we want to pass in to AdminModify.jsp by getting
					// it from
					// AdminLoginBean
					request.setAttribute("success", "yes");
					request.setAttribute("bookid", adminLoginBean.getBookIdDB());
					request.setAttribute("bookname", adminLoginBean.getBookNameDB());
					request.setAttribute("price", adminLoginBean.getPriceDB());
					request.setAttribute("author", adminLoginBean.getAuthorDB());
					request.setAttribute("category", adminLoginBean.getCategoryDB());
					request.setAttribute("language", adminLoginBean.getLanguageDB());
					request.setAttribute("noofcopies", adminLoginBean.getNoCopiesDB());
					request.setAttribute("pubdate", adminLoginBean.getPubDateDB());
					request.getRequestDispatcher("/adminModify.jsp").forward(request, response);

				}

				// //check the button value as UPDATE for doing update operation
			} else if (button.equals("UPDATE")) {

				try {
					int new_price = Integer.parseInt(request.getParameter("newPrice"));

					// setting the bookid and new price using the reference of adminloginbean
					adminLoginBean.setBookID(bookID);
					adminLoginBean.setNew_price(new_price);

					// calling the modifyPrice() of AdminDAO by passing adminLoginBean as argument
					String value = AdminDAO.modifyPrice(adminLoginBean);

					// checks if the returned value of modifyprice() gives a success or not
					if (value.equals("success")) {

						// Dispatching the request to adminModify.jsp view along with the error message
						request.setAttribute("succmessage", "Book Successfully Updated......");
						request.getRequestDispatcher("/adminModify.jsp").forward(request, response);

						// Dispatching the request to adminmodify.jsp view with the fail message
					} else {
						request.setAttribute("failmessage", value);
						request.getRequestDispatcher("/adminModify.jsp").forward(request, response);

					}
					// NumberFormatException is catched by using try catch block
				} catch (NumberFormatException e) {
					// dispatching the request to adminModify.jsp veiw along with the error message
					request.setAttribute("blank", "New Price should not be blank....");
					request.getRequestDispatcher("/adminModify.jsp").forward(request, response);

				}

			} else {
				request.setAttribute("cancel", "Modification is Canceled....");
				request.getRequestDispatcher("/adminProperties.jsp").forward(request, response);

			}
			// nullPoint Exception is catched by using try catch block
		} catch (NumberFormatException e) {
			// dispatching the request to adminmodify.jsp veiw along with the error message
			request.setAttribute("valid", "Enter a valid Book Id....");
			request.getRequestDispatcher("/adminModify.jsp").forward(request, response);

		}

	}

}
