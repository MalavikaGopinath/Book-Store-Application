package com.demo.onlinebookstore.buyandrent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.demo.onlinebookstore.DBConnection;
import com.demo.onlinebookstore.OnlineBookStoreBean;

/**
 * 
 * This DAO Class is used for implementing the functions like adding books to
 * the cart, deleting books from the cart, buying the books directly without
 * going to the cart, getting the books from the cart table,adding books to rent
 * table and getting the books taken for rent from the rent table by getting
 * connection to the database.
 * 
 */
public class CartDAO {

	// The connection variable
	static Connection con = null;

	/**
	 * 
	 * This addCartList() is used for adding books into the cart table by getting
	 * connection to the database and returning the messages.
	 * 
	 */
	static public String addCartList(OnlineBookStoreBean onlineBookStoreBean) {

		// Variables for storing the values from the table.
		int bookIdDB = 0;
		String bookNameDB = null;
		int priceDB = 0;
		String actionDB = null;
		int copiesDB = 0;

		try {

			// calling the createConnection() of DBConnection class for getting the
			// connection
			con = DBConnection.createConnection();

			// Creates a PreparedStatement object for sending for sending parameterized SQL
			// statement to the database and selecting the details from book table
			PreparedStatement statement = con.prepareStatement("select * from book_bt ");

			// Executes the given SQL statement, which returns a single ResultSet object
			ResultSet rs = statement.executeQuery();

			// Getting the values in the result set
			while (rs.next()) {

				// Storing the values retrieved of book table
				bookIdDB = rs.getInt("book_id");
				actionDB = rs.getString("action");
				copiesDB = rs.getInt("NO_OF_COPIES");

				// Checking whether the book id entered user is already in the cart table,and
				// also whether it has the buy option
				if ((bookIdDB == onlineBookStoreBean.getBookId())
						&& (actionDB.equals("BUY") || actionDB.equals("BOTH"))) {

					// Storing the book name and price from table
					bookNameDB = rs.getString("book_name");
					priceDB = rs.getInt("price");

					// Creates a PreparedStatement object for sending for sending parameterized SQL
					// statement to the database and selecting the details from cart table
					PreparedStatement statement2 = con.prepareStatement("select * from cart_bt ");

					// Executes the given SQL statement, which returns a single ResultSet object
					ResultSet rs2 = statement2.executeQuery();

					// Getting the values in the result set
					while (rs2.next()) {

						// Checking whether the user has already added the book id to the cart
						if (onlineBookStoreBean.getUserName().equals(rs2.getString("user_name"))
								&& (rs2.getInt("book_id") == onlineBookStoreBean.getBookId())) {

							// Checking whether the quantity of book is less than the no of copies available
							// for that book
							if (rs2.getInt("quantity") < (copiesDB)) {

								// Updating the price and quantity of that book id
								PreparedStatement statement3 = con.prepareStatement(
										"update cart_bt set price=price+?,quantity=? where book_id=?");

								// passing values for the update statement
								statement3.setInt(1, priceDB);
								statement3.setInt(2, rs2.getInt(5) + 1);
								statement3.setInt(3, rs.getInt("book_id"));

								// Updating the table
								statement3.executeUpdate();

								// Returning the message
								return "Book added to cart";
							} else {

								// Returning the message
								return "No enough copies";
							}
						}
					}

					// Checking whether if there is any bookin the cart table
					if (((rs2.next() == false) && bookIdDB == onlineBookStoreBean.getBookId())
							&& (actionDB.equals("BUY") || actionDB.equals("BOTH"))) {

						// Prepared statement for inserting book into the cart
						PreparedStatement statement4 = con.prepareStatement("insert into cart_bt values(?,?,?,?,?)");

						// Passing the values that is to be inserted into the cart table
						statement4.setString(1, onlineBookStoreBean.getUserName());
						statement4.setInt(2, onlineBookStoreBean.getBookId());
						statement4.setString(3, bookNameDB);
						statement4.setInt(4, priceDB);
						statement4.setInt(5, 1);
						statement4.executeUpdate();

						// Committing the statement
						con.commit();

						// Returning the message
						return "Book added to cart";

					}
				}

			}

			// Catching the SQL exception
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();
		}
		return "Was not able to add the Book to cart";
	}

	/**
	 * This buyBook() is used for buying a book directly from the home withoutadding
	 * it into the cart
	 * 
	 */
	public static String buyBook(OnlineBookStoreBean onlineBookStoreBean) {

		// Declaring the variables for storing the values from the database
		int bookIdDB = 0;
		String bookNameDB = null;
		int priceDB = 0;
		String actionDB = null;

		try {
			// Getting the connection
			con = DBConnection.createConnection();

			// Selecting the books from the book table
			PreparedStatement statement = con.prepareStatement("select * from book_bt ");

			// Executing the query and storing the result set
			ResultSet rs = statement.executeQuery();

			// Getting the details in the book table
			while (rs.next()) {

				// Getting the details from book table and storing it
				bookIdDB = rs.getInt("book_id");
				actionDB = rs.getString("action");

				// Checking whether the book has the buy option
				if ((bookIdDB == onlineBookStoreBean.getBookId())
						&& (actionDB.equals("BUY") || actionDB.equals("BOTH"))) {

					// Getting the book name and price of the book from table
					bookNameDB = rs.getString("book_name");
					priceDB = rs.getInt("price");

					// Setting the book name and price to the OnlineBookStoreBean
					onlineBookStoreBean.setBookName(bookNameDB);
					onlineBookStoreBean.setPrice(priceDB);

					// Returning the message
					return "success";
				}
			}

			// catching the SQLException
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();
		}

		// Returning the message
		return "Book not available to buy";
	}

	/**
	 * 
	 * This addRentCartList() is used to add book to the rent table
	 */
	public static String addRentCartList(OnlineBookStoreBean onlineBookStoreBean) {

		// For storing the values from database
		int bookIdDB = 0;
		String bookNameDB = null;

		try {

			// Getting the connection
			con = DBConnection.createConnection();

			// Selecting the books from the book table
			PreparedStatement statement = con.prepareStatement("select * from book_bt");

			// Executing the query and storing its result set
			ResultSet resultSet = statement.executeQuery();

			// Getting the values in the result set
			while (resultSet.next()) {

				// Checking whether the book is in the book table and it has a rent option
				if (resultSet.getInt("book_id") == onlineBookStoreBean.getBookId()
						&& (resultSet.getString("action").equals("RENT")
								|| resultSet.getString("action").equals("BOTH"))) {

					// Storing the values from the table
					bookIdDB = resultSet.getInt(1);
					bookNameDB = resultSet.getString("book_name");

					// Selecting the books in the rent table
					PreparedStatement statement2 = con.prepareStatement("select user_name,book_id from rent_bt ");

					// executing the query and storing the result set
					ResultSet resultSet2 = statement2.executeQuery();

					// Iterating the result set
					while (resultSet2.next()) {

						// Checking whether the user has taken the book for rent
						if ((!resultSet2.getString(1).equals(onlineBookStoreBean.getUserName())
								&& resultSet2.getInt(2) != onlineBookStoreBean.getBookId())) {

							// Inserting the book to rent table
							PreparedStatement statement3 = con
									.prepareStatement("insert into rent_bt values(?,?,?,?,?)");

							// Passing the values for the insert statement
							statement3.setString(1, onlineBookStoreBean.getUserName());
							statement3.setInt(2, bookIdDB);
							statement3.setString(3, bookNameDB);

							// Setting the format for the date
							SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

							// Getting the current date
							Calendar cal = Calendar.getInstance();

							// Converting the current date to the format
							String rentDate = sd.format(cal.getTime());

							// Passing the rent date to insert statement
							statement3.setString(4, rentDate);

							// Calculating the date after 7 days
							cal.add(Calendar.DATE, 7);

							// Converting the date to the format required
							String returnDate = sd.format(cal.getTime());

							// Passing the return date to insert statement
							statement3.setString(5, returnDate);

							// Executing the insert statement
							statement3.executeUpdate();

							// Committing the statement
							con.commit();

							// Returning the message
							return "Rent Successfull";
						} else {

							// Returning the message
							return "Cannot rent more than one book at a time";
						}
					}

					// Checking whether the user has taken the book for rent
					if (((resultSet2.next() == false) && (bookIdDB == onlineBookStoreBean.getBookId()))
							&& (resultSet.getString("action").equals("RENT")
									|| resultSet.getString("action").equals("BOTH"))) {

						// Inserting the book to rent table
						PreparedStatement statement3 = con.prepareStatement("insert into rent_bt values(?,?,?,?,?)");

						// Passing the values for the insert statement
						statement3.setString(1, onlineBookStoreBean.getUserName());
						statement3.setInt(2, bookIdDB);
						statement3.setString(3, bookNameDB);

						// Setting the format for the date
						SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

						// Getting the current date
						Calendar cal = Calendar.getInstance();

						// Converting the current date to the format
						String rentDate = sd.format(cal.getTime());

						// Passing the rent date to insert statement
						statement3.setString(4, rentDate);

						// Calculating the date after 7 days
						cal.add(Calendar.DATE, 7);

						// Converting the date to the format required
						String returnDate = sd.format(cal.getTime());

						// Passing the return date to insert statement
						statement3.setString(5, returnDate);

						// Executing the insert statement
						statement3.executeUpdate();

						// Committing the statement
						con.commit();

						// Returning the message
						return "Rent Successfull";

					}
				}
			}

			// Catching the SQLException
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// Returning the message
		return "Rent option failed";
	}

	/**
	 * 
	 * This getCartList() is used to get the cart details from the cart table
	 */
	public static void getCartList(OnlineBookStoreBean onlineBookStoreBean) {

		try {
			// Getting the connection
			con = DBConnection.createConnection();

			// selecting the books from the cart table of that user
			PreparedStatement statement = con.prepareStatement("select * from cart_bt where user_name=?");

			// Passing the value for the select statement
			statement.setString(1, onlineBookStoreBean.getUserName());

			// Executing the query and storing the result set
			ResultSet resultSet = statement.executeQuery();

			// Setting the result set to the OnlineBookStoreBean
			onlineBookStoreBean.setCartrs(resultSet);

			// Catching the SQLException
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * This deleteCart() is used for deleting books from the cart
	 */
	public static void deleteCart(OnlineBookStoreBean onlineBookStoreBean) {

		// For storing the price of the book
		int priceDB = 0;

		try {
			// Getting the connection
			con = DBConnection.createConnection();

			// Selecting the books in the cart table of the user
			PreparedStatement statement = con.prepareStatement("select * from cart_bt where user_name=?");

			// Passing the value for the select statement
			statement.setString(1, onlineBookStoreBean.getUserName());

			// Executing and storing the result set
			ResultSet resultSet = statement.executeQuery();

			// iterating the result set
			while (resultSet.next()) {

				// Checking whether the book is in book table and has only one copy
				if ((resultSet.getInt(2) == onlineBookStoreBean.getBookId()) && (resultSet.getInt(5) > 1)) {

					// selecting the book from the table
					PreparedStatement statement2 = con.prepareStatement("select * from book_bt where book_id=?");

					// Passing the value to the select statement
					statement2.setInt(1, onlineBookStoreBean.getBookId());

					// Executing the statement and storing the result set
					ResultSet resultSet2 = statement2.executeQuery();

					// Iterating the result set
					while (resultSet2.next()) {

						// Getting the price from table and storing it
						priceDB = resultSet2.getInt(3);
					}

					// PreparedStatement for updating the price and quantity in cart table
					PreparedStatement statement3 = con.prepareStatement(
							"update cart_bt set price=price-?,quantity=quantity-1 where (book_id=? and user_name=?)");

					// Passing the values for the update statement
					statement3.setInt(1, priceDB);
					statement3.setInt(2, onlineBookStoreBean.getBookId());
					statement3.setString(3, onlineBookStoreBean.getUserName());

					// executing the update statement
					statement3.executeUpdate();

					// Checking whether there is only one quantity of the book
				} else if ((resultSet.getInt(2) == onlineBookStoreBean.getBookId()) && (resultSet.getInt(5) == 1)) {

					// Deleting the book from cart table
					PreparedStatement statement4 = con
							.prepareStatement("delete from cart_bt where (user_name=? and book_id=?)");

					// Passing the values for the delete statement
					statement4.setString(1, onlineBookStoreBean.getUserName());
					statement4.setInt(2, onlineBookStoreBean.getBookId());

					// updating the delete statement
					statement4.executeUpdate();

					// Committing the statement
					con.commit();

				}
			}

			// For catching the SQLException
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * This getRentList() is used to get the book taken for rent by the user
	 */
	public static void getRentList(OnlineBookStoreBean onlineBookStoreBean) {

		try {
			// Getting the connection
			con = DBConnection.createConnection();

			// Selecting the rent book from the table of that user
			PreparedStatement statement = con.prepareStatement("select * from rent_bt where user_name=?");

			// Passing the value for the select statement
			statement.setString(1, onlineBookStoreBean.getUserName());

			// Executing the query and storing the result set
			ResultSet resultSet = statement.executeQuery();

			// Setting the rent result set to the OnlineBookStoreBean
			onlineBookStoreBean.setRentrs(resultSet);

			// Catching the SQLException
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();
		}

	}
}
