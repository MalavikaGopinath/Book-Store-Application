<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Sign Up</title>
<link rel='stylesheet' href='registration.css' type='text/css'>
<script>
	function validate() {
		var firstname = document.form.firstname.value;
		var lastname = document.form.lastname.value;
		var userEmail = document.form.userEmail.value;
		var phone = document.form.phone.value;
		var gender = document.form.gender.value;
		var dob = document.form.gender.value;
		var username = document.form.username.value;
		var password = document.form.password.value;
		var confirmpassword = document.form.confirmpassword.value;

		if (firstname == null || firstname == "") {
			alert("First Name cannot be blank");
			return false;
		} else if (lastname == null || lastname == "") {
			alert("Last Name cannot be blank");
			return false;
		} else if (userEmail == null || userEmail == "") {
			alert("Enter email id");
			return false;
		} else if (phone == null || phone == "") {
			alert("Enter Phone number");
			return false;
		} else if (username == null || username == "") {
			alert("Username cannot be blank");
			return false;
		} else if (password == null || password == "") {
			alert("Password cannot be blank");
			return false;
		} else if (dob == null || dob == "") {
			alert("Enter the Date of Birth");
			return false;
		}
	}
</script>
</head>
<body>
	<center>
		<h1 style="color: darkslategrey">Online Book Store</h1>
	</center>
	<h2 align="center">Create New Account</h2>
	<form align="center" name="form" action="UserSignUpController"
		method="post" onsubmit="return validate()">
		<center>
			<table align="center">
				<tr>
					<td><b>First Name </b></td>
					<td>:</td>
					<td><input type="text" name="firstname"
						placeholder="First Name" /></td>
				</tr>
				<br>
				<br>
				<tr>
					<td><b>Last Name </b></td>
					<td>:</td>
					<td><input type="text" name="lastname" placeholder="Last Name" /></td>
				</tr>
				<br>
				<br>
				<tr>
					<td><b>Email ID </b></td>
					<td>:</td>
					<td><input type="text" name="userEmail" placeholder="Email ID" /></td>
				</tr>
				<br>
				<br>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("error") == null ? "" : request.getAttribute("error"))%></span>
				</tr>
				<tr>
					<td><b>Phone Number </b></td>
					<td>:</td>
					<td><input type="text" name="phone" placeholder="Phone" /></td>
				</tr>
				<br>
				<br>
				<tr>
					<td><b> Gender</b></td>
					<td>:</td>
					<td><select name="gender">
							<option>Male</option>
							<option>Female</option>
							<option>Others</option>
					</select></td>
				</tr>
				<br>
				<br>
				<tr>
					<td><b> Username </b></td>
					<td>:</td>
					<td><input type="text" name="username" placeholder="User Name" /></td>
				</tr>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("fail") == null ? "" : request.getAttribute("fail"))%></span></td>
				</tr>
				<br>
				<br>
				<tr>
					<td><b>Password </b></td>
					<td>:</td>
					<td><input type="password" name="password" placeholder="Password" /></td>
				</tr>
				<br>
				<br>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("Message") == null ? "" : request.getAttribute("Message"))%></span></td>
				</tr>

				<tr>
					<td><b> Date Of Birth(mm-dd-yyyy) </b></td>
					<td>:</td>
					<td><input type="date" id="start" name="dob"
						placeholder="dd-mm-yyyy"></td>
				</tr>
				<br>
				<br>

				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("notvalid") == null ? "" : request.getAttribute("notvalid"))%></span></td>
				</tr>

				<span style="color: red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
			</table>
			<input type="submit" value="Register" /><br>
	</form>

	</center>

	<%@ include file="footer.jsp"%>
</body>
</html>