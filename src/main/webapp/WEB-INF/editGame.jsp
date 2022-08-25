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
<title>Edit ${game.getName()}</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<h1>Edit Game</h1>

		<a href="/home">Home</a>

		<div class="container">
			<form:form action="/games/${game.getId()}" modelAttribute="game" class="form"
				method="post">
				<input type="hidden" name="_method" value="put"/>
				<div class="form-row">
					<form:errors path="name" class="error text-danger" /><br />
					<form:label class="form-label" for="name" path="name">Name:</form:label>
					<form:input type="text" path="name" class="form-control" />
				</div>

				<div class="form-row">
					<form:errors path="genre" class="error text-danger" /><br />
					<form:label class="form-label" for="genre" path="genre">Genre:</form:label>
					<form:input type="text" path="genre" class="form-control" />

				</div>

				<div class="form-row">
					<form:errors path="description" class="error text-danger" /><br />
					<form:label class="form-label" for="description" path="description">Description:</form:label>
					<form:textarea class="form-control" path="description" cols="15" rows="5"></form:textarea>
				</div>
				
				<div class="form-row">
					<form:errors path="user" class="error" />
					<form:input type="hidden" path="user" value="${user.id}"
						class="form-control" />
				</div>
				
				<button class="btn btn-secondary">Submit</button>

			</form:form>
		</div>
	</div>

</body>
</html>