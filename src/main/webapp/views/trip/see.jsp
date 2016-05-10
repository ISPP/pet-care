<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="col-md-8-2-noColor panel panel-default">
	<div class="wrap-3">
		<h3 class="h3-no-bottom">${trip.startCity} -> ${trip.endCity}</h3>
		<hr>
		<div class="col-md-4">
			<b><spring:message code="trip.startCity" /></b>:
			<jstl:out value="${trip.startCity}"></jstl:out>
			<br /> <b><spring:message code="trip.distance" /></b>:
			<jstl:out value="${trip.distance}"></jstl:out>
			<br />
		</div>
		<div class="col-md-4">
			<b><spring:message code="trip.endCity" /></b>:
			<jstl:out value="${trip.endCity}"></jstl:out>
			<br /> <b><spring:message code="trip.vehicle" /></b>:
			<jstl:out value="${trip.vehicle.title}"></jstl:out>
			<br />

		</div>
		<div class="col-md-4">
			<b><spring:message code="trip.cost" /></b>:
			<jstl:out value="${trip.cost}">&#8364;</jstl:out>
			<br /> <b><spring:message code="trip.moment" /></b>:
			<fmt:formatDate value="${trip.moment}" pattern="dd/MM/yyyy HH:mm" />
			<br />
		</div>
		<br /> <b><spring:message code="trip.descriptionText" /></b>:
		<jstl:out value="${trip.descriptionText}"></jstl:out>


		<!-- 
<input type="button" name="cancel" class="button btn-primary" value="<spring:message code="trip.cancel" />" onclick="javascript: window.location.replace('welcome/index.do');" />
 -->
		<br>

	</div>
	<jstl:if test="${deletable==true}">

		<button class="btnAccept" type="button" class="btn btn-primary"
			onclick="javascript: relativeRedir('/trip/petShipper/edit.do?tripId=${trip.id}')">
			<spring:message code="trip.edit" />
		</button>
		<br>
		<br>
	</jstl:if>
</div>

<div id="googleMap"></div>

<!-- Add Google Maps -->

<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
	function initialize() {

		//Needed to display the route
		var directionsService = new google.maps.DirectionsService();
		var directionsDisplay = new google.maps.DirectionsRenderer();

		/* var country = "${searchSuppliersForm.address}"; */
		var mapProp = {
			zoom : 12,
			scrollwheel : true,
			draggable : true,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);

		//We set the map to display the render
		directionsDisplay.setMap(map);

		calculateAndDisplayRoute(directionsService, directionsDisplay);

		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);
	}

	//Function to calculate and display the route
	function calculateAndDisplayRoute(directionsService, directionsDisplay) {
		var origin = "${trip.startCity}";
		var destination = "${trip.endCity}";
		directionsService.route({
			origin : origin,
			destination : destination,
			travelMode : google.maps.TravelMode.DRIVING
		}, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
			} else {
				window.alert('Direction request failed due to ' + status);
			}
		});
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>