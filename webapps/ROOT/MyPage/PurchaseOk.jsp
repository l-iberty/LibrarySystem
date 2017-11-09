<!DOCTYPE html>
<html>
<head>
	<title>Purchase Book</title>
</head>
<body>
<jsp:useBean id="tradeRecordBean" type="coreservlets.TradeRecord" scope="session"/>
<h3>You've purchased
	<jsp:getProperty name="tradeRecordBean" property="numBooks" />
	<i><jsp:getProperty name="tradeRecordBean" property="bookName" /></i>
</h3>
<h4>Total cost: $<jsp:getProperty name="tradeRecordBean" property="totalCost" /></h4>
<br><hr>
<a href="/MyPage/LoginSuccess.jsp">Go back</a>
</body>
</html>