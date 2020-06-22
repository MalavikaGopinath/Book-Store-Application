package com.demo.onlinebookstore;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * Class for registering the driver and establishing the connection to the
 * database
 *
 */
public class DBConnection {

	/**
	 * 
	 * This createConnection() is to register and get connection to the database
	 */
	public static Connection createConnection() {

		// Connection variable
		Connection con = null;

		// The connection URL for the oracle10G database
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		// The username for the oracle database
		String username = "hr";

		// The password given by the user at the time of installing the oracle database
		String password = "pass";

		try {
			try {
				// "oracle.jdbc.driver.OracleDriver" is the driver class for the oracle database
				// load the Driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// The ClassNotFoundException is catched here
			} catch (ClassNotFoundException e) {

				// Helps to trace the exception
				e.printStackTrace();
			}

			// create the connection object
			con = DriverManager.getConnection(url, username, password);

			// The exception for catching the exception if any occurs
		} catch (Exception e) {

			// Helps to trace the exception
			e.printStackTrace();
		}

		// Returning the connection to the caller
		return con;

	}
}
