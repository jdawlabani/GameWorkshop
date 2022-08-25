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
<title>Add Game</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<h1>Game Details</h1>

		<a href="/home">Home</a>

		<div class="container">
		
			<p>Name: ${game.getName()}</p>
			<p>Developer: ${game.getUser().getUserName()}</p>
			<p>Description:</p><br />
			<p>${game.description}</p>
			
			<c:if test = "${game.getUser().getId() == user.getId()}">
			<a class="btn btn-primary" href="/games/${game.getId()}/edit">Edit</a>
			<form:form action="/games/${game.getId()}" modelAttribute="team" class="form"
				method="post">
				<input type="hidden" name="_method" value="delete"/>
				<button class="btn btn-danger">Delete</button>
			</form:form>
			</c:if>
			
			<form:form action="/games/${game.getId()}/mechanics" modelAttribute="newMechanic" 
			class="form text-info" method="post">

				<div class="form-row">
					<form:errors path="name" class="error" /><br />
					<form:label class="form-label" for="name" path="name">Game Mechanic:</form:label>
					<form:input type="text" path="name" class="form-control" />
				</div>
				<button class="btn btn-info">Submit</button>
			</form:form>
			
			<h4>Suggested Game Mechanics:</h4>
			<ul>
				<c:forEach var="mechanic" items="${mechanics}">
					<li>${mechanic.getName()}
						<c:if test="${mechanic.getUser().getId() == user.getId()}">
							<form:form action="/games/${game.getId()}/mechanics/${mechanic.getId()}" modelAttribute="team" class="form"
							method="post">
								<input type="hidden" name="_method" value="delete"/>
								<button class="btn btn-danger">Delete</button>
							</form:form>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</body>
</html>