<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>book train admin</title>

<!-- 
  This script is used for the validations of blank columns
  -->
<script>
	function validate() {
		var adminId = document.form.adminId.value;
		var adminpass = document.form.adminpass.value;
		
		if (adminId == null || adminId == ""){
			alert("adminId cannot be blank");
			return false;
		} else if (adminpass == null || adminpass == ""){
			alert("adminpass cannot be blank");
			return false;
		}
	}
</script>
</head>

<head>
        <title>book train admin</title>
         <div style="text-align: left">
		<a href="login.jsp" style="color:white">User Login</a>&nbsp;&nbsp;
		</div>
        <link href='ref.css' rel='stylesheet' type='text/css'>
    </head>

 <body background="btadmin.jpg" style="background-size: cover">
        <div class="login-box">
        <img src="bt4login.jpg" class="book">
            <h1>Admin Login</h1>
           
            <form name="form" action="AdminLoginController" method="post" onsubmit="return validate()">
                <p style="color: azure">AdminId</p>
                <input type="text" name="adminId" placeholder="Enter AdminId">
                <p style="color: azure">password</p>
                <input type="password" name="adminpass" placeholder="Enter Password">
                <input type="submit" name="submit" value="Login"> 
                </form>
                <form action="adminSignUp.jsp"><input type="submit" name="submit" value="Signup"/></form>
                      <td colspan="2"><span style="color:red"><%=(request.getAttribute("errmessage")==null
	?"":request.getAttribute("errmessage")) %></span>
	</tr>            
        </div>
  
    </body>
   </html>