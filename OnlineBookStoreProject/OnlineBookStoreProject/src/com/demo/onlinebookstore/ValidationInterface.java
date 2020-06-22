package com.demo.onlinebookstore;

import com.demo.onlinebookstore.admin.AdminLoginBean;

/**
 * 
 * This Interface has five validation functions for validating the email and
 * password of admin and user and the admin id.
 *
 */
public interface ValidationInterface {

	// Method declaration for admin password validation
	public String passValidation(AdminLoginBean adminLoginBean);

	// Method declaration for admin email id validation
	public String emailValidation(AdminLoginBean adminLoginBean);

	// Method declaration for admin id validation
	public String idValidation(AdminLoginBean adminLoginBean);

	// Method declaration for user email validation
	public String userEmailValidation(OnlineBookStoreBean onlineBookStoreBean);

	// Method declaration for user password validation
	public String userPasswordValidation(OnlineBookStoreBean onlineBookStoreBean);
}
