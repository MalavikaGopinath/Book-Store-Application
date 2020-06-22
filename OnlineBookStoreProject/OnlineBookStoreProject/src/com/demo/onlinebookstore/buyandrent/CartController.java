package com.demo.onlinebookstore.buyandrent;

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
 * This Controller is used for implementing all the functions that a user can do
 * from the home page, that is,the function of direct payment when BUY button is
 * clicked, the function of adding the books to cart when ADD TO CART button is
 * clicked, the function of adding the renting book of a user to the rent cart
 * when RENT button is clicked and for displaying the books in the cart table
 * when the Cart link is clicked
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * In this doGet() of CartController the values in the cart table are retrieved
	 * and the control will be given to the cart.jsp view where the cart will be
	 * displayed.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creating the object of OnlineBookStoreBean
		OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

		// Getting the current session
		HttpSession session = request.getSession(false);

		// Getting the current user from the session
		String userName = (String) session.getAttribute("userName");

		// Setting the user name to OnlineBookStoreBean
		onlineBookStoreBean.setUserName(userName);

		// Calling the getCartList() of CartDAO for getting the cart details
		CartDAO.getCartList(onlineBookStoreBean);

		// Getting the result set of cart from the OnlineBookStoreBean
		ResultSet rs = onlineBookStoreBean.getCartrs();

		// Setting the cart result set to the session
		session.setAttribute("cartrs", rs);

		// Passing the control to the cart.jsp view
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	} // end of doGet()

	/**
	 * This doPost() of CartController is used for performing the different
	 * operations such as buying a Book by directly going for the payment, buying
	 * the book by adding book into the cart,taking a book for rent and deleting
	 * books from the cart.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting the value of cart from the home.jsp and cart.jsp view and storing it
		String cart = request.getParameter("cart");

		// Creating the object of OnlineBookStoreBean
		OnlineBookStoreBean onlineBookStoreBean = new OnlineBookStoreBean();

		// Getting the current session
		HttpSession session = request.getSession(false);

		// Getting the current user from the session
		String userName = (String) session.getAttribute("userName");

		// Setting the user name to OnlineBookStoreBean
		onlineBookStoreBean.setUserName(userName);

		// Setting the value of cart to the OnlineBookStoreBean
		onlineBookStoreBean.setCart(cart);

		// Checks whether the value of cart is 'BUY'
		if (cart.equals("BUY")) {

			try {

				// Getting value of book id of the book which is to be bought by user from the
				// home.jsp view
				int bookId = Integer.parseInt(request.getParameter("bookid").trim());

				// Setting the book id to OnlineBookStoreBean
				onlineBookStoreBean.setBookId(bookId);

				// Setting the book id to the session
				session.setAttribute("bookId", bookId);

				// Calling the buyBook() of CartDAO by passing onlineBookStoreBean and storing
				// the returned value
				String state = CartDAO.buyBook(onlineBookStoreBean);

				// Checks the value returned from buyBook()
				if (state.equals("Book not available to buy") || state.equals("Failed to buy")) {

					// Setting the state as additional parameter which is to be passed with the
					// dispatcher
					request.setAttribute("state", state);

					// Calling the bookList() of BookDAO for getting the book details
					BookDAO.bookList(onlineBookStoreBean);

					// Getting the result set of books and storing it
					ResultSet bookrs = onlineBookStoreBean.getBookrs();

					// Setting the result set as additional parameter which is to be passed with the
					// dispatcher
					request.setAttribute("resultset", bookrs);

					// Passing the control to the home.jsp view
					request.getRequestDispatcher("/home.jsp").forward(request, response);

				} else {

					// Getting the book name which is bought from OnlineBookStoreBean
					String bookName = onlineBookStoreBean.getBookName();

					// Setting the book name to the session
					session.setAttribute("bookName", bookName);

					// The amount of the book which is to be bought from the OnlineBookStoreBean
					long amount = onlineBookStoreBean.getPrice();

					// Setting the amount to the session
					session.setAttribute("amount", amount);

					// Passing the control to the payment2.jsp view
					request.getRequestDispatcher("/payment2.jsp").forward(request, response);
				}

				// This catch is used for catching the NumberFormatException
			} catch (NumberFormatException e) {

				// setting the error message when the exception is raised
				request.setAttribute("errBookId", "Enter a valid Book ID");

				// Calling the bookList() of BookDAO for getting the book details
				BookDAO.bookList(onlineBookStoreBean);

				// Getting the result set of books and storing it
				ResultSet bookrs = onlineBookStoreBean.getBookrs();

				// Setting the result set as additional parameter which is to be passed with the
				// dispatcher
				request.setAttribute("resultset", bookrs);

				// Passing the control to the home.jsp view
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}

			// Checks whether the value of cart is ADD TO CART
		} else if (cart.equals("ADD TO CART")) {
			try {

				// Getting value of book id of the book which is to be bought by user from the
				// home.jsp view
				int bookId = Integer.parseInt(request.getParameter("bookid").trim());

				// Setting the book id to OnlineBookStoreBean
				onlineBookStoreBean.setBookId(bookId);

				// Calling the addCartList() of CartDAO to ad the books to the cart and storing
				// the returned value
				String state = CartDAO.addCartList(onlineBookStoreBean);

				// Checks if the returned value of addCartList() is "Book added to cart"
				if (state.equals("Book added to cart")) {

					// Setting the returned message from the addCartList
					request.setAttribute("state1", state);
				} else {

					// Setting the returned message from the addCartList
					request.setAttribute("state2", state);
				}
				// Calling the bookList() of BookDAO for getting the book details
				BookDAO.bookList(onlineBookStoreBean);

				// Getting the result set of books and storing it
				ResultSet bookrs = onlineBookStoreBean.getBookrs();

				// Setting the result set as additional parameter which is to be passed with the
				// dispatcher
				request.setAttribute("resultset", bookrs);

				// Passing the control to the home.jsp view
				request.getRequestDispatcher("/home.jsp").forward(request, response);

				// This catch is used for catching the NumberFormatException
			} catch (NumberFormatException e) {

				// setting the error message when the exception is raised
				request.setAttribute("errBookId", "Enter a valid Book ID");

				// Calling the bookList() of BookDAO for getting the book details
				BookDAO.bookList(onlineBookStoreBean);

				// Getting the result set of books and storing it
				ResultSet bookrs = onlineBookStoreBean.getBookrs();

				// Setting the result set as additional parameter which is to be passed with the
				// dispatcher
				request.setAttribute("resultset", bookrs);

				// Passing the control to the home.jsp view
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}

			// Checks if the value of cart is RENT
		} else if (cart.equals("RENT")) {

			try {

				// Getting value of book id of the book which is to be bought by user from the
				// home.jsp view
				int bookId = Integer.parseInt(request.getParameter("bookid").trim());

				// Setting the book id to OnlineBookStoreBean
				onlineBookStoreBean.setBookId(bookId);

				// Calling the addRentCartList() of CartDAO and stores the return value message
				String state = CartDAO.addRentCartList(onlineBookStoreBean);

				// Checks whether the returned value is "Rent Successful"
				if (state.equals("Rent Successfull")) {

					// Setting the returned message from the addRentCartList()
					request.setAttribute("state1", state);
				} else {

					// Setting the returned message from the addRentCartList()
					request.setAttribute("state2", state);
				}

				// Calling the bookList() of BookDAO for getting the book details
				BookDAO.bookList(onlineBookStoreBean);

				// Getting the result set of books and storing it
				ResultSet bookrs = onlineBookStoreBean.getBookrs();

				// Setting the result set as additional parameter which is to be passed with the
				// dispatcher
				request.setAttribute("resultset", bookrs);

				// Passing the control to the home.jsp view
				request.getRequestDispatcher("/home.jsp").forward(request, response);

				// This catch is used for catching the NumberFormatException
			} catch (NumberFormatException e) {

				// setting the error message when the exception is raised
				request.setAttribute("errBookId", "Enter a valid Book ID");

				// Calling the bookList() of BookDAO for getting the book details
				BookDAO.bookList(onlineBookStoreBean);

				// Getting the result set of books and storing it
				ResultSet bookrs = onlineBookStoreBean.getBookrs();

				// Setting the result set as additional parameter which is to be passed with the
				// dispatcher
				request.setAttribute("resultset", bookrs);

				// Passing the control to the home.jsp view
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}
			// Checking whether the value of cart is a number
		} else if (Integer.parseInt(cart) > 0) {

			// Getting value of book id of the book which is to be bought by user from the
			// cart.jsp view
			int bookId = Integer.parseInt(cart);

			// Setting the book id to OnlineBookStoreBean
			onlineBookStoreBean.setBookId(bookId);

			// Calling the deleteCart() of CartDAO by passing the object of
			// OnlineBookStoreBean
			CartDAO.deleteCart(onlineBookStoreBean);

			// Calling the getCartList() of CartDAO by passing the object of
			// OnlineBookStoreBean
			CartDAO.getCartList(onlineBookStoreBean);

			// Getting the result set of cart from the OnlineBookStoreBean
			ResultSet rs = onlineBookStoreBean.getCartrs();

			// Setting the cart result set to the session
			session.setAttribute("cartrs", rs);

			// Passing the control to the cart.jsp view
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}

	}// end of doPost()

}// end of CartController
