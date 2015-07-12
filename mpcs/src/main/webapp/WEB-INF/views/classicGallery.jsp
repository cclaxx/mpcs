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
	
	<script>
		function initialize() {
			var mapProp = {
				center:new google.maps.LatLng(36,12),
				zoom:3,
				mapTypeId:google.maps.MapTypeId.SATELLITE
			};
		var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	
	<script>
		$(document).ready(function(){
           $('li img').on('click',function(){
                var src = $(this).attr('src');
                var img = '<img src="' + src + '" class="img-responsive"/>';
                $('#myModal').modal();
                $('#myModal').on('shown.bs.modal', function(){
                    $('#myModal .modal-body').html(img);
                });
                $('#myModal').on('hidden.bs.modal', function(){
                    $('#myModal .modal-body').html('');
                });
           });  
        })
	</script>
	
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
      <a class="navbar-brand" href="<spring:url value= "/index"/>">MPCS</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<spring:url value= "/login"/>"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		<li><a href="<spring:url value= "/register"/>"><span class="glyphicon glyphicon-user"></span> Register</a></li>
      </ul>
    </div>
  </div>
</nav>

<section>

<div id="classicGallery" class="container-fluid gallery">
	<h1>Classic Gallery</h1>
	<div class="secondGallery"><a href="<spring:url value= "/map"/>">Map Gallery</a></div>
</div>
<div id="fotoPreview">
	<div class="container">
		<ul class="row">
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/1.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/2.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/3.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/4.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/5.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/6.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/7.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/8.jpg"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/9.png"/></li>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><img src="images/10.jpg"/></li>
        </ul>
	</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content"> 
<div class="modal-body"> 
</div>
</div>
</div>
</div>

</section>

</body>