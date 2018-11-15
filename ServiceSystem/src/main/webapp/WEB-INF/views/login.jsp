<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<c:if test="${ErrorMessage == '' || LoginStatus == 'success'}">
		<h4>Login Success</h4>
		<a href="">Go Back To Home Page</a>
		<a href="dashboard">Go Back To Dashboard Page</a>
	</c:if>
	<c:if test="${ErrorMessage != '' && LoginStatus == 'fail'}">
		<h4>${ErrorMessage}</h4>
		<div id="login_area" class="">
			<h2>Login</h2>
			<sf:form action="login" method="POST" modelAttribute="loginCustomer">
				<div>
					<p>USERNAME:</p>
					<sf:input type="text" placeholder="Input Your Username"
						path="username" />
				</div>
				<div>
					<p>PASSWORD:</p>
					<sf:input type="password" placeholder="Input Your Password"
						path="password" />
				</div>
				<input type="submit" value="SUBMIT">
			</sf:form>
			<a href="index">Go Back To Home Page</a>
			<a href="register">Go Register</a>
		</div>
	</c:if>
</body>
</html>