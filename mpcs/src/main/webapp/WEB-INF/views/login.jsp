<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				<ul class="nav navbar-nav navbar-right">
				</ul>
			</div>
		</div>
	</nav>

	<header>
		<div id="main" class="container">
			<h1>Mountain Photo Collection System</h1>
			<p>Enter your email and password to log in MPCS.</p>
		</div>

		<div class="container">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>Login</h2>
				</div>

				<div class="panel-body">
					<c:url value="/login" var="loginUrl" />
					<form:form class="form-horizontal" role="form" action="${loginUrl}"
						method='POST'>

						<c:if test="${param.error != null}">
							<div class="alert alert-danger fade in">

								Failed to login.

								<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
								Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
								</c:if>
							</div>
						</c:if>

						<c:if test="${param.logout != null}">
							<div class="alert alert-success">You have been logged out.</div>
						</c:if>

						<div class="form-group">
							<label class="control-label col-sm-3" for="email">Email:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name='username' value=''
									id="email" placeholder="Inserisci il tuo indirizzo email" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-3" for="password">Password:</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="password"
									name='password' placeholder="Password" />
							</div>
						</div>

						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<input id="btnSubmit" name="submit" type="submit"
								class="btn btn-default" value="Accedi" />
						</div>

					</form:form>
				</div>

			</div>
		</div>
	</header>
</body>