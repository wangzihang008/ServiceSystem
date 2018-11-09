<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Result</title>
</head>
<body>
	<c:if test="${RegisterStatus == 'success'}">
		<h4>Register Success</h4>
	</c:if>
	<c:if test="${RegisterStatus == 'fail'}">
		<h4>Register Fail - Username Or Email Has Already Existed</h4>
	</c:if>
	<c:if test="${RegisterStatus == 'missing'}">
		<h4>Register Fail - Missing Information</h4>
	</c:if>
</body>
</html>