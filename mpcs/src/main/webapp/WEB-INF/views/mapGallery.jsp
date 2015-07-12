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

<script src="http://maps.googleapis.com/maps/api/js"></script>



<script
	src="<%=request.getContextPath()%>/resources/js/markerclusterer.js"
	type="text/javascript"></script>


<script type="text/javascript">
		function initialize() {
			
			var mapProp = {
				center:new google.maps.LatLng(36,12),
				zoom:3,
				mapTypeId:google.maps.MapTypeId.SATELLITE
			};
		
			var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
		
			var clusterStyles = [
			                     {
			                       textColor: 'white',
			                       textSize: 16,
			                       url: '<%=request.getContextPath()%>/resources/img/plussign.png',
			                       height: 50,
			                       width: 50
			                     },
			                    {
			                       textColor: 'white',
			                       textSize: 16,
			                       url: '<%=request.getContextPath()%>/resources/img/plussign.png',
			                       height: 50,
			                       width: 50
			                     },
			                    {
			                       textColor: 'white',
			                       textSize: 16,
			                       url: '<%=request.getContextPath()%>/resources/img/plussign.png',
			height : 50,
			width : 50
		} ];

		var mcOptions = {
			gridSize : 30,
			styles : clusterStyles,
			maxZoom : 12
		};

		var markers = [];
		var i;

		var infowindow = new google.maps.InfoWindow();

		<c:forEach items="${mountains}" var="mountain" varStatus="status">

		var lat = "${mountain.lat}";

		var lng = "${mountain.lng}";

		var sign = new google.maps.MarkerImage('${mountain.source}', null,
				null,
				/* Offset x axis 33% of overall size, Offset y axis 100% of overall size */
				new google.maps.Point(40, 110), new google.maps.Size(100, 100));

		var marker = new google.maps.Marker({
			position : new google.maps.LatLng(lat, lng),

			animation : google.maps.Animation.BOUNCE,
			icon : sign
		});

		google.maps.event
				.addListener(
						marker,
						'click',
						function() {

							infowindow
									.setContent("<a href='<spring:url value='/photo/${mountain.id}'/>'>${mountain.name}</a>");
							infowindow.open(map, this);

						});

		markers.push(marker);

		</c:forEach>

		var markerCluster = new MarkerClusterer(map, markers, mcOptions);

	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>

<script>
	$(document).ready(function() {
		$('li img').on('click', function() {
			var src = $(this).attr('src');
			var img = '<img src="' + src + '" class="img-responsive"/>';
			$('#myModal').modal();
			$('#myModal').on('shown.bs.modal', function() {
				$('#myModal .modal-body').html(img);
			});
			$('#myModal').on('hidden.bs.modal', function() {
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
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<spring:url value= "/index"/>">MPCS</a>
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
	<section>
		<div id="mapGallery" class="container-fluid gallery">
			<c:choose>
				<c:when test="${gallery=='personal' and authenticated}">
					<h1><sec:authentication property="name" /> Personal Gallery</h1>
				</c:when>
				<c:otherwise>
					<h1>Map Gallery</h1>
				</c:otherwise>
			</c:choose>
			
		</div>
		<div id="googleMap"></div>
	</section>
</body>