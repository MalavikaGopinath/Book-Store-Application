
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.sql.ResultSet"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Modify</title>
<!-- 
  This script is used for the validations of blank columns
  -->
<script>
	function validate()//pop up message
	{
		var bookid = document.form.bookid.value;
 

		if (bookid == null || bookid == "") {
			alert("Book Id cannot be blank ");
			return false;
		}
	}
</script>
</head>
<body background="btadmininsertion.jpg">
<%@ include file="adminHeader.jsp"%>
	<form name="form" action="AdminModifyController" method="post" onsubmit="return validate()">
		<div style="text-align: center">
			<h1>Modify Price</h1>
		</div>
		<br> <br>
		<div style="text-align: center">
		<% 
		if((String)request.getAttribute("success")!=null)
		{
			%>
			<table style="display: inline-block" cellspacing="20">
				<tr>
					<td><b>Book Id </b></td>
					<td><input type="text" name="bookid" value=<%=(request.getAttribute("bookid"))%> /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="SUBMIT" name="buttonCheck" /></td>
				</tr>
				   
					<tr>
					<td>Book ID</td>
					<td><%=(request.getAttribute("bookid"))%></td>
				</tr>

				<tr>
					<td>Book Name</td>
					<td><%=(request.getAttribute("bookname"))%></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><%=(request.getAttribute("price"))%></td>
				</tr>
				<tr>
					<td>Publishing Date</td>
					<td><%=(request.getAttribute("pubdate"))%></td>
				</tr>
				<tr>
					<td>Author</td>
					<td><%=(request.getAttribute("author"))%></td>
				</tr>
				<tr>
					<td>Language</td>
					<td><%=(request.getAttribute("language"))%></td>
				</tr>
				<tr>
					<td>Category</td>
					<td><%=(request.getAttribute("category"))%></td>
				</tr>
				<tr>
					<td>No Of Copies</td>
					<td><%=(request.getAttribute("noofcopies"))%></td>
				</tr>
				<tr>
				<td><b>New Price </b></td>
				<td><input type="text" name="newPrice" /></td>
				</tr>
				<tr>
				
					<td align="left"><input type="submit" value="UPDATE" name="buttonCheck"/></td>
					<td align="right"><input type="submit" value="CANCEL" name="buttonCheck"/></td>
				</tr>
				
					</table>
			<%}else{ %>
			<table style="display: inline-block">
				<tr>
					<td><b>Book Id </b></td>
					<td><input type="text" name="bookid" value=""  /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="SUBMIT" name="buttonCheck" /></td>
				</tr>
				 <tr>
				    <td colspan="2"><span style="color: red"><%=(request.getAttribute("error")==null
					?"":request.getAttribute("error")) %></span>
					</tr>
					<tr>
				    <td colspan="2"><span style="color:green"><%=(request.getAttribute("succmessage")==null
					?"":request.getAttribute("succmessage")) %></span>
					</tr>
					<tr>
				    <td colspan="2"><span style="color: red"><%=(request.getAttribute("failmessage")==null
					?"":request.getAttribute("failmessage")) %></span>
					</tr>
					<tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("cancel")==null
					?"":request.getAttribute("cancel")) %></span>
					</tr>
					 <tr>
				    <td colspan="2"><span style="color: red"><%=(request.getAttribute("blank")==null
					?"":request.getAttribute("blank")) %></span>
					</tr>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("valid")==null
					?"":request.getAttribute("valid")) %></span>
                   </tr>
				</table>
			<%} %>

			</table>
		</div>
	</form>
	<%@ include file="footer.jsp"%>

</body>
</html>