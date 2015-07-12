<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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
		<div id="photocontainer" class="container">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h1>${mountain.name}</h1>
				</div>
				<div class="panel-body">
					<c:if test="${not empty esitoPositivo}">
						<div class="alert alert-success fade in">${esitoPositivo}</div>
					</c:if>
					<c:if test="${not empty esitoNegativo}">
						<div class="alert alert-danger fade in">${esitoNegativo}</div>
					</c:if>
					<img class="img-responsive centered" alt="Photo pic"
						src="${mountain.source}">
				</div>
				<div class="panel-footer"
					style="padding-left: auto; padding-right: auto; text-align: center">

					<c:if test="${authenticated}">
						<c:choose>
							<c:when test="${mountain.wrong}">
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${mountain.saved}"><a href='<spring:url value='/user/delete/${mountain.id}'/>'
										class="btn btn-danger" role="button">DELETE</a></c:when>
									<c:otherwise>
										<a href='<spring:url value='/user/save/${mountain.id}'/>'
											class="btn btn-success" role="button">SAVE</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:if>

					<c:choose>
						<c:when test="${mountain.saved}">
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${mountain.wrong}">Wrong!</c:when>
								<c:otherwise>
									<a href='<spring:url value='/wrongphoto/${mountain.id}'/>'
										class="btn btn-warning" role="button">WRONG</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>

					<a href='<spring:url value='/gallery'/>' class="btn btn-default"
						role="button">BACK</a>

				</div>
			</div>

		</div>
	</header>
</body>