<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function validate() {
		var cust_id = document.form.cust_id.value;
		var cust_pin = document.form.cust_pin.value;

		if (cust_id == null || cust_id == "") {
			alert("Customer Id cannot be blank");
			return false;
		} else if (cust_pin == null || cust_pin == "") {
			alert("Pin Number cannot be blank");
			return false;
		} else if (cust_id.length != 3) {
			alert("Customer Id must be 3 digits");
			return false;
		} else if (cust_pin.length != 4) {
			alert("Pin number must be 4 digits");
			return false;
		}
	}
</script>

</head>
<link rel='stylesheet' href='debit.css' type='text/css'>
<body background="img9p.jpg">
	<%@ include file="header.jsp"%>
	<form name="form" action="OnlinePaymentController" method="post"
		onsubmit="return validate()">
		<div style="text-align: center">
			<h2>Online Payment</h2>
		</div>
		<br>
		
		<div class="online" style="text-align: center; color: aqua;">
			<b>Customer ID</b><br>
			<input type="text" name="cust_id" placeholder="Enter ID" /><br>
			<span style="color: red"><h2><%=(request.getAttribute("custid") == null) ? "" : request.getAttribute("custid")%></h2></span>
			<br> <b>Pin Number </b><br>
			<input type="password" name="cust_pin" placeholder="Enter PIN" /><br>
			<span style="color: red"><h2><%=(request.getAttribute("custpin") == null) ? "" : request.getAttribute("custpin")%></h2></span><br/>
			<br> <span style="color: red"> <%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span> 
			<input type="submit"
				value="Pay" name="ob" align="middle" />
	</form>
	</div>
	<center>
	<span style="color: red"><h2><%=(request.getAttribute("msg2") == null) ? "" : request.getAttribute("msg2")%></h2></span><br/>
	</center>
	<br/><br/>
	<%@ include file="footer.jsp"%>
</body>
</html>