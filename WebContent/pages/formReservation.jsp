<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>

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
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
	<!-- General -->
	<link rel="stylesheet" href="resources/css/global.css" />
</head>
<body>
		<nav class="navbar navbar-light " id="header">
	 <a class="navbar-brand" href="#">
		<img  src="resources/images/delorean.png"/>
	 </a>
	  <ul class="nav nav-pills">
	    <li class="nav-item">
	      <a class="nav-link" href="./clients.jsp">Client List</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="./CarServlet">Car List</a>
	    </li>
	   </ul>
		
	</nav>
</body>
</html>