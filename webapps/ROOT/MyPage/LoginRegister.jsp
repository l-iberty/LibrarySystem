<!DOCTYPE html>
<html>
<head>
	<title>Login And Register</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function () {
		document.getElementById('name').focus();
	}
</script>
<table border="5" align="center">
	<tr><th>Login And Register</th></tr>
</table>
<form action="/login_register_servlet">
	UserName: <input type="text" name="name" id="name"><br><br>
	Password: <input type="password" name="passwd"><br><br>
	Type:
	<select name="type">
		<option value="0" selected="selected">Regular User</option>
		<option value="1">Administrator</option>
	</select>
	<br><br>
	<input type="submit" name="submit" value="Register">
	<input type="submit" name="submit" value="Login">
</form>
</body>
</html>