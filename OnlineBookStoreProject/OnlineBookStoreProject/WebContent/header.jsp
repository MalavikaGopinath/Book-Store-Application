<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Online Book Store</title>
<link rel='stylesheet' href='onlineBookStore.css' type='text/css'>
</head>
<body>
	<center>
		<h1>Online Book Store</h1>
	</center>
	<div style="text-align:left">
	</div>
	<span style="color: brown"><h2><%= (session.getAttribute("user")==null)
				?"" : session.getAttribute("user")%></h2></span>	
	<div style="text-align: right">
		<a href="LoginController" style="color:crimson">Home</a>&nbsp;&nbsp;
			<a href="CartController" style="color:crimson">Cart</a>&nbsp;&nbsp;
			<a href="MyAccountController" style="color: crimson">My Account</a>&nbsp;&nbsp; 
			<a href="LogoutController" style="color:crimson">Logout</a>&nbsp;&nbsp;
		</div>
</body>
</html>