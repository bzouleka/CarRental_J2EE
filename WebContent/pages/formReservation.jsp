<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.campusnumerique.vehiclerental.entity.Car"%>

<jsp:useBean id="client" scope="session"
	class="com.campusnumerique.vehiclerental.bean.ClientBean" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pre Reservation</title>

<!-- JQuery 3.3.1 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<!-- Bootstrap 4.1.3 -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<!-- General -->
<link rel="stylesheet" href="resources/css/global.css" />
</head>
<body>
	<nav class="navbar navbar-light " id="header">
		<a class="navbar-brand" href="#"> <img
			src="resources/images/delorean.png" />
		</a>
		<ul class="nav nav-pills">
			<li class="nav-item"><a class="nav-link" href="./clients.jsp">Client
					List</a></li>
			<li class="nav-item"><a class="nav-link" href="./CarServlet">Car
					List</a></li>
		</ul>
	</nav>
	<div class="container" id="content">
		<div id="errorMessage">
		<h2>
			<%
			if(null!=request.getAttribute("error"))
			{
				out.println(request.getAttribute("error"));
			}
			%>
			</h2>
		</div>
		<h1 align="center">Réservez votre voiture</h1>
		<form method="post" action="./reservation">
			<div class="form-group">
				<label for="nom">Nom</label> <input type="text" class="form-control"
					id="nom" name="nom" value="Verges" placeholder="Votre nom">
			</div>
			<div class="form-group">
				<label for="prenom">Prenom</label> <input type="text"
					class="form-control" id="prenom" name="prenom" value="Vincent" placeholder="Votre prénom">
			</div>
			<div class="form-group">
				<label for="email">E-mail</label> <input type="email"
					class="form-control" id="email" name="email" value="vincent.verges@gmail.com" placeholder="Votre adresse e-mail">
			</div>
			<div class="form-group">
				<label for="permisNb">Numéro de permis</label> <input type="text"
					class="form-control" id="permisNb" name="permisNb" value="123456789"
					placeholder="Votre numéro de permis">
			</div>
			<div class="form-group">
				<label for="permisDate">Date d'obtention du permis</label> <input type="date"
					class="form-control" id="permisDate" name="permisDate">
			</div>
			<div class="form-group">
				<label for="birthDate">Date de naissance</label> <input type="date"
					class="form-control" id="birthDate" name="birthDate">
			</div>
			<div class="form-group">
				<label for="distance">Distance prévue en km</label> <input type="text"
					class="form-control" id="distance" name="distance" placeholder="Km parcourus">
			</div>
			<div class="form-group">
				<label for="startDate">Date de départ</label> <input type="date"
					class="form-control" id="startDate" name="startDate">
			</div>
			<div class="form-group">
				<label for="finishDate">Date d'arrivée</label> <input type="date"
					class="form-control" id="finishDate" name="finishDate">
			</div>
			<button type="submit" class="btn btn-primary mb-2">Reserver</button>
		</form>
	</div>
</body>
</html>