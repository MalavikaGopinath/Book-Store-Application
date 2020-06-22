package com.demo.onlinebookstore.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.demo.onlinebookstore.DBConnection;

/**
 * 
 * Admin's all functions will be carried out by in this class
 *
 */
public class AdminDAO {
	static Connection con = null;
	static PreparedStatement statement = null;
	static ResultSet resultSet = null;
	static String sql;

	// The method used to validate Admin UserId and Password
	public static String authenticateAdmin(AdminLoginBean adminLoginBean) {
		// Getting user id and password using
		// AdminLoginBean Class reference
		String userId = adminLoginBean.getAdminUserID();
		String password = adminLoginBean.getAdminPassword();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String adminIdDB = "";
		String passwordDB = "";
		
		try {
			con = DBConnection.createConnection();
			statement = con.createStatement();
			// Execute the Query
			resultSet = statement.executeQuery("select admin_id,password from admin_bt ");

			while (resultSet.next()) { // Taking the values from data base
				adminIdDB = resultSet.getString(1);
				passwordDB = resultSet.getString(2);
				// Comparing user id and password with userid and password from database
				if (userId.equals(adminIdDB) && password.equals(passwordDB)) {
					return "success";
				}
			}
		} catch (SQLException es) {
			es.printStackTrace();
		}
		return "invalid user credential..";
		// The method return String value
	}

	// method for inserting book in to the book table
	public String addBook(AdminLoginBean adminLoginBean) {

		// getting the values using AdminLoginBean refference(loginBean)
		int bookid = adminLoginBean.getBookID();
		String bookname = adminLoginBean.getBookName();
		int price = adminLoginBean.getPrice();
		String pubdate = adminLoginBean.getPub_date();
		String category = adminLoginBean.getCategory();
		String language = adminLoginBean.getLanguage();
		String author = adminLoginBean.getAuthor();
		int nocopies = adminLoginBean.getNoOfCopy();
		String option = adminLoginBean.getOption();
		String state = "";

		try {
			con = DBConnection.createConnection();
			sql = "select book_id,book_name,no_of_copies from book_bt where book_id=?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, bookid);
			resultSet = statement.executeQuery();

			if (resultSet.next()) { // checking book id and book name are same as that of the values from data base
				if (resultSet.getInt(1) == bookid && resultSet.getString(2).equals(bookname)) {
					// Updating the No of
					// copies in data base
					nocopies = (resultSet.getInt(3) + nocopies);
					sql = "update book_bt set no_of_copies=? where book_id=?";
					statement = con.prepareStatement(sql);
					statement.setInt(1, nocopies);
					statement.setInt(2, bookid);
					statement.executeUpdate();
					con.commit();
					state = "Succeessfully Updated...";
				} else {
					state = "Mismatch in parameter.... ";
				}
			} else {
				// Inserting values in to the book table
				sql = "insert into book_bt values(?,?,?,?,?,?,?,?,?)";

				statement = con.prepareStatement(sql);
				statement.setInt(1, bookid);
				statement.setString(2, bookname);
				statement.setInt(3, price);
				statement.setString(4, pubdate);
				statement.setString(5, author);
				statement.setString(6, language);
				statement.setString(7, category);
				statement.setInt(8, nocopies);
				statement.setString(9, option);
				statement.executeUpdate();
				con.commit();
				// Check the values is successfully inserted
				sql = "select book_id from book_bt";
				statement = con.prepareStatement(sql);
				resultSet = statement.executeQuery();

				state = "fails to insert";
				while (resultSet.next()) {
					if (resultSet.getInt(1) == bookid) {
						
						state = "success";
					}

				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return the String value inside the state
		return state;

	}

	// To print the book details inside the jsp page
	public static AdminLoginBean enterBookDetails(AdminLoginBean adminLoginBean) {
		int bookIdDB = 0;
		String bookNameDB = "";
		int priceDB = 0;
		String pubDateDB = "";
		String authorDB = "";
		String languageDB = "";
		String categoryDB = "";
		int noCopiesDB = 0;
		int bookid = adminLoginBean.getBookID();

		try {
			// selecting the details from boo table corresponding to book id
			con = DBConnection.createConnection();
			sql = "select * from book_bt where book_id=?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, bookid);
			resultSet = statement.executeQuery();

			// loginBean.setRs(resultSet);
			// setting the database value into the variables
			resultSet.next();
			bookIdDB = resultSet.getInt(1);
			bookNameDB = resultSet.getString(2);
			priceDB = resultSet.getInt(3);
			pubDateDB = resultSet.getString(4);
			authorDB = resultSet.getString(5);
			languageDB = resultSet.getString(6);
			categoryDB = resultSet.getString(7);
			noCopiesDB = resultSet.getInt(8);
			// setting the loginBean Value
			adminLoginBean.setBookIdDB(bookIdDB);
			adminLoginBean.setBookNameDB(bookNameDB);
			adminLoginBean.setAuthorDB(authorDB);
			adminLoginBean.setCategoryDB(categoryDB);
			adminLoginBean.setLanguageDB(languageDB);
			adminLoginBean.setPriceDB(priceDB);
			adminLoginBean.setPubDateDB(pubDateDB);
			adminLoginBean.setNoCopiesDB(noCopiesDB);

			return adminLoginBean;

		} catch (SQLException se) {
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// To modify the price of the Book
	public static String modifyPrice(AdminLoginBean adminLoginBean) {
		String state = null;

		try {
			con = DBConnection.createConnection();
			// Storing the values into variables
			int bookid = adminLoginBean.getBookID();
			int new_price = adminLoginBean.getNew_price();

			// Precompile the sql quarry
			sql = "update book_bt set price=? where book_id=?";
			statement = con.prepareStatement(sql);

			statement.setInt(1, new_price);
			statement.setInt(2, bookid);
			// updating the table with new price
			statement.executeUpdate();
		
			con.commit();

			// check the the table is successfully updated
			sql = "select book_id,price from book_bt";
			statement = con.prepareStatement(sql);
			resultSet = statement.executeQuery();

			state = "Updation failed.....";

			while (resultSet.next()) {
				if (resultSet.getInt(1) == bookid && resultSet.getInt(2) == new_price) {
					
					// return success value
					state = "success";
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}

	// method to delete a book from database
	public static String deleteBook(AdminLoginBean adminLoginBean) {
		String state = null;

		try {
			con = DBConnection.createConnection();
			// getting the value form the AdminLoginBeanClass
			int bookid = adminLoginBean.getBookID();

			// Sql Quarry for deleting the book from Book table
			sql = "delete from book_bt where book_id=?";
			statement = con.prepareStatement(sql);

			statement.setInt(1, bookid);
			statement.executeUpdate();
			con.commit();

			// check book is successfully deleted
			sql = "select book_id from book_bt";
			statement = con.prepareStatement(sql);
			resultSet = statement.executeQuery();

			state = "success";
			while (resultSet.next()) {
				if (resultSet.getInt(1) == bookid) {
					
					state = "Deletion Faild.....";
				}

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;

	}

	// method to insert a new admin to admin table
	public String adminSignUp(AdminLoginBean adminLoginBean) {
		String state = null;
		try {
			// getting the values from AdminLoginBean class
			con = DBConnection.createConnection();
			String adminId = adminLoginBean.getAdminId();
			String adminPassword = adminLoginBean.getAdminPassword();
			String adminFirstName = adminLoginBean.getAdminFirstName();
			String adminLastName = adminLoginBean.getAdminLastName();
			String adminEmail = adminLoginBean.getAdminEmail();
			double adminPhone = adminLoginBean.getAdminPhone();

			// inserting Admin details to admin_bt
			sql = "insert into admin_bt values(?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);

			// setting the values in to corresponding "?"
			statement.setString(1, adminId);
			statement.setString(2, adminPassword);
			statement.setString(3, adminFirstName);
			statement.setString(4, adminLastName);
			statement.setString(5, adminEmail);
			statement.setDouble(6, adminPhone);

			statement.executeUpdate();
			con.commit();

			// check the Admin details successfully inserted
			sql = "select admin_id from admin_bt";
			statement = con.prepareStatement(sql);
			resultSet = statement.executeQuery();

			state = "Admin Registration faild.....";
			while (resultSet.next()) {
				if (resultSet.getString(1).equals(adminId)) {
					
					state = "success";
				}

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return state;
	}

	// to get admin details
	public static void adminDetails(AdminLoginBean adminLoginBean) {
		try {
			// calling the createConnection() of DBConnection class for getting the
			// connection
			con = DBConnection.createConnection();
			// selecting the admin details from admin_bt
			statement = con.prepareStatement("select * from admin_bt");
			resultSet = statement.executeQuery();

			// setting the resultset into the AdminLoginBean Class
			adminLoginBean.setAdminrs(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
