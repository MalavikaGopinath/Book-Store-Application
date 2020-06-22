<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Account</title>
<link rel='stylesheet' href='onlineBookStore.css' type='text/css'>
</head>
<body background="obs.jpg">
	<%@ include file="header.jsp"%>
	<h2>My Account</h2>

	<!-- The below java code is embedded to get the user details from the result set and showing it in the user profile -->
	<center>
		<table>
			<%
				ResultSet rs1 = (ResultSet) session.getAttribute("userrs");
				while (rs1.next()) {
			%>
			<tr>
				<td><b>First Name</b></td>
				<td>:</td>
				<td><%=rs1.getString(1)%></td>
			</tr>
			<br />
			<tr>
				<td><b>Last Name</b></td>
				<td>:</td>
				<td><%=rs1.getString(2)%></td>
			</tr>
			<br />
			<tr>
				<td><b>Email ID</b></td>
				<td>:</td>
				<td><%=rs1.getString(3)%></td>
			</tr>
			<br />
			<tr>
				<td><b>Phone</b></td>
				<td>:</td>
				<td><%=rs1.getLong(4)%></td>
			</tr>
			<br />
			<tr>
				<td><b>Gender</b></td>
				<td>:</td>
				<td><%=rs1.getString(5)%></td>
			</tr>
			<br />
			<tr>
				<td><b>Date Of Birth</b></td>
				<td>:</td>
				<td><%=rs1.getString(6)%></td>
			</tr>
			<br />
			<tr>
				<td><b>User Name</b></td>
				<td>:</td>
				<td><%=rs1.getString(7)%></td>
			</tr>
			<br />
			<%
				}
			%>
		</table>
		<br />
		<br />
		<!-- This java code is embedded to show the book,it's rent date and return date which has taken for rent by user if any  -->
		<%
			ResultSet rs2 = (ResultSet) session.getAttribute("rentrs");
			while (rs2.next()) {
		%>
		<table>
			<tr>
				<td><b>Book ID</b></td>
				<td></td>
				<td></td>
				<td><b>Book Name</b></td>
				<td></td>
				<td></td>
				<td><b>Rent Date</b></td>
				<td></td>
				<td></td>
				<td><b>Return Date</b></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><%=rs2.getInt(2)%></td>
				<td></td>
				<td></td>
				<td><%=rs2.getString(3)%></td>
				<td></td>
				<td></td>
				<td><%=rs2.getString(4)%></td>
				<td></td>
				<td></td>
				<td><%=rs2.getString(5)%></td>
				<td></td>
				<td></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<br />
	<br />
	<br />
	<br />
	<%@ include file="footer.jsp"%>
</body>
</html>