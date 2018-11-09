<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="reset_password" method="POST">
		<div>
			<p>OLD PASSWORD:</p>
			<input type="password" placeholder="Input Your New Password"
				name="old_password">
		</div>
		<div>
			<p>NEW PASSWORD:</p>
			<input type="password" placeholder="Input Your New Password"
				name="new_password">
		</div>
		<div>
			<p>NEW PASSWORD RETYPE:</p>
			<input type="password" placeholder="Retye Your New Password"
				name="retype_new_password">
		</div>
		<input type="submit" value="SUBMIT">
	</form>
</body>
</html>