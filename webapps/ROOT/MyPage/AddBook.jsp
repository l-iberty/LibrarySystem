<!DOCTYPE html>
<html>
<head>
	<title>Add Book</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function () {
		document.getElementById('id').focus();
	}
</script>
<table border="5" align="center">
	<tr><th>Add Book</th></tr>
</table>
<form action="/add_book_servlet"><br><br>
<table>
	<tr><td>ID:</td><td><input type="text" name="id" id="id"></td></tr>
	<tr><td>Name:</td><td><input type="text" name="name"></td></tr>
	<tr><td>Author:</td><td><input type="text" name="author"></td></tr>
	<tr><td>Price:</td><td><input type="text" name="price"></td></tr>
	<tr><td>numItems:</td><td><input type="text" name="numItems"></td></tr>
	<tr><td><input type="submit" value="Submit"></td>
</table>
</form>
<hr><a href="/MyPage/LoginSuccess.jsp">Go back</a>
</body>
</html>