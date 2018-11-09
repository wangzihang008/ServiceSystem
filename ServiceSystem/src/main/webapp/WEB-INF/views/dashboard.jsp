<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<style type="text/css">
.content-block {
	margin: 10px;
}
</style>
</head>
<body>
	<div class="main">
		<c:if test="${LoginStatus == 'success'}">
			<h4>Welcome ${active_user_name}</h4>
			<div class="side-bar"></div>
			<div class="content">

				<div class="content-block personal-info">
					<h4>Personal Info</h4>
					<div class="content-block-detail">
						<div class="content-block-detail">
							<p>Username : ${active_user_name}</p>
						</div>
						<div class="content-block-detail">
							<p>Email : ${active_user_email}</p>
						</div>
						<div class="content-block-detail">
							<a href="">Reset Personal Information</a> <a href="">Reset
								Password</a>
						</div>
					</div>
				</div>
				<div class="content-block orders">
					<h4>Orders</h4>
					<div class="content-block-detail"></div>
				</div>
				<div class="content-block favourite-vednors">
					<h4>Favorite Vendors</h4>
					<div class="content-block-detail"></div>
				</div>
			</div>
		</c:if>
		<c:if test="${LoginStatus == 'fail'}">
			<h4>Username Or Password Is Not Correct</h4>
			<a href="">Go Back To Home Page</a>
		</c:if>

	</div>
</body>
</html>