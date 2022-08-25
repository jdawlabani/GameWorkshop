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
<title>Home</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h1>
			Welcome,
			<c:out value="${user.getUserName()}" />
		</h1>

		<a href="/logout">logout</a>

		<c:forEach var="game" items="${games}">
			<h2><a href="/games/${game.getId()}"><c:out value="${game.getName()}" /></a></h2>
			<h5>Genre: ${game.getGenre()}</h5>
		</c:forEach>
		<a class="btn btn-primary" href="/games/new"> Create New Game</a>
	</div>

</body>
</html>