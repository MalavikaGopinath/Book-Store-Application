package com.demo.onlinebookstore.admin;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 * 
 * Bean class for admin functions
 *
 */
public class AdminLoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;

	// To get Book Details
	private int bookID;
	private String bookName;
	private int price;
	private String pub_date;
	private String author;
	private String language;
	private String category;
	private int noOfCopy;

	// resultSet for admin details
	private ResultSet rs;

	// setting the values from book_bt
	private String bookNameDB;
	private int priceDB;
	private String pubDateDB;
	private String authorDB;
	private String languageDB;
	private String categoryDB;
	private int noCopiesDB;
	private int new_price;
	private int bookIdDB;
	private String adminUserID;
	private String option;

	// To get Admin Details
	private String adminId;
	private String adminPassword;
	private String adminFirstName;
	private String adminLastName;
	private String adminEmail;
	private double adminPhone;
	private ResultSet adminrs;

	// getters and setters for each private variables
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminFirstName() {
		return adminFirstName;
	}

	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	public String getAdminLastName() {
		return adminLastName;
	}

	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public double getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(double adminPhone) {
		this.adminPhone = adminPhone;
	}

	public int getBookIdDB() {
		return bookIdDB;
	}

	public void setBookIdDB(int bookIdDB) {
		this.bookIdDB = bookIdDB;
	}

	public int getNew_price() {
		return new_price;
	}

	public void setNew_price(int new_price) {
		this.new_price = new_price;
	}

	public String getUserName() {
		return userName;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminUserID() {
		return adminUserID;
	}

	public void setAdminUserID(String adminUserID) {
		this.adminUserID = adminUserID;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNoOfCopy() {
		return noOfCopy;
	}

	public void setNoOfCopy(int noOfCopy) {
		this.noOfCopy = noOfCopy;
	}

	public String getBookNameDB() {
		return bookNameDB;
	}

	public void setBookNameDB(String bookNameDB) {
		this.bookNameDB = bookNameDB;
	}

	public int getPriceDB() {
		return priceDB;
	}

	public void setPriceDB(int priceDB) {
		this.priceDB = priceDB;
	}

	public String getPubDateDB() {
		return pubDateDB;
	}

	public void setPubDateDB(String pubDateDB) {
		this.pubDateDB = pubDateDB;
	}

	public String getAuthorDB() {
		return authorDB;
	}

	public void setAuthorDB(String authorDB) {
		this.authorDB = authorDB;
	}

	public String getLanguageDB() {
		return languageDB;
	}

	public void setLanguageDB(String languageDB) {
		this.languageDB = languageDB;
	}

	public String getCategoryDB() {
		return categoryDB;
	}

	public void setCategoryDB(String categoryDB) {
		this.categoryDB = categoryDB;
	}

	public int getNoCopiesDB() {
		return noCopiesDB;
	}

	public void setNoCopiesDB(int noCopiesDB) {
		this.noCopiesDB = noCopiesDB;
	}

	public ResultSet getAdminrs() {
		return adminrs;
	}

	public void setAdminrs(ResultSet adminrs) {
		this.adminrs = adminrs;
	}

}
