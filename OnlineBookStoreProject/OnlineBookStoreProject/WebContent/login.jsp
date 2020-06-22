
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
<script>
	/**
	 * This script is used for the validations of blank columns
	 */
	function validate() {
		var username = document.form.username.value;
		var password = document.form.password.value;

		if (username == null || username == "") {
			alert("Username cannot be blank");
			return false;
		} else if (password == null || password == "") {
			alert("Password cannot be blank");
			return false;
		}
	}
</script>
</head>
<head>
<title>User login</title>
</head>
<link rel="stylesheet" type="text/css" href="ref.css">
<body background="bt3.jpg" style="background-size: cover">
	<a href="admin.jsp" style="color: white">Admin Login</a>
	<div class="login-box">
		<img src="book.jpg" class="book">
		<h1>Login Here</h1>
		<form name="form" action="LoginController" method="post"
			onsubmit="return validate()">
			<p style="color: azure">Username</p>
			<input type="text" name="username" placeholder="Enter Username">
			<p style="color: azure">Password</p>
			<input type="password" name="password" placeholder="Enter Password">
			<input type="submit" name="submit" value="Login"> <span
				style="color: red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
			<a href="forgotPassword.jsp">Forgot Password</a>
		</form>
		<span style="color: yellow"><%=(request.getAttribute("logMessage") == null) ? "" : request.getAttribute("logMessage")%></span>
		<span style="color: yellow"><%=(request.getAttribute("notvalid") == null) ? "" : request.getAttribute("notvalid")%></span>
		<form action="userSignUp.jsp">
			<input type="submit" name="submit" value="Signup">
		</form>
	</div>
</body>
</html>