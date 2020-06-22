<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Book Store</title>
<script>
	function validate() {
		var bookid = document.form.bookid.value;

		if (bookid == null || bookid == "") {
			alert("Enter a valid Book ID");
			return false;
		}
	}
</script>
<link rel='stylesheet' href='onlineBookStore.css' type='text/css'>
</head>
<body background="obs.jpg">
	<%@include file="header.jsp"%>
	<br />
	<span style="color: darkblue"><h2><%=(request.getAttribute("msg1") == null) ? "" : request.getAttribute("msg1")%></h2></span>
	<br />
	<table align="center" class="DbTable" border="1">
		<tr>
			<td><b>Book ID</b></td>
			<td><b>Book Name</b></td>
			<td><b>Price (in Rs.)</b></td>
			<td><b>Published Date</b></td>
			<td><b>Author</b></td>
			<td><b>Language</b></td>
			<td><b>Category</b></td>
			<td><b>Available Copies</b></td>
			<td><b>Option</b></td>
			<td><b> </b></td>
		</tr>
		<%
			ResultSet rs = (ResultSet) request.getAttribute("resultset");
			while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getInt(1)%></td>
			<td><%=rs.getString(2)%></td>
			<td><%=rs.getDouble(3)%></td>
			<td><%=rs.getString(4)%></td>
			<td><%=rs.getString(5)%></td>
			<td><%=rs.getString(6)%></td>
			<td><%=rs.getString(7)%></td>
			<td><%=rs.getInt(8)%></td>
			<%
				if (rs.getString(9).equals("BUY")) {
			%>
			<td>BUY</td>
			<%
				} else if (rs.getString(9).equals("BOTH")) {
			%>
			<td>BUY or RENT</td>
			<%
				} else if (rs.getString(9).equals("RENT")) {
			%>
			<td>RENT</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<br />
	<div align="center">
		<form name="form" action="CartController" method="post"
			onsubmit="return validate()">
			<span style="color: darkblue">Type the Book ID of the Book you
				would like to BUY/RENT below <br /> <br />Book ID :
			</span> <input type="text" name="bookid" placeholder=" Enter Book ID"><br />
			<br /> <input type="submit" value="BUY" name="cart">
			<!-- </form> -->
			<input type="submit" value="ADD TO CART" name="cart"> <input
				type="submit" value="RENT" name="cart">
		</form>
		<br />
		<span style="color: red"><b><%=(request.getAttribute("errBookId") == null) ? "" : request.getAttribute("errBookId")%></b></span>
		<br />
		<span style="color: red"><b><%=(request.getAttribute("state") == null) ? "" : request.getAttribute("state")%></b></span>
		<br /> <span style="color: green"><b><%=(request.getAttribute("state1") == null) ? "" : request.getAttribute("state1")%></b></span>
		<br /> <span style="color: red"><b><%=(request.getAttribute("state2") == null) ? "" : request.getAttribute("state2")%></b></span><br />
	</div>
	<br />
	<%@ include file="footer.jsp"%>
</body>
</html>