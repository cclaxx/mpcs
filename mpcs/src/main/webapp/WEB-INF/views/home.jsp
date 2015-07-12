<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>MPCS</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href='http://fonts.googleapis.com/css?family=Carme'
	rel='stylesheet' type='text/css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/mpcsstyle.css">

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href=" <spring:url value= "/index"/>">MPCS</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="<spring:url value= "/user/map"/>">Personal
							Gallery</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="authenticated" var="authenticated"></sec:authorize>
					<c:choose>
						<c:when test="${authenticated}">
							<li><a href="<spring:url value= "/logout"/>"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</c:when>
						<c:otherwise>
							<li><a href=" <spring:url value= "/user"/>"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>				
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>

	<header>
		<div id="main" class="container">
			<h1>Mountain Photo Collection System</h1>
			<p>Welcome to Mountain Photo Collection System.</p>
			<p>Click on Search to find Mountain Photos.</p>
			<a href="<spring:url value= "/search"/>"><button type="button"
					class="btn btn-primary">
					<span class="glyphicon glyphicon-search"></span> SEARCH
				</button></a> <br> <br>
			<p>Or click on Gallery to view the photo Gallery.</p>
			<a href="<spring:url value= "/gallery"/>"><button type="button"
					class="btn btn-success">
					<span class="glyphicon glyphicon-picture"></span> GALLERY
				</button></a>
		</div>
	</header>
</body>