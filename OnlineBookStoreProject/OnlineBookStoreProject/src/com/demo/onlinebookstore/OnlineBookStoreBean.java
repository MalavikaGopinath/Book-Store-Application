package com.demo.onlinebookstore;

import java.io.Serializable;

import java.sql.ResultSet;

/**
 * 
 * The Bean class for all the user functions
 *
 */
public class OnlineBookStoreBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// For Admin id and password
	private int adminId;
	private String adminpass;

	// For storing User Details
	private String firstName;
	private String lastName;
	private String email;
	private long phone;
	private String gender;
	private String dob;
	private String userName;
	private String password;
	private String confirmpassword;

	// For storing book details
	private int bookId;
	private String bookName;
	private long price;
	private String pubDate;
	private String author;
	private String language;
	private String category;
	private int copies;

	// For payment details
	private int customerId;
	private int pinNo;
	private String cardNo;
	private double balance;

	// For cart details
	private String cart;
	private int slNo;
	private int cartBookId;
	private String cartBookName;
	private double cartBookPrice;

	// For ResultSet of book table
	private ResultSet bookrs;

	// For ResultSet of user table
	private ResultSet userrs;

	// For ResultSet of cart table
	private ResultSet cartrs;

	// For ResultSet of rent table
	private ResultSet rentrs;

	// Method to get adminId
	public int getAdminId() {
		return adminId;
	}

	// Method to set adminId
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	// Method to get admin password
	public String getAdminpass() {
		return adminpass;
	}

	// Method to set admin password
	public void setAdminpass(String adminpass) {
		this.adminpass = adminpass;
	}

	// Method o get user first name
	public String getFirstName() {
		return firstName;
	}

	// Method to get user last name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Method to get user last name
	public String getLastName() {
		return lastName;
	}

	// Method to set user last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Method to get user Email
	public String getEmail() {
		return email;
	}

	// Method to set user Email
	public void setEmail(String email) {
		this.email = email;
	}

	// Method to get user phone
	public long getPhone() {
		return phone;
	}

	// Method to set user phone
	public void setPhone(long phone) {
		this.phone = phone;
	}

	// Method to get gender
	public String getGender() {
		return gender;
	}

	// Method to set gender
	public void setGender(String gender) {
		this.gender = gender;
	}

	// Method to get user DOB
	public String getDob() {
		return dob;
	}

	// Method to set DOB
	public void setDob(String dob) {
		this.dob = dob;
	}

	// Method to get username
	public String getUserName() {
		return userName;
	}

	// Method to set username
	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Method to get password
	public String getPassword() {
		return password;
	}

	// Method to get confirm password
	public String getConfirmpassword() {
		return confirmpassword;
	}

	// Method to set confirm password
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	// Method to set passowrd
	public void setPassword(String password) {
		this.password = password;
	}

	// Method to get book id of books db
	public int getBookId() {
		return bookId;
	}

	// Method to set book id of books db
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	// Method to get book name of books db
	public String getBookName() {
		return bookName;
	}

	// Method to set book name of books db
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	// Method to get price of books db
	public long getPrice() {
		return price;
	}

	// Method to set price of books db
	public void setPrice(long price) {
		this.price = price;
	}

	// Method to get publishing date of books db
	public String getPubDate() {
		return pubDate;
	}

	// Method to set publishing date of books db
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	// Method to get author of books db
	public String getAuthor() {
		return author;
	}

	// Method to set author of books db
	public void setAuthor(String author) {
		this.author = author;
	}

	// Method to get language of books db
	public String getLanguage() {
		return language;
	}

	// Method to set language of books db
	public void setLanguage(String language) {
		this.language = language;
	}

	// Method to get category of books db
	public String getCategory() {
		return category;
	}

	// Method to set category of books db
	public void setCategory(String category) {
		this.category = category;
	}

	// Method to get copies of books db
	public int getCopies() {
		return copies;
	}

	// Method to set copies of books db
	public void setCopies(int copies) {
		this.copies = copies;
	}

	// Method to get customer id for payment
	public int getCustomerId() {
		return customerId;
	}

	// Method to set customer id for payment
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	// Method to get pin no for payment
	public int getPinNo() {
		return pinNo;
	}

	// Method to set pin no for payment
	public void setPinNo(int pinNo) {
		this.pinNo = pinNo;
	}

	// Method to get card no for payment
	public String getCardNo() {
		return cardNo;
	}

	// Method to set card no for payment
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	// Method to get balance for payment
	public double getBalance() {
		return balance;
	}

	// Method to set balance for payment
	public void setBalance(int balance) {
		this.balance = balance;
	}

	// Method to get cart value
	public String getCart() {
		return cart;
	}

	// Method to set cart value
	public void setCart(String cart) {
		this.cart = cart;
	}

	// Method to get sl no of cart
	public int getSlNo() {
		return slNo;
	}

	// Method to set sl no of cart
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	// Method to get book id of cart
	public int getCartBookId() {
		return cartBookId;
	}

	// Method to set book id of cart
	public void setCartBookId(int cartBookId) {
		this.cartBookId = cartBookId;
	}

	// Method to get book name of cart
	public String getCartBookName() {
		return cartBookName;
	}

	// Method to set book name of cart
	public void setCartBookName(String cartBookName) {
		this.cartBookName = cartBookName;
	}

	// Method to get book price of cart
	public double getCartBookPrice() {
		return cartBookPrice;
	}

	// Method to set book price of cart
	public void setCartBookPrice(int cartBookPrice) {
		this.cartBookPrice = cartBookPrice;
	}

	// Getter for ResultSet
	public ResultSet getBookrs() {
		return bookrs;
	}

	// Setter for ResultSet of book table
	public void setBookrs(ResultSet bookrs) {
		this.bookrs = bookrs;
	}

	// Getter for ResultSet of user table
	public ResultSet getUserrs() {
		return userrs;
	}

	// Setter for ResultSet of user table
	public void setUserrs(ResultSet userrs) {
		this.userrs = userrs;
	}

	// Getter for ResultSet of cart table
	public ResultSet getCartrs() {
		return cartrs;
	}

	// Setter for ResultSet of cart table
	public void setCartrs(ResultSet cartrs) {
		this.cartrs = cartrs;
	}

	// Getter for ResultSet of rent table
	public ResultSet getRentrs() {
		return rentrs;
	}

	// Setter for ResultSet of rent table
	public void setRentrs(ResultSet rentrs) {
		this.rentrs = rentrs;
	}

	// Method to get getSerialversionuid
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
