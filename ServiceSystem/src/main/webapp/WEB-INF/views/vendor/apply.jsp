<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Apply</title>
</head>
<body>
	<c:if test="${VendorApplyErrorMessage == null}">
		<h2>Vendor Apply</h2>
		<sf:form action="login" method="POST" modelAttribute="applyVendor">
			<div>
				<p>First Name:</p>
				<sf:input type="text" placeholder="Input Your First Name"
					path="vendorFirstName" />
			</div>
			<div>
				<p>Middle Name:</p>
				<sf:input type="text" placeholder="Input Your Middle Name"
					path="vendorMiddleName" />
			</div>
			<div>
				<p>Last Name:</p>
				<sf:input type="text" placeholder="Input Your Last Name"
					path="vendorLastName" />
			</div>
			<div>
				<p>Address:</p>
				<sf:input type="text" placeholder="Input Your address"
					path="address" />
			</div>
			<div>
				<p>Mobile Phone:</p>
				<sf:input type="text" placeholder="Input Your Mobile Phone Number"
					path="phoneNumber" />
			</div>
			<div>
				<p>Store Name:</p>
				<sf:input type="text" placeholder="Input Your Store Name"
					path="storeName" />
			</div>
			<input type="submit" value="SUBMIT">
		</sf:form>
	</c:if>
	<c:if test="${VendorApplyErrorMessage != null}">
		${errorMessage}
	</c:if>
	
	<a href="index">Go Back To Home Page</a>
</body>
</html>