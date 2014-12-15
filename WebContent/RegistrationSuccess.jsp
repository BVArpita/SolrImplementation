<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.properties.MessageAndPropertiesDisplay"%>
<head>
<meta charset="utf-8">
<title><%=MessageAndPropertiesDisplay.websitetitle%></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="col-md-12">
				<h1>New user registration</h1>
				<h5 style="font-style: italic; color: blue;">${msg}</h5>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<form action="login" method="get">
					<button type="submit" class="btn btn-primary">Login Here</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>