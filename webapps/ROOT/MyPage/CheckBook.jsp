<!DOCTYPE html>
<html>
<head>
	<title>Books Information</title>
</head>
<body>
<jsp:useBean id="bookEntries2" type="coreservlets.BookEntries" scope="session"/>
<h3>Books Information:</h3>
<jsp:getProperty name="bookEntries2" property="entries"/><br><hr>
<a href="/MyPage/LoginSuccess.jsp">Go Bank</a>
</body>
</html>