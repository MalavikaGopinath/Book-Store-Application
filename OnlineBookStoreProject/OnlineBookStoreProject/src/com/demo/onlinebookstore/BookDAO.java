package com.demo.onlinebookstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * This BookDAO class is used to connect to the database and retrieve the book
 * details from the book_bt table
 *
 */
public class BookDAO {

	// Connection variable
	static Connection con = null;

	// PreparedStatement variable
	static PreparedStatement statement = null;

	// ResultSet variable
	static ResultSet resultSet = null;

	/*
	 * This bookList() has the object of OnlineBookStoreBean as parameter. It
	 * retrieves the books and its details from the book_bt table.
	 */
	public static void bookList(OnlineBookStoreBean onlineBookStoreBean) {

		try {
			// calling the createConnection() of DBConnection class for getting the
			// connection
			con = DBConnection.createConnection();

			// Creates a PreparedStatement object for sending for sending parameterized SQL
			// statement to the database
			statement = con.prepareStatement("select * from book_bt order by book_id");

			// Executes the given SQL statement, which returns a single ResultSet object
			resultSet = statement.executeQuery();

			// setting the ResultSet to onlineBookStoreBean
			onlineBookStoreBean.setBookrs(resultSet);

			// The below catch is used to catch the SQL Exception that can occur in this try
			// block
		} catch (SQLException e) {

			// Helps to trace the exception
			e.printStackTrace();
		}
	}

}
