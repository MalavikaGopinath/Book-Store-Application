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
		<h2>Admin Page</h2>
	</center>
	<div style="text-align: left"></div>
	<span style="color: orange"><h3><%=(session.getAttribute("adminid") == null) ? "" : session.getAttribute("adminid")%></h3></span>
	<div style="text-align: right">
		<a href="adminProperties.jsp" style="color: crimson">Home</a>&nbsp;&nbsp;
		<a href="admin.jsp" style="color: crimson">Logout</a>
	</div>
</body>
</html>