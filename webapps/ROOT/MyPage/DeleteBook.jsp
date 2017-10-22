<!DOCTYPE html>
<html>
<head>
	<title>Delete Book</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function () {
		document.getElementById('id').focus();
	}
</script>
<table border="5" align="center">
	<tr><th>Delete Book</th></tr>
</table>
<form action="/delete_book_servlet">
	Which one do you wanna delete?
	<input type="text" name="id" id="id"><br><br>
	<input type="submit" value="Delete it">
</form>
<hr><a href="/MyPage/LoginSuccess.jsp">Go back</a>
</body>
</html>