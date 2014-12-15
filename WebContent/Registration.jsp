<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.properties.MessageAndPropertiesDisplay"%>
<html>
<head>
<meta charset="utf-8">
<title><%=MessageAndPropertiesDisplay.websitetitle%></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<form action="login" method="get">
					<button type="submit" class="btn btn-primary">Login Here</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">

				<h5 style="font-style: italic; color: red">${error}</h5>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4">
				<form:form action="register" method="post" commandName="userForm"
					id="identicalForm">
					<form role="form">
						<div class="form-group">
							<label class="control-label" for="exampleInputFirstName1">First
								Name</label>
							<form:input path="firstName" class="form-control"
								placeholder="First Name" />
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputLastName1">Last
								Name</label>
							<form:input path="lastName" class="form-control"
								placeholder="Last Name" />
						</div>
						<div class="form-group" draggable="true">
							<label class="control-label" for="exampleInputEmail1">Email
								address</label>
							<form:input path="email" class="form-control" placeholder="Email" />
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputPassword1">Password</label>
							<form:password path="password" class="form-control"
								placeholder="Password" />
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputRePassword1">Re-enter
								Password</label>
							<form:password path="rePassword" class="form-control"
								placeholder="Re-enter Password" />
						</div>
						<div class="form-inline">
							<label class="control-label"> Gender</label> <label class="radio">
								<form:radiobutton path="gender" value="M" />Male
							</label> <label class="radio"> <form:radiobutton path="gender"
									value="F" />Female
							</label>
						</div>
						<div class="form-group">
							<label for="sel1">Nationality</label>
						</div>
						<div class="form-group">
							<form:select path="nationality" items="${countries}" ></form:select>
						</div>
						<button class="btn btn-primary btn-sm" type="submit">Submit</button>
					</form>
				</form:form>
			</div>
		</div>
	</div>

</body>

</html>