package com.demo.onlinebookstore.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.demo.onlinebookstore.DBConnection;
import com.demo.onlinebookstore.OnlineBookStoreBean;

/**
 * 
 * Login functions of the user such as authenticating a user and the method for
 * changing the password of a user are carried out in this class.
 *
 */
public class LoginDAO {

	// Variables for the Database related functions
	static Connection con = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	/**
	 * 
	 * This authenticateUser() is used for checking whether this is a valid user
	 */
	public static String authenticateUser(OnlineBookStoreBean onlineBookStoreBean) {

		// Getting the username and password from the OnlineBookStoreBean
		String userName = onlineBookStoreBean.getUserName();
		String password = onlineBookStoreBean.getPassword();

		// For storing the database values
		String userNameDB = "";
		String passwordDB = "";

		try {
			// Getting the connection
			con = DBConnection.createConnection();

			// For selecting the user name and password from user table
			statement = con.createStatement();
			resultSet = statement.executeQuery("select user_name,password from user_bt");

			// Iterating through the result set
			while (resultSet.next()) {

				// Storing the result set values
				userNameDB = resultSet.getString("user_name");
				passwordDB = resultSet.getString("password");

				// Checking whether the user sname and password matches
				if (userName.equals(userNameDB) && password.equals(passwordDB)) {

					// Returning the message
					return "SUCCESS";
				}
			}

			// For catching the SQLException
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();
		}

		// Returning the message
		return "Invalid user credentials";
	}

	/**
	 * 
	 * This forgotpassword() is used to change the password of a user using his/her
	 * email id.
	 */
	public static String forgotpassword(OnlineBookStoreBean onlineBookStoreBean) {

		// keeping user entered values from the OnlineBookStoreBean
		String emailidDB = onlineBookStoreBean.getEmail();
		String password = onlineBookStoreBean.getPassword();
		String cnpassword = onlineBookStoreBean.getConfirmpassword();

		// Getting the connection
		Connection con = DBConnection.createConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String state = "Invalid Email Id...";
		try {

			// Selecting the user details of the respective email id
			statement = con.prepareStatement("select * from user_bt where email_id=?");

			// Passing the value for the select statement
			statement.setString(1, emailidDB);

			// Executimg the query and storing it in the result set
			resultSet = statement.executeQuery();

			// Getting the details from the result set
			resultSet.next();

			// Checking whether the email id is in the table
			if (resultSet.getString(3).equals(emailidDB)) {

				// Checking whether the password and confirm password matches
				if (password.equals(cnpassword)) {

					try {

						// Updating the password
						statement = con.prepareStatement("update user_bt set password = ? where email_id = ?");

						// Passing the values for the update statement
						statement.setString(1, password);
						statement.setString(2, emailidDB);

						// Executimg the update statement
						statement.executeUpdate();

						// Committing
						con.commit();

						// For catching the SQL Exception
					} catch (SQLException e) {

						// Helps to trace the exception
						e.printStackTrace();
					}

					// storing the message to be returned
					state = "Password Changed..";

				} else {

					// Just returning appropriate message
					state = "passwords not matching";
				}
			}

			// For catching the SQL Exception
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();

			// For catching the Exceptions if any occurs
		} catch (Exception e) {

			// Helps to trace the exception
			e.printStackTrace();

		}

		// Returning the message
		return state;
	}
}
