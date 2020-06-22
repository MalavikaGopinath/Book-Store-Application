<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Sign Up</title>
<!-- 
  This script is used for the validations of blank columns
  -->
<script>
	function validate()//pop up message
	{
		var adminid = document.form.adminid.value;
		var adminpassword = document.form.adminpassword.value;
		var adminfirstname = document.form.adminfirstname.value;
		var adminlastname = document.form.adminlastname.value;
		var adminemail = document.form.adminemail.value;
		var adminphone = document.form.adminphone.value;

		if (adminid == null || adminid == "") {
			alert("Admin Id cannot be blank ");
			return false;
		} else if (adminpassword == null || adminpassword == "") {
			alert("Password cannot be blank ");
			return false;
		} else if (adminfirstname == null || adminfirstname == "") {
			alert("First Name Can not be blank ");
			return false;
		} else if (adminlastname == null || adminlastname == "") {
			alert(" Last name cannot be blank ");
			return false;
		} else if (adminemail == null || adminemail == "") {
			alert("Email cannot be blank ");
			return false;
		} else if (adminphone == null || adminphone == "") {
			alert("Phone number cannot be blank ");
			return false;
		}
	}
</script>

</head>
<body background="btadmininsertion.jpg">
	<div style="text-align: right">
		<a href="admin.jsp" style="color: blue">Back</a>
	</div>
	<form name="form" action="AdminSignUpController" method="post"
		onsubmit="return validate()">


		<div class="head1">
			<h1 align="center">Admin Sign Up</h1>
		</div>
		<div align="center">
			<table style="display: inline-block" cellspacing="20">
				<tr>
					<td><b>Admin Id[eg:AI123] </b> :</td>
					<td><input type="text" name="adminid" placeholder="eg:AI123" /></td>
				</tr>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("err") == null ? "" : request.getAttribute("err"))%></span>
				</tr>
				<tr>
					<td><b>Password</b> :</td>
					<td><input type="password" name="adminpassword" /></td>
				</tr>

				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("Message") == null ? "" : request.getAttribute("Message"))%></span>
				</tr>
				<tr>
					<td><b> First Name [eg:Raj]</b>:</td>
					<td><input type="text" name="adminfirstname"
						placeholder=eg:Raj /></td>
				</tr>
				<tr>
					<td><b> Last Name[eg:Mohann]</b> :</td>
					<td><input type="text" name="adminlastname"
						placeholder="eg:Mohan" /></td>
				</tr>

				<tr>
					<td><b>Email ID [eg:Raj@gmail.com]</b> :</td>
					<td><input type="text" name="adminemail"
						placeholder="eg:Raj@gmail.com" /></td>
				</tr>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("error") == null ? "" : request.getAttribute("error"))%></span>
				</tr>
				
				<tr>
					<td><b>Phone Number</b>:</td>
					<td><input type="text" name="adminphone" placeholder="Number" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Register" /></td>
				</tr>

				<tr>
					<td colspan="2"><span style="color: green"><%=(request.getAttribute("errMessage") == null ? "" : request.getAttribute("errMessage"))%></span>
				</tr>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("err1") == null ? "" : request.getAttribute("err1"))%></span>
				</tr>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("err2") == null ? "" : request.getAttribute("err2"))%></span>
				</tr>
				<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("err3") == null ? "" : request.getAttribute("err3"))%></span>
				</tr>

			</table>
		</div>
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>