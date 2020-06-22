package com.demo.onlinebookstore.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.onlinebookstore.DBConnection;
import com.demo.onlinebookstore.OnlineBookStoreBean;

/**
 * 
 * this UserDaAO class is used for storing the details of new user to the data
 * base and also for retrieving the user details from the user table
 *
 */
public class UserDAO {

	// Variables for the Database related functions
	static Connection con = null;
	static PreparedStatement statement = null;
	static ResultSet rs = null;
	static String sql;

	/**
	 * 
	 * This setUserDetails() is used for storing the details of the user into the
	 * user table
	 */
	public static String setUserDetails(OnlineBookStoreBean onlineBookStoreBean) {

		// Getting the user details from the OnlineBookStoreBean
		String firstName = onlineBookStoreBean.getFirstName();
		String lastName = onlineBookStoreBean.getLastName();
		String email = onlineBookStoreBean.getEmail();
		long phone = onlineBookStoreBean.getPhone();
		String gender = onlineBookStoreBean.getGender();
		String dob = onlineBookStoreBean.getDob();
		String userName = onlineBookStoreBean.getUserName();
		String password = onlineBookStoreBean.getPassword();

		try {
			
			// Getting the connection
			con = DBConnection.createConnection();
			
			// Storing the sql statement
			sql = "select * from user_bt";
			statement = con.prepareStatement(sql);
			
			// executing the select statement and storing in the result set
			rs = statement.executeQuery(sql);
			
			// Iterating the result set
			while (rs.next()) {
				
				// checking whether there is a user already with same user name
				if (rs.getString(7).equals(userName)) {
					
					// Returning the message
					return "Username already exits, try a new user name";
				}
			}

			// Inserting the user details to user table
			sql = "insert into user_bt values (?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);

			// Passing the values for the insert statement
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setLong(4, phone);
			statement.setString(5, gender);
			statement.setString(6, dob);
			statement.setString(7, userName);
			statement.setString(8, password);

			// Executing the update statement
			statement.executeUpdate();
			
			// Closing the connection
			con.commit();

			// Selecting the users in the user table
			sql = "select * from user_bt";
			statement = con.prepareStatement(sql);
			
			// Executing the select statement and storing the result set
			rs = statement.executeQuery(sql);

			// Iterating the result set
			while (rs.next()) {
				
				// Checking whether the new value is in the user table
				if (rs.getString(7).equals(userName)) {

					// returning the message
					return "success";
				}
			}
			
			// Catching the SQLException
		} catch (SQLException e) {
			
			// helps to trace the exception
			e.printStackTrace();
		}

		return "fail";
	}// end of setUserDetails()

	/**
	 * 
	 * This getUserDetails() is for getting the details from the from the table
	 */
	public static void getUserDetails(OnlineBookStoreBean onlineBookStoreBean) {
		
		// Result set variable
		ResultSet resultset = null;

		// Getting the user name from the OnlineBookStoreBean
		String username = onlineBookStoreBean.getUserName();

		try {
			
			// Getting the connection
			con = DBConnection.createConnection();
			
			// Selecting the details of the current user
			statement = con.prepareStatement("select * from user_bt where user_name=?");

			// Passing values for the select statement
			statement.setString(1, username);
			
			// Executing the select query and storing the result set
			resultset = statement.executeQuery();
			
			// Setting the result set to the OnlineBookStoreBean
			onlineBookStoreBean.setUserrs(resultset);

			// Catching the SQLExcepion
		} catch (SQLException e) {
			
			// Helps to trace the exception
			e.printStackTrace();
		}
	} // end of getUserDetails()
}
