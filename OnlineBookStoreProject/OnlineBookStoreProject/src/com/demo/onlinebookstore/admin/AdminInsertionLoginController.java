package com.demo.onlinebookstore.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Controller for inserting a book to the table book_bt
 *
 */
@WebServlet("/AdminInsertionLoginController")
public class AdminInsertionLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * method for inserting a book to the table book_bt
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		
		// Storing the values from the jsp page to different variables in servlet page
		int bookID = Integer.parseInt(request.getParameter("bookid"));
		String bookName = request.getParameter("bookname");
		int price = Integer.parseInt(request.getParameter("price"));
		String pub_date = request.getParameter("pubdate");
		String author = request.getParameter("author");
		String language = request.getParameter("language");
		String category = request.getParameter("category");
		int noOfCopy = Integer.parseInt(request.getParameter("noofcopy"));
		String option = request.getParameter("option");

		// creating the object reference of AdminLoginBean Class
		AdminLoginBean adminLoginBean = new AdminLoginBean();

		// setting the variables using loginBean reference
		adminLoginBean.setBookID(bookID);
		adminLoginBean.setBookName(bookName);
		adminLoginBean.setPrice(price);
		adminLoginBean.setPub_date(pub_date);
		adminLoginBean.setAuthor(author);
		adminLoginBean.setLanguage(language);
		adminLoginBean.setCategory(category);
		adminLoginBean.setNoOfCopy(noOfCopy);
		adminLoginBean.setOption(option);

		AdminDAO adminDao = new AdminDAO();
		// calling the addBook method in the AdminDAO and return String value
		String value = adminDao.addBook(adminLoginBean);
		// According to the return value the message will pass to corresponding jsp page
		if (value.equals("success")) {

			request.setAttribute("errMessage", "You are successfully added a Book...");
			request.getRequestDispatcher("/adminInsertion.jsp").forward(request, response);

		} else {
			request.setAttribute("errMessage", value);
			request.getRequestDispatcher("/adminInsertion.jsp").forward(request, response);

		}

		// nullPoint Exception is catched by using try catch block
				} catch (NumberFormatException e) {
					// dispatching the request to admininsertion.jsp veiw along with the error message
					request.setAttribute("valid", "Enter a valid Book Id....");
					request.getRequestDispatcher("/adminInsertion.jsp").forward(request, response);

				}
	}

}
