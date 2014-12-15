<%@page import="com.properties.MessageAndPropertiesDisplay"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><%=MessageAndPropertiesDisplay.websitetitle%></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<style type="text/css">
.bs-example {
	margin: 160px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="bs-example" align="center">
			<h2 class="panel-title" style="font: normal; color: red">${msg}</h2>
			<h2 class="panel-title" style="font: normal; color: red">${error}</h2>
			<div>
				<form:form action="login" method="post" commandName="userForm"
					class="form-inline">
					<div class="form-group">
						<label class="sr-only" for="inputEmail">Email</label>
						<form:input path="email" class="form-control" placeholder="Email" />
					</div>
					<div class="form-group">
						<label class="sr-only" for="inputPassword">Password</label>
						<form:password path="password" class="form-control"
							placeholder="Password" />
					</div>
					<button type="submit" class="btn btn-primary btn-sm">Sign
						in</button>

				</form:form>
			</div>
			<br>
			<div>Not signed up yet.Please sign up.</div>
			<br>
			<div>
				<form action="register" method="get">
					<button type="submit" class="btn btn-primary">Register</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
