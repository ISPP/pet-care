<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="toptop">
</div>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img class="todoAncho" src="images/SlideA.png" alt="Presentation 1" width="1200" height="600">
      </div>

      <div class="item">
        <img class="todoAncho" src="images/SlideB.png" alt="Presentation 2" width="1200" height="600">
      </div>
    
      <div class="item">
        <img class="todoAncho" src="images/SlideC.png" alt="Presentation 3" width="1200" height="600">
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideD.png" alt="Presentation 4" width="1200" height="600">
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideE.png" alt="Presentation 5" width="1200" height="600">
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideF.png" alt="Presentation 6" width="1200" height="600">
      </div>
    
      <div class="item">
        <img class="todoAncho" src="images/SlideG.png" alt="Presentation 7" width="1200" height="600">
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideH.png" alt="Presentation 8" width="1200" height="600">
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideI.png" alt="Presentation 9" width="1200" height="600">
      </div>
      
      
      <div class="carousel-caption center-bottom container-fluid" style="position:absolute;top:90px;">
          	<form:form action="search/searchSuppliers.do" modelAttribute="searchSuppliersForm" method="POST">
			<form:hidden path="id"/>
			<fieldset >
			<h2><spring:message code="master.page.searchSuppliers"/></h2>
			<spring:message var="startD" code="sitter.startDate"/>
			<form:input id="datepicker" class="blackL datepicker" path="startDate"  placeholder="${startD}"/>
			<form:errors path="startDate" cssClass="error" />
			
			<spring:message var="endD" code="sitter.endDate"/>
			<form:input class="blackL datepicker" path="endDate" placeholder="${endD}"/>
			<form:errors path="endDate" cssClass="error" />
			
			<spring:message var="addrs" code="sitter.address"/>
			<form:input class="blackL" path="address" placeholder="${addrs}"/>
			<form:errors path="address" cssClass="error" />
			<br/>
			<form:label path="type">
				<spring:message code="supplier.type" />
			</form:label>	
			<form:select path="type" cssStyle="color:black">
				<form:option value="1"><spring:message code="supplier.petSitter" /></form:option>
				<form:option value="2"><spring:message code="supplier.petShipper" /></form:option>
				<form:option value="3"><spring:message code="supplier.company" /></form:option>
			</form:select>
			<form:errors path="type" cssClass="error" />
			
			<acme:submit code="sitter.search.go" name="search" />
			</fieldset>
			</form:form>


<br/>
<br/>
        </div>  
    </div>

</div>
<div class="container text-center">

	<!-- List sitters -->
	
	<jstl:if test="${searchSuppliersForm.type == 1}">
		<jstl:forEach var="petSitter" items="${suppliers}">
		 <jstl:if test="${toBook==true}">
		 	<jstl:set var="styla" value="cursor: pointer;" />
		 	<jstl:set var="clicka" value="location.href='booking/petOwner/create.do?petSitterId=${petSitter.id}&startMoment=${searchSuppliersForm.startDate}&endMoment=${searchSuppliersForm.endDate}';" />
		 </jstl:if>
		 <div style="${styla}" onclick="${clicka}"  class="col-md-4 panel panel-default">
		 	<div class="wrap">
		 	
		 	<img class="max-h-little img-center" alt="Your PETSITTER" src="images/petOwner-index.jpg">
			<span  class="hM3 carousel-caption desc"><jstl:out value=" ${petSitter.priceNight}*"/>&#8364;</span>
			</div>
			
			<div>
			<p class="list-name"><jstl:out value="${petSitter.name}"/><hr class="linea-pegada"/>
			<jstl:out value=" ${petSitter.address}"/>
			<br/>
			<spring:message code="sitter.priceHour"/>: <jstl:out value=" ${petSitter.priceHour}"/> &#8364;
			</div>
			
			<jstl:if test="${petSitter.rating<1.0}">
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>

        	<jstl:if test="${petSitter.rating>=1.0 and petSitter.rating<2.0}">
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petSitter.rating>=2.0 and petSitter.rating<3.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petSitter.rating>=3.0 and petSitter.rating<4.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petSitter.rating>=4.0 and petSitter.rating<5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petSitter.rating==5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
        	</jstl:if>
			
		</div>
		</jstl:forEach>
		<jstl:if test="${suppliers.size()==0}">
			<h2><spring:message code="search.noResults"/></h2>
		</jstl:if>
		<jstl:if test="${toBook==false}">
		<spring:message code="search.mustRegister"/><br/>
		</jstl:if>
		<spring:message code="search.priceShowsNight"/>
	</jstl:if>
	
	<!-- List shippers -->
	
	<jstl:if test="${searchSuppliersForm.type == 2}">
		<jstl:forEach var="petShipper" items="${suppliers}">
		 <div style="cursor: pointer;" onclick="location.href='trip/petShipper/listByShipper.do?petShipperId=${petShipper.id}';"  class="col-md-4 panel panel-default">
		 	<div class="wrap">
		 	<jstl:if test="${toBook==true}">
		 	<a href="trip/petShipper/listByShipper.do?petShipperId=${petShipper.id}">
		 	<img class="max-h-little img-center" alt="Your PETSHIPPER" src="images/petOwner-index.jpg">
			</a>
		 	</jstl:if>
			
			<jstl:if test="${toBook == false}">
			<img class="max-h-little img-center" alt="Your PETSHIPPER" src="images/petOwner-index.jpg">
			</jstl:if>
			</div>
			
			<div>
			<p class="list-name"><jstl:out value="${petShipper.name}"/><hr class="linea-pegada"/>
			<jstl:out value=" ${petShipper.address}"/>
			<br/>
			</div>
			
		</div>
		</jstl:forEach>
		<jstl:if test="${suppliers.size()==0}">
			<h2><spring:message code="search.noResults"/></h2>
		</jstl:if>
		<jstl:if test="${toBook==false}">
		<spring:message code="search.mustRegister"/><br/>
		</jstl:if>
		<spring:message code="search.priceShowsNight"/>
	</jstl:if>
	
	<!-- List companies -->
	
	<jstl:if test="${searchSuppliersForm.type == 3}">
		<jstl:forEach var="company" items="${suppliers}">
		 <div style="cursor: pointer;" onclick="location.href='booking/petOwner/createForCompany.do?companyId=${company.id}&startMoment=${searchSuppliersForm.startDate}&endMoment=${searchSuppliersForm.endDate}';"  class="col-md-4 panel panel-default">
		 	<div class="wrap">
		 	<jstl:if test="${toBook==true}">
		 	<img class="max-h-little img-center" alt="Your COMPANY" src="images/petOwner-index.jpg">
			<span  class="hM3 carousel-caption desc"><jstl:out value=" ${company.pricePerDay}*"/>&#8364;</span>
		 	</jstl:if>
			
			<jstl:if test="${toBook == false}">
			<img class="max-h-little img-center" alt="Your COMPANY" src="images/petOwner-index.jpg">
			<span  class="hM3 carousel-caption desc"><jstl:out value=" ${company.pricePerDay}*"/>&#8364;</span>
			</jstl:if>
			</div>
			
			<div>
			<p class="list-name"><jstl:out value="${company.name}"/><hr class="linea-pegada"/>
			<jstl:out value=" ${company.address}"/>
			<br/>
			</div>
		</div>
		</jstl:forEach>
		<jstl:if test="${suppliers.size()==0}">
			<h2><spring:message code="search.noResults"/></h2>
		</jstl:if>
		<jstl:if test="${toBook==false}">
		<spring:message code="search.mustRegister"/><br/>
		</jstl:if>
		<spring:message code="search.priceShowsNight"/>
	</jstl:if>
	
</div>


<div id="googleMap"></div>

<!-- Add Google Maps -->

<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>

function initialize() {
	var geocoder = new google.maps.Geocoder();
	var country = "${searchSuppliersForm.address}";
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
