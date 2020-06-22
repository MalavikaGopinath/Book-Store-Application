<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Gateway</title>

</head>
<link rel="stylesheet"type="text/css" href="style.css">
<body>
		<%@ include file="header.jsp"%>
		<center>
		<h3>Book Name : <%=session.getAttribute("bookName") %></h3>
		<h3>Price	  : Rs.<%=session.getAttribute("amount") %>/-</h3>
		</center>
		<br/>
        <div class="app-container">
        <div class="top-box">
            <p>PAYMENT GATEWAY</p>
        </div>
        <div class="middle-box">
            <h1><%=session.getAttribute("amount") %><span>/-</span></h1>
        </div>
        <div class="bottom-box">
        	<form action="onlinePayment2.jsp">
        	<input type="submit" class="Payment-option-btn" value="Online Banking" name="pay" /></form>
        </div>
          <%@ include file="footer.jsp"%>
    </div>
    </body>
</html>