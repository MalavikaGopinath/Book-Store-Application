<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' href='com.demo.onlineBookStore.css'
	type='text/css'>
</head>
<body background="obs.jpg">
	<%@ include file="header.jsp"%>
	<h2>My Cart</h2>

	<!--The below code is used for fetching the books in the cart from the cart table and displaying it in the screen -->
	<table align="center" class="DbTable" border="1">
		<tr>
			<td><b>Book ID</b></td>
			<td><b>Book Name</b></td>
			<td><b>Price (in Rs.)</b></td>
			<td><b>Quantity</b></td>
		</tr>
		<%
			ResultSet rs = (ResultSet) session.getAttribute("cartrs");
			long sum = 0;
			while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getInt(2)%></td>
			<td><%=rs.getString(3)%></td>
			<td><%=rs.getInt(4)%></td>
			<td><%=rs.getInt(5)%></td>
			<td>
				<form action="CartController" method="post">
					<%
						//session.setAttribute("bookid",rs.getInt(2));
					%>
					<button name="cart" value=<%=rs.getInt(2)%>>-</button>

				</form>
			</td>
		</tr>
		<%
			sum = sum + rs.getInt(4);
		%>
		<%
			}
		%>
	</table>

	<center>
		<h3 style="color: deeppink">
			Total Amount to Pay : Rs.<%=sum%></h3>
		<%
			session.setAttribute("totalprice", sum);
		%>
		<br />
		<form action="payment1.jsp">
			<input type="submit" value="Go for Payment" />
		</form>
	</center>
	<br />
	<br />
	<%@ include file="footer.jsp"%>
</body>
</html>