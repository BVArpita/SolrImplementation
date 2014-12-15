<%@page import="com.properties.MessageAndPropertiesDisplay"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="utf-8">
<title><%=MessageAndPropertiesDisplay.websitetitle%></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body class="" draggable="true">
	<div class="navbar navbar-default navbar-static-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse"></a> <span class="sr-only">Toggle
					navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav navbar-right" style="">
					<li draggable="true"><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form action="search" method="get">
					<button type="submit" class="btn btn-primary">Back</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h4>${title }</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">${newsContent }</div>
		</div>

	</div>
</body>

</html>