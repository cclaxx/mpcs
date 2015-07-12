<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
	<title>MPCS</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link href='http://fonts.googleapis.com/css?family=Carme' rel='stylesheet' type='text/css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mpcsstyle.css">
	
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="<spring:url value= "/user"/>">MPCS</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="<spring:url value= "/user/gallery/map"/>">Personal Gallery</a></li>
        <li><a href="<spring:url value= "/user/unregister"/>">Un-register</a></li>		
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<spring:url value= "/index"/>"><span class="glyphicon glyphicon-log-out"></span></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<header>
<div id="main" class="container">
    <h1>Mountain Photo Collection System</h1>      
    <p>Are you sure you want un-register from Mountain Photo Collection System?</p>
	</br>
	<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> YES, sure!</button>
<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> NO, I'm not sure!</button>
</div>

</header>

</body>