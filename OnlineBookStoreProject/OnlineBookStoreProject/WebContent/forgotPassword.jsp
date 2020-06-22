<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function validate() {
		var emailid = document.form.emailid.value;
		var password = document.form.password.value;		
		if (emailid == null || emailid == ""){
			alert("emailid cannot be blank");
			return false;
		} else if (password == null || password == ""){
			alert("Password cannot be blank");
			return false;
		}
	}
</script>
</head>
<link rel="stylesheet"type="text/css" href="ref.css">
<body>
<form name="form" action="ForgotPasswordController" method="post" onsubmit="return validate()">
<div class="login-box">
 <input type="text" name="emailid" placeholder="Enter email-Id">
 <input type="password" name="password" placeholder="New Password">
 <input type="password" name="confirmpassword" placeholder="Confirm Password">
 <input type="submit" name="submit" value="Confirm">
  <table>
 <tr>
     <td colspan=""><span style="color:red"><%=(request.getAttribute("errMessage")==null
     ?"":request.getAttribute("errMessage")) %></span>
     </tr>
 </table>
 </form>
 <form action="login.jsp"><input type="submit" name="submit" value="Cancel"></form>
 </div>
 
</body>
</html>