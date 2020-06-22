package com.demo.onlinebookstore;

import com.demo.onlinebookstore.admin.AdminLoginBean;

/**
 * Class for validating the email and password while registering
 */
public class Validation implements ValidationInterface {

	/**
	 * 
	 * This passValidation(AdminLoginBean adminLoginBean) method is used for
	 * validating the password while registering the admin
	 */
	public String passValidation(AdminLoginBean adminLoginBean) {

		// For getting the admin password from the AdminLoginBean
		String password = adminLoginBean.getAdminPassword();

		// Storing the message as invalid in the value variable for password initially
		String value = "invalid";

		// Checking whether the password length is greater than or equal to 8 and less
		// than 16
		if ((password.length() >= 8) && (password.length() <= 16)) {

			// The conditions are satisfied and the message is overwritten as valid in the
			// value variable
			value = "valid";
		}

		// Returning the value to the caller
		return value;

	}

	/**
	 * 
	 * This emailValidation(AdminLoginBean adminLoginBean) method is used for
	 * validating the email id while registering the admin
	 */
	public String emailValidation(AdminLoginBean adminLoginBean) {

		// For getting the admin email id from the AdminLoginBean
		String email = adminLoginBean.getAdminEmail();

		// Storing the message as invalid in the value variable for email initially
		String value = "invalid";

		// Checking whether the email id has the '@' and '.' symbols
		if (email.matches("^(.+)@(.+).(.+)$")) {

			// The conditions are satisfied and the message is overwritten as valid in the
			// value variable
			value = "valid";
		}

		// Returning the value to the caller
		return value;

	}

	/**
	 * 
	 * This idValidation(AdminLoginBean adminLoginBean) method is used for
	 * validating the admin id while registering the admin
	 */
	public String idValidation(AdminLoginBean adminLoginBean) {

		// For getting the admin id from the AdminLoginBean
		String id = adminLoginBean.getAdminId();

		// Storing the message as invalid in the value variable for admin id initially
		String value = "invalid";

		// Checking whether the admin id length is less than or equal to 5
		if (id.length() <= 5) {

			// The conditions are satisfied and the message is overwritten as valid in the
			// value variable
			value = "valid";
		}

		// Returning the value to the caller
		return value;
	}

	/**
	 * 
	 * This userPasswordValidation(OnlineBookStoreBean onlineBookStoreBean) method
	 * is used for validating the password while registering the user
	 */
	public String userPasswordValidation(OnlineBookStoreBean onlineBookStoreBean) {

		// For getting the user password from the OnlineBookStoreBean
		String password = onlineBookStoreBean.getPassword();

		// Storing the message as invalid in the value variable for user password
		// initially
		String value = "invalid";

		// Checking whether the password length is greater than or equal to 8 and less
		// than 16
		if ((password.length() >= 8) && (password.length() <= 16)) {

			// The conditions are satisfied and the message is overwritten as valid in the
			// value variable
			value = "valid";
		}

		// Returning the value to the caller
		return value;
	}

	/**
	 * 
	 * This userEmailValidation(OnlineBookStoreBean onlineBookStoreBean) method is
	 * used for validating the email id while registering the user
	 */
	public String userEmailValidation(OnlineBookStoreBean onlineBookStoreBean) {

		// For getting the user email id from the AdminLoginBean
		String email = onlineBookStoreBean.getEmail();

		// Storing the message as invalid in the value variable for email initially
		String value = "invalid";

		// Checking whether the email id has the '@' and '.' symbols
		if (email.matches("^(.+)@(.+).(.+)$")) {

			// The conditions are satisfied and the message is overwritten as valid in the
			// value variable
			value = "valid";
		}

		// Returning the value to the caller
		return value;

	}
}
