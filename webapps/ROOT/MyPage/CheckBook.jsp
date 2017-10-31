<!DOCTYPE html>
<html>
<head>
	<title>Books Information</title>
</head>
<body>
<jsp:useBean id="bookManager2" type="coreservlets.BookManager" scope="session"/>
<h3>Books Information:</h3>
<jsp:getProperty name="bookManager2" property="entries"/><br><hr>
<a href="/MyPage/LoginSuccess.jsp">Go back</a>
</body>
</html>