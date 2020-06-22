package com.demo.onlinebookstore.payment;

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
 * Controller for the payment for user
 */
@WebServlet("/OnlinePaymentController")
public class OnlinePaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Here the payment of the book when the book is bought directing using buy
	 * button
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// Getting the customer id from the onlinePayment2.jsp view
			int custId = Integer.parseInt(request.getParameter("cust_id"));
			try {

				// Getting the customer id from the onlinePayment2.jsp view
				int custPin = Integer.parseInt(request.getParameter("cust_pin"));

				// Getting the current session
				HttpSession session = request.getSession(false);

				// Getting the current user from the session
				String userName = (String) session.getAttribute("userName");

				// Getting the amount of the book the user is buying from the session
				long amount = (long) session.getAttribute("amount");

				// Getting the book id of the book the user is buying from the session
				int bookId = (int) session.getAttribute("bookId");

				// Creating the object of OnlineBookStoreBean
				OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

				// Storing the values to the OnlineBookStoreBean
				onlineBookStoreBean.setCustomerId(custId);
				onlineBookStoreBean.setPinNo(custPin);
				onlineBookStoreBean.setPrice(amount);
				onlineBookStoreBean.setBookId(bookId);
				onlineBookStoreBean.setUserName(userName);

				// Calling the onlineBanking2() of PaymentDAO
				String msg = PaymentDAO.onlineBanking2(onlineBookStoreBean);

				// Checking the payment was successful
				if (msg.equals("Payment Successfull")) {

					// Calling the bookList() of BookDAO for getting the book details
					BookDAO.bookList(onlineBookStoreBean);

					// Getting the result set of books and storing it
					ResultSet bookrs = onlineBookStoreBean.getBookrs();

					// Setting the result set as additional parameter which is to be passed with the
					// dispatcher
					request.setAttribute("resultset", bookrs);

					// Setting the message as additional parameter
					request.setAttribute("msg1", msg);

					// Passing the control to the home.jsp view
					request.getRequestDispatcher("home.jsp").forward(request, response);

				} else {

					// Calling the bookList() of BookDAO for getting the book details
					BookDAO.bookList(onlineBookStoreBean);

					// Getting the result set of books and storing it
					ResultSet bookrs = onlineBookStoreBean.getBookrs();

					// Setting the result set as additional parameter which is to be passed with the
					// dispatcher
					request.setAttribute("resultset", bookrs);

					// Setting the message as additional parameter
					request.setAttribute("msg2", msg);

					// Passing the control to the home.jsp view
					request.getRequestDispatcher("onlinePayment2.jsp").forward(request, response);
				}

				// Catching the NumberFormatException of pin
			} catch (NumberFormatException e) {

				// Setting the message as additional parameter
				request.setAttribute("custpin", "Enter a valid customer pin");

				// Passing the control to the onlinePayment2.jsp view
				request.getRequestDispatcher("onlinePayment2.jsp").forward(request, response);
			}

			// Catching the NumberFormatException of customer if
		} catch (NumberFormatException e) {

			// Setting the message as additional parameter
			request.setAttribute("custid", "Enter a valid customer id");

			// Passing the control to the onlinePayment2.jsp view
			request.getRequestDispatcher("onlinePayment2.jsp").forward(request, response);
		}

	}// end of doGet()

	/**
	 * Payment from cart is done in this methoddoPost() of OnlinePaymentController
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Getting the customer id from the onlinePayment2.jsp view
			int custId = Integer.parseInt(request.getParameter("cust_id"));
			try {

				// Getting the customer id from the onlinePayment2.jsp view
				int custPin = Integer.parseInt(request.getParameter("cust_pin"));

				// Getting the current session
				HttpSession session = request.getSession(false);

				// Getting the current user from the session
				String userName = (String) session.getAttribute("userName");

				// Getting the totalamount of the books in the cart
				long amount = (long) session.getAttribute("totalprice");

				// Creating the object of OnlineBookStoreBean
				OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

				// Storing the values to the OnlineBookStoreBean
				onlineBookStoreBean.setCustomerId(custId);
				onlineBookStoreBean.setPinNo(custPin);
				onlineBookStoreBean.setPrice(amount);
				onlineBookStoreBean.setUserName(userName);

				// Calling the onlineBanking1() of PaymentDAO
				String msg = PaymentDAO.onlineBanking1(onlineBookStoreBean);

				// Checking the payment was successful
				if (msg.equals("Payment Successfull")) {

					// Calling the bookList() of BookDAO for getting the book details
					BookDAO.bookList(onlineBookStoreBean);

					// Getting the result set of books and storing it
					ResultSet bookrs = onlineBookStoreBean.getBookrs();

					// Setting the result set as additional parameter which is to be passed with the
					// dispatcher
					request.setAttribute("resultset", bookrs);

					// Setting the message as additional parameter
					request.setAttribute("msg1", msg);

					// Passing the control to the home.jsp view
					request.getRequestDispatcher("home.jsp").forward(request, response);

				} else {

					// Setting the message as additional parameter
					request.setAttribute("msg2", msg);

					// Passing the control to the onlinePayment1.jsp view
					request.getRequestDispatcher("onlinePayment1.jsp").forward(request, response);
				}
				// Catching the NumberFormatException of pin
			} catch (NumberFormatException e) {

				// Setting the message as additional parameter
				request.setAttribute("custpin", "Enter a valid customer pin");

				// Passing the control to the onlinePayment2.jsp view
				request.getRequestDispatcher("onlinePayment2.jsp").forward(request, response);
			}

			// Catching the NumberFormatException of customer if
		} catch (NumberFormatException e) {

			// Setting the message as additional parameter
			request.setAttribute("custid", "Enter a valid customer id");

			// Passing the control to the onlinePayment2.jsp view
			request.getRequestDispatcher("onlinePayment2.jsp").forward(request, response);
		}
	}// end of doPost()
}// end of OnlinePaymentController
