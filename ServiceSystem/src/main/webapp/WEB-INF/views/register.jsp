<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	
	<c:if test="${ErrorMessage == ''}">
		<h4>Register Success</h4>
		<a href="index">Go Back To Home Page</a>
		<a href="/dashboard">Go Back To Dashboard Page</a>
	</c:if>
	<c:if test="${ErrorMessage != ''}">
		<c:if test="${ErrorMessage != null}">
			<h4>${ErrorMessage}</h4>
		</c:if>
		<c:if test="${ErrorMessage == null}">
			<h4>Register</h4>
		</c:if>
		<div id="register_area" class="">
			<h2>Register</h2>
			<sf:form action="register" method="POST"
				modelAttribute="registerCustomer">
				<div>
					<p>USERNAME:</p>
					<sf:input type="text" placeholder="Input Your Username"
						path="username" />
				</div>
				<div>
					<p>EMAIL:</p>
					<sf:input type="text" placeholder="Input Your Email" path="email" />
				</div>
				<div>
					<p>PASSWORD:</p>
					<sf:input type="password" placeholder="Input Your Password"
						path="password" />
				</div>
				<input type="submit" value="SUBMIT">
			</sf:form>
		</div>
	</c:if>
</body>
</html>