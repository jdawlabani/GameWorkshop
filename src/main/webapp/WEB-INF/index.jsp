<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login and Register</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">

		<h1>Welcome!</h1>

		<h2>Register</h2>

		<div class="container">
			<form:form action="/register" modelAttribute="newUser" class="form">

				<div class="form-row">
					<form:errors class="text-danger" path="userName" /> <br/>
					<form:label for="userName" path="userName">User Name:</form:label>
					<form:input type="text" path="userName" class="form-control" />
				</div>

				<div class="form-row">
					<form:errors class="text-danger" path="email" /> <br/>
					<form:label for="email" path="email">Email:</form:label>
					<form:input type="text" path="email" class="form-control" />
				</div>

				<div class="form-row">
					<form:errors class="text-danger" path="password" /> <br/>
					<form:label for="password" path="password">Password:</form:label>
					<form:input type="password" path="password" class="form-control" />
				</div>

				<div class="form-row">
					<form:errors class="text-danger" path="confirm" /> <br/>
					<form:label for="" path="confirm">Confirm:</form:label>
					<form:input type="password" path="confirm" class="form-control" />
				</div>

				<button class="btn btn-secondary">Submit</button>

			</form:form>
		</div>
		
		<h2>Login</h2>
		<div class="container">
			<form:form action="/login" modelAttribute="newLogin" class="form">
				<div class="form-row">
					<form:errors class="text-danger" path="email" /> <br />
					<form:label for="email" path="email">Email:</form:label>
					<form:input type="text" path="email" class="form-control" />
				</div>

				<div>
					<form:errors class="text-danger" path="password" /> <br />
					<form:label for="password" path="password">Password:</form:label>
					<form:input type="password" path="password" class="form-control" />
				</div>

				<button class="btn btn-secondary">Submit</button>

			</form:form>
		</div>

	</div>

</body>
</html>