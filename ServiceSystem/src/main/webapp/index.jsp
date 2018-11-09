<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

div > form > input{
	margin: 10px  12%;
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
		<button onclick="buttonEffect('login_area');">Customer
				Login</button>
	</div>
	<div class="main">
		<div id="register_area" class="">
			<h2>Register</h2>
			<form action="register" method="POST">
				<div>
					<p>USERNAME:</p>
					<input type="text" placeholder="Input Your Username"
						name="username">
				</div>
				<div>
					<p>EMAIL:</p>
					<input type="text" placeholder="Input Your Email" name="email">
				</div>
				<div>
					<p>PASSWORD:</p>
					<input type="password" placeholder="Input Your Password"
						name="password">
				</div>
				<input type="submit" value="SUBMIT">
			</form>
		</div>
		<div id="login_area" class="">
			<h2>Login</h2>
			<form action="login" method="POST">
				<div>
					<p>USERNAME:</p>
					<input type="text" placeholder="Input Your Username"
						name="username">
				</div>
				<div>
					<p>PASSWORD:</p>
					<input type="password" placeholder="Input Your Password"
						name="password">
				</div>
				<input type="submit" value="SUBMIT">
			</form>
		</div>
	</div>
</body>
</html>