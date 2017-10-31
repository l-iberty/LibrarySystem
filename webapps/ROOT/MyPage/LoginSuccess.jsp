<!DOCTYPE html>
<html>
<head>
	<title>Login Success</title>
</head>
<body>
<jsp:useBean id="bookManager" type="coreservlets.BookManager" scope="session"/>
<jsp:useBean id="userBean" type="coreservlets.User" scope="session"/>
<h3>Welcome, <jsp:getProperty name="userBean" property="name"/>!<br>
	All books are as follow:</h3>
<jsp:getProperty name="bookManager" property="entries"/><br><hr>
<%@ page import="coreservlets.util.*" %>
<%
	String dest;
	if (userBean.getType() == UserType.REGULAR_USER) {
		dest = "/MyPage/RegularUserOpt.jsp";
	} else {
		dest = "/MyPage/AdministratorOpt.jsp";
	}
%>
<jsp:include page="<%= dest %>"/>
<br><a href="/MyPage/LoginRegister.jsp">Go back</a>
</body>
</html>