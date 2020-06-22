package com.demo.onlinebookstore.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.onlinebookstore.DBConnection;
import com.demo.onlinebookstore.OnlineBookStoreBean;

/**
 * 
 * The payment functions are carried out in this DAO class
 *
 */
public class PaymentDAO {

	// Variables for the Database related functions
	static Connection con = null;
	static PreparedStatement statement = null;
	static ResultSet resultSet = null;
	static ResultSet resultSet1 = null;
	static long balance;
	static int pin;
	static long remaining;
	static String sql;

	/**
	 * 
	 * Functions for payment from cart
	 */
	public static String onlineBanking1(OnlineBookStoreBean onlineBookStoreBean) {

		// Keeping user entered values
		int custId = onlineBookStoreBean.getCustomerId();
		int custPin = onlineBookStoreBean.getPinNo();
		long amount = onlineBookStoreBean.getPrice();
		String userName = onlineBookStoreBean.getUserName();
		try {
			// establishing connection
			con = DBConnection.createConnection();

			// Selecting the details in the payment tables
			statement = con.prepareStatement("select customer_id,pin_number,balance from payment_bt");

			// Executing the query and storing it in the result set
			resultSet = statement.executeQuery();

			// until next row is present otherwise it returns
			// fetch the present values
			while (resultSet.next()) {

				// Storing the customer id, pin and balance in the table
				int custIdDB = resultSet.getInt("customer_id");
				int custPinDB = resultSet.getInt("pin_number");
				balance = resultSet.getLong("balance");

				// Checking whether the values entered are equal to the values in the table
				if (custId == custIdDB && custPin == custPinDB) {

					// checking whether the amount to pay is less than the account balance
					if (amount < balance) {

						// deducting bank balance
						remaining = balance - amount;

						// Updating the payment table
						sql = "update payment_bt set balance=? where customer_id=?";
						statement = con.prepareStatement(sql);

						// Passing the values to the update statement
						statement.setDouble(1, remaining);
						statement.setInt(2, custId);

						// Executing the update statement
						statement.executeUpdate();

						// Secting the quantity and bookid from the cart table which is equl to that
						// inthe book table using join
						PreparedStatement statement2 = con.prepareStatement(
								"select c.quantity,c.book_id from cart_bt c join book_bt b on c.book_id=b.book_id");

						// Executing the query and storing the result set
						ResultSet resultSet1 = statement2.executeQuery();

						// Iterating the result set
						while (resultSet1.next()) {

							// Storing the book id and quantity from the cart table
							int quantity = resultSet1.getInt("quantity");
							int bookId = resultSet1.getInt("book_id");

							// Updating no_of_available_copies of the book table
							PreparedStatement statement3 = con
									.prepareStatement("update book_bt set no_of_copies=no_of_copies-? where book_id=?");

							// Passing values to the update statements
							statement3.setInt(1, quantity);
							statement3.setInt(2, bookId);

							// Executing the update statement
							statement3.executeUpdate();
						}

						// Storing the delete query
						sql = "delete from cart_bt where user_name=?";
						statement = con.prepareStatement(sql);

						// Passing values to the delete statements
						statement.setString(1, userName);

						// executing the update statement
						statement.executeUpdate();

						// Committing
						con.commit();

						// Returning the message
						return "Payment Successfull";
					} else {

						// Committing
						con.commit();

						// Returning the message
						return "No enough balance";
					}

				}
			}

			// Catching the Exception
		} catch (Exception e) {
			System.out.println("");
		}

		// Returning the message
		return "Payment Failed";
	}

	/**
	 * 
	 * method for the functions for direct buy button
	 */
	public static String onlineBanking2(OnlineBookStoreBean onlineBookStoreBean) {

		// Keeping user entered values in
		int custId = onlineBookStoreBean.getCustomerId();
		int custPin = onlineBookStoreBean.getPinNo();
		long amount = onlineBookStoreBean.getPrice();
		String userName = onlineBookStoreBean.getUserName();
		try {
			// establishing connection
			con = DBConnection.createConnection();

			statement = con.prepareStatement("select customer_id,pin_number,balance from payment_bt");

			// Executing the select statement andstoring the resultset
			resultSet = statement.executeQuery();

			// fetch the present values
			while (resultSet.next()) {

				// Storing the customer id, pin and balance in the table
				int custIdDB = resultSet.getInt("customer_id");
				int custPinDB = resultSet.getInt("pin_number");
				balance = resultSet.getLong("balance");

				// Checking whether the values entered are equal to the values in the table
				if (custId == custIdDB && custPin == custPinDB) {

					// checking whether the amount to pay is less than the account balance
					if (amount < balance) {
						// deducting bank balance
						remaining = balance - amount;

						// Updating the payment table
						sql = "update payment_bt set balance=? where customer_id=?";
						statement = con.prepareStatement(sql);

						// Passing the values to the update statement
						statement.setDouble(1, remaining);
						statement.setInt(2, custId);

						// Executing the update statement
						statement.executeUpdate();

						// Selecting the book details from the book table
						sql = "select * from book_bt where book_id=?";
						statement = con.prepareStatement(sql);

						// Passing the value to the select statement
						statement.setInt(1, onlineBookStoreBean.getBookId());

						// Executing the statement and storing the result set
						ResultSet rs = statement.executeQuery();

						// Getting the details from the result set
						rs.next();

						// Checking whether the no of copies is equal to 1
						if (rs.getInt("NO_OF_COPIES") == 1) {

							statement = con.prepareStatement("delete from book_bt where book_id=?");

							// Passing the value to the delete statement
							statement.setInt(1, onlineBookStoreBean.getBookId());

							// Executing the update statement
							statement.executeUpdate();

							// Committing
							con.commit();
						} else {

							statement = con
									.prepareStatement("update book_bt set no_of_copies=no_of_copies-1 where book_id=?");

							// Passing the value to the update statement
							statement.setInt(1, onlineBookStoreBean.getBookId());

							// Executing the update statement
							statement.executeUpdate();

							// Committing the statement
							con.commit();
						}

						// Returning the message
						return "Payment Successfull";
					} else {

						// Committing the statement
						con.commit();

						// Returning the message
						return "No enough balance";
					}

				}
			}

			// Catching the SQLException
		} catch (Exception e) {

			System.out.println("");
		}

		// Returning the message
		return "Payment Failed";
	}
}
