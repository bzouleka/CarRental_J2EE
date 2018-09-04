<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@page import="com.campusnumerique.vehiclerental.entity.Client"%>
<%@page import="com.campusnumerique.vehiclerental.entity.Car"%>
<%@page import="com.campusnumerique.vehiclerental.entity.Reservation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<jsp:useBean id="client" scope="session"
	class="com.campusnumerique.vehiclerental.bean.ClientBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Client List</title>

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
<script src="resources/js/client.js"></script>
</head>
<body>
	<nav class="navbar navbar-light " id="header"> <a
		class="navbar-brand" href="#"> <img
		src="resources/images/delorean.png" />
	</a>
	<ul class="nav nav-pills">
		<li class="nav-item"><a class="nav-link" href="./clients.jsp">Client
				List</a></li>
		<li class="nav-item"><a class="nav-link" href="/CarServlet">Car
				List</a></li>
		<li class="nav-item"><a class="nav-link" href="./reservation.jsp">Liste
				des vehicules</a></li>
	</ul>
	</nav>
	<div class="container" id="content">
		<div class="row">
			<h1 align="center">Choix du vehicule</h1>

			<form action="./carAvailable" method="post">

				<table id="userTable" class="table table-striped">
					<thead>
						<tr>
							<th>Votre Choix</th>
							<th>Marque</th>
							<th>Model</th>
							<th>Immatriculation</th>
							<th>Couleur</th>
							<th>Prix Réservation</th>
							<th>Prix au KM</th>
							<th>Chevaux Fiscaux</th>
						</tr>

					</thead>
					<tbody>

						<%
							List<Car> cars = (List<Car>) request.getAttribute("cars");
							for (Car car : cars) {
						%>
						<tr>
							<td><input type="radio" id="selection" name="choix"
								value="<%=car.getId()%>" /></td>
							<td><%=car.getBrand()%></td>
							<td><%=car.getModel()%></td>
							<td><%=car.getPlateNumber()%></td>
							<td><%=car.getColor()%></td>
							<td><%=car.getReservation()%></td>
							<td><%=car.getKmRate()%></td>
							<td><%=car.getCv()%></td>
						</tr>
						<%
							}
						%>
					</tbody>

				</table>
				<input type="submit" value="Valider sélection" />
			</form>
		</div>

	</div>
</body>
</html>