<!DOCTYPE html>
<html>
<head>
	<title>Purchase Book</title>
</head>
<body>
<jsp:useBean id="book" type="coreservlets.Book" scope="session"/>
<h3>You've purchased
	<jsp:getProperty name="book" property="numPurchase" />
	<i><jsp:getProperty name="book" property="name" /></i>
</h3>
<h4>Total cost: $<jsp:getProperty name="book" property="totalCost" /></h4>
<br><hr>
<a href="/MyPage/LoginSuccess.jsp">Go Bank</a>
</body>
</html>