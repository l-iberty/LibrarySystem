<!DOCTYPE html>
<html>
<head>
	<title>Login Success</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function () {
		document.getElementById('id').focus();
	}
</script>
<form action="/check_book_servlet">
	Please input book ID to check detailed information:
	<input id="id" type="text" name="id"><br>
	Get books list by
	<select name="key">
		<option value="name" selected="selected">Name</option>
		<option value="author">Author</option>
	</select>
	<input type="text" name="keyvalue"><br>
	<input type="submit" value="check"><br><hr>
</form>
<jsp:include page="/MyPage/PurchaseBook.jsp"/>
</body>
</html>