<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Marketplace - Find What You Need</title>
<style type="text/css">
.hidden {
	display: none;
}

.active {
	display: inline-block;
}

.main {
	float: left;
	width: 600px;
}

#register_area {
	width: 45%;
	float: left;
}

#login_area {
	width: 50%;
	float: right;
}

div>form>input {
	margin: 10px 12%;
}
</style>
<script type="text/javascript">
	function show(id) {
		var area = document.getElementById(id);
		area.className.replace("hidden", "active");
	}

	function hide(id) {
		var area = document.getElementById(id);
		area.className.replace("active", "hidden");
	}

	function buttonEffect(id) {
		var register_area = document.getElementById("register_area");
		if (id == "register_area") {
			show(id);
			hide("login_area");
		} else {
			hide(id);
			show("login_area");
		}
	}
</script>
</head>
<body>
	<div>
		<button onclick="buttonEffect('register_area');">Customer
			Register</button>
		<button onclick="buttonEffect('login_area');">Customer Login</button>
	</div>
	<div class="main">
		<c:if test="${LoginStatus == 'fail'}">
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
			</div>
		</c:if>
		<c:if test="${LoginStatus == 'success'}">
			<a href="dashboard">Go To Your Dashboard</a> 
			<a href="logout">Logout</a>
			<form action="search" method="GET">
				<input type="text" name="search" placeholder="search service with vendor info or service keyword">
				<input type="submit" value="SEARCH">
			</form>
		</c:if>
	</div>
</body>
</html>