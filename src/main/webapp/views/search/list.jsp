

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="images/SlideA.png" alt="Presentation 1" width="1200" height="600">
      </div>

      <div class="item">
        <img src="images/SlideB.png" alt="Presentation 2" width="1200" height="600">
      </div>
    
      <div class="item">
        <img src="images/SlideC.png" alt="Presentation 3" width="1200" height="600">
      </div>
      
      <div class="item">
        <img src="images/SlideD.png" alt="Presentation 4" width="1200" height="600">
      </div>
      
      <div class="item">
        <img src="images/SlideE.png" alt="Presentation 5" width="1200" height="600">
      </div>
      
      <div class="item">
        <img src="images/SlideF.png" alt="Presentation 6" width="1200" height="600">
      </div>
    
      <div class="item">
        <img src="images/SlideG.png" alt="Presentation 7" width="1200" height="600">
      </div>
      
      <div class="item">
        <img src="images/SlideH.png" alt="Presentation 8" width="1200" height="600">
      </div>
      
      <div class="item">
        <img src="images/SlideI.png" alt="Presentation 9" width="1200" height="600">
      </div>
      
      
      <div class="carousel-caption center-bottom">
          	<form:form action="search/searchSitters.do" modelAttribute="searchSittersForm" method="POST">
			<form:hidden path="id"/>
			<fieldset >
			<h2><spring:message code="master.page.searchSitters"/></h2>
			<spring:message var="startD" code="sitter.startDate"/>
			<form:input id="datepicker" class="blackL datepicker" path="startDate"  placeholder="${startD}"/>
			<form:errors path="startDate" cssClass="error" />
			
			<spring:message var="endD" code="sitter.endDate"/>
			<form:input class="blackL datepicker" path="endDate" placeholder="${endD}"/>
			<form:errors path="endDate" cssClass="error" />
			
			<spring:message var="addrs" code="sitter.address"/>
			<form:input class="blackL" path="address" placeholder="${addrs}"/>
			<form:errors path="address" cssClass="error" />
			
			<acme:submit code="sitter.search.go" name="search" />
			</fieldset>
			</form:form>


<br/>
<br/>
        </div>  
    </div>

</div>
<div class="container text-center">
	<jstl:forEach var="petSitter" items="${sitters}">
	 <div class="col-md-4 panel panel-default">
	 	<div class="wrap">
	 	<jstl:if test="${toBook==true}">
	 	<a href="booking/petOwner/create.do?petSitterId=${petSitter.id}&startMoment=${searchSittersForm.startDate}&endMoment=${searchSittersForm.endDate}">
	 	<img class="max-h-little img-center" alt="Your PETSITTER" src="images/petOwner-index.jpg">
		<span  class="hM3 carousel-caption desc"><jstl:out value=" ${petSitter.priceNight}*"/>&#8364;</span>
		</a>
	 	</jstl:if>
		
		<jstl:if test="${toBook == false}">
		<img class="max-h-little img-center" alt="Your PETSITTER" src="images/petOwner-index.jpg">
		<span  class="hM3 carousel-caption desc"><jstl:out value=" ${petSitter.priceNight}*"/>&#8364;</span>
		</jstl:if>
		</div>
		
		<div>
		<p class="list-name"><jstl:out value="${petSitter.name}"/><hr class="linea-pegada"></p>
		<jstl:out value=" ${petSitter.address}"/>
		<br/>
		<spring:message code="sitter.priceHour"/>: <jstl:out value=" ${petSitter.priceHour}"/> &#8364;
		</div>
		
	</div>
	</jstl:forEach>
	<jstl:if test="${sitters.size()==0}">
		<h2><spring:message code="search.noResults"/></h2>
	</jstl:if>
	<jstl:if test="${toBook==false}">
	<spring:message code="search.mustRegister"/><br/>
	</jstl:if>
	<spring:message code="search.priceShowsNight"/>
</div>


<div id="googleMap"></div>


<!-- Add Google Maps -->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>


function initialize() {
	var geocoder = new google.maps.Geocoder();
	var country = "${searchSittersForm.address}";
	var mapProp = {
			zoom:12,
			scrollwheel:true,
			draggable:true,
			mapTypeId:google.maps.MapTypeId.ROADMAP
			};
	var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
	geocoder.geocode( { 'address': country }, function(results, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
	        map.setCenter(results[0].geometry.location);
	        var marker = new google.maps.Marker({
	            map: map,
	            position: results[0].geometry.location
	          });
	    } else {
	        alert("Could not find location: " + location);
	    }
	});

	


var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);



}

google.maps.event.addDomListener(window, 'load', initialize);




</script>
