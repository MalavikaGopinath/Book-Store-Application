<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Insertion</title>
<!-- 
  This script is used for the validations of blank columns
  -->
<script>
	function validate()//pop up message
	{
		var bookid = document.form.bookid.value;
		var bookname = document.form.bookname.value;
		var price = document.form.price.value;
		var pubdate = document.form.pubdate.value;
		var author = document.form.author.value;
		var language = document.form.language.value;
		var category = document.form.category.value;
		var noofcopy = document.form.noofcopy.value;

		if (bookid == null || bookid == "") {
			alert("Book Id cannot be blank ");
			return false;
		} else if (bookname == null || bookname == "") {
			alert("Book Name cannot be blank ");
			return false;
		} else if (price == null || price == "") {
			alert("Price cannot be blank ");
			return false;
		} else if (pubdate == null || pubdate == "") {
			alert("Publishing date cannot be blank ");
			return false;
		} else if (author == null || author == "") {
			alert("Author name cannot be blank ");
			return false;
		} else if (language == null || language == "") {
			alert("Language name cannot be blank ");
			return false;
		} else if (category == null || category == "") {
			alert("Category name cannot be blank ");
			return false;
		} else if (noofcopy == null || noofcopye == "") {
			alert("Number of copies cannot be blank ");
			return false;
		} else {
			alert(" blank box ");
			return false;

		}

	}
</script>
</head>
<body background="btadmininsertion.jpg">
	<%@ include file="adminHeader.jsp"%>
	<form name="form" action="AdminInsertionLoginController" method="post"
		onsubmit="return validate()">


		<div style="text-align: center">
			<h1>Insert Book</h1>
		</div>
		<br> <br>
		<div style="text-align: center">
			<table style="display: inline-block" cellspacing="20">

				<tr>
					<td><b>Book Id[Number] </b></td>
					<td><input type="text" name="bookid" placeholder="Number" /></td>
				</tr>
				<tr>
					<td><b>Name of the Book </b></td>
					<td><input type="text" name="bookname" placeholder="Book Name" /></td>
				</tr>
				<tr>
					<td><b>Price(Rs)</b></td>
					<td><input type="text" name="price" placeholder="eg:100" /></td>
				</tr>
				<tr>
					<td><b>Publishing Date[mm-dd-yyyy] </b></td>
					<td><input type="date" name="pubdate" placeholder="dd-mm-yyyy" /></td>
				</tr>
				<tr>
					<td><b>Author </b></td>
					<td><input type="text" name="author" placeholder="Name" /></td>
				</tr>
				<tr>
					<td><b>Language[eg:English]</b></td>
					<td><input type="text" name="language"
						placeholder="eg:English" /></td>
				</tr>
				<tr>
					<td><b>Category[eg:Comic] </b></td>
					<td><input type="text" name="category" placeholder="eg:Comic" /></td>
				</tr>
				<tr>
					<td><b>No of Copies</b></td>
					<td><input type="text" name="noofcopy" placeholder="Number" /></td>
				</tr>
				<tr>
					<td><b> Option</b></td>:
					<td><select name="option">
							<option>BUY</option>
							<option>RENT</option>
							<option>BOTH</option>
					</select></td>
				</tr>
				<br>
				<br>
				<tr>
					<td colspan="2"><input type="submit" value="SUBMIT" /></td>
				</tr>
				<tr>
					<td colspan="2"><span style="color: green"><%=(request.getAttribute("errMessage") == null ? "" : request.getAttribute("errMessage"))%></span>
					<td colspan="2"><span style="color: red"><%=(request.getAttribute("valid") == null ? "" : request.getAttribute("valid"))%></span>
				</tr>
			</table>
		</div>
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>