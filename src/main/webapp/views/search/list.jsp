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
		<div id="petSittersDisplay"><!-- Added to create a custom onfo windows in google maps for each marker -->
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
		</div>
	</jstl:if>
	
	<!-- List shippers -->
		<jstl:if test="${searchSuppliersForm.type == 2}">
		<div id="petShippersDisplay"><!-- Added to create a custom onfo windows in google maps for each marker -->
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
			
			<jstl:if test="${petShipper.rating<1.0}">
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>

        	<jstl:if test="${petShipper.rating>=1.0 and petShipper.rating<2.0}">
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petShipper.rating>=2.0 and petShipper.rating<3.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petShipper.rating>=3.0 and petShipper.rating<4.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petShipper.rating>=4.0 and petShipper.rating<5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${petShipper.rating==5.0}">
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
		</div>
	</jstl:if>
	
	<!-- List companies -->
	<jstl:if test="${searchSuppliersForm.type == 3}">
		<div id="companiesDisplay"><!-- Added to create a custom onfo windows in google maps for each marker -->
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
			
			<jstl:if test="${company.rating<1.0}">
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>

        	<jstl:if test="${company.rating>=1.0 and company.rating<2.0}">
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=2.0 and company.rating<3.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=3.0 and company.rating<4.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=4.0 and company.rating<5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating==5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
        	</jstl:if>
			
			</jstl:forEach>
			<jstl:if test="${suppliers.size()==0}">
				<h2><spring:message code="search.noResults"/></h2>
			</jstl:if>
			<jstl:if test="${toBook==false}">
			<spring:message code="search.mustRegister"/><br/>
			</jstl:if>
			<spring:message code="search.priceShowsNight"/>
		</div>
	</jstl:if>
	
</div>


<div id="googleMap"></div>

<!-- Add Google Maps -->

<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>

var prev_infowindow = false; //This is the last infowindow opened (we need it to close it when we open other one)

function createInfoWindow(marker,map, index){
	//We check what type of supplier we have to show
	/*
	 * 1. We get the petSitter/petShipper/company display div -> $("#petSittersDisplay")[0]
	 * 2. To get he results more easily we find the childrens fo the div wich have the wrap class -> $($("#petSittersDisplay")[0]).find(".wrap") 
	 * 3. We get the index we want (the number of the supplier we are going to display) -> $($("#petSittersDisplay")[0]).find(".wrap")[index]
	 * 4. Next we get the parent (the complete supplier div we are going to show) -> $($($("#petSittersDisplay")[0]).find(".wrap")[index]).parent()
	 * 5. As a list is returned we have to get the 1st element (the inmediate parent) -> $($($("#petSittersDisplay")[0]).find(".wrap")[index]).parent()[0]
	 * 6. Finally we get the htmk inside the div -> $($($("#petSittersDisplay")[0]).find(".wrap")[index]).parent().html()
	*/
	var contentString = $($($($("#petSittersDisplay")[0]).find(".wrap")[index]).parent()[0]).html();
	if(contentString == undefined){
		contentString = $($($($("#petShippersDisplay")[0]).find(".wrap")[index]).parent()[0]).html();
		if(contentString == undefined){
			contentString = $($($($("#companiesDisplay")[0]).find(".wrap")[index]).parent()[0]).html();
		}
	}
	
	//We create the new info window
	var infowindow = new google.maps.InfoWindow({
		content: contentString
	});
	
	//We add the onclick handler to the marker
	marker.addListener("click",function(){
		if(prev_infowindow){
			prev_infowindow.close();
		}
		prev_infowindow = infowindow;
		infowindow.open(map,marker);
	});
}

function initialize() {
	var geocoder = new google.maps.Geocoder();
	/* var country = "${searchSuppliersForm.address}"; */
	var mapProp = {
			zoom:12,
			scrollwheel:true,
			draggable:true,
			mapTypeId:google.maps.MapTypeId.ROADMAP
			};
	var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
	var index = 0;
	<jstl:forEach items="${suppliers}" var="supplier">
		//We get all the suppliers we need and we create a marker for each of them
		//console.log("${supplier.address}")
		var _address = "${supplier.address}";
		var title = "${supplier.name} ${supplier.surname}";
		
		var marker = null;
		
		//Custom marker icon with 20x32 px size
		var markerIcon = new google.maps.MarkerImage(
				<jstl:if test="${searchSuppliersForm.type == 1}">
			    	"images/sm_petSitter_marker.png",
			    </jstl:if>
		    	<jstl:if test="${searchSuppliersForm.type == 2}">
			    	"images/sm_petShipper_marker.png",
			    </jstl:if>
			    	<jstl:if test="${searchSuppliersForm.type == 3}">
			    	"images/sm_company_marker.png",
			    </jstl:if>
			    null,
			    null,
			    null,
			    null/*new google.maps.Size(70.04,98.76)*/
		);
		
		geocoder.geocode( { 'address': _address }, function(results, status) {
		    if (status == google.maps.GeocoderStatus.OK) {
		        map.setCenter(results[0].geometry.location);
		        var marker = new google.maps.Marker({
		            map: map,
		            position: results[0].geometry.location,
		            icon: markerIcon
		          });
		        createInfoWindow(marker,map,index);
		        index++;
		    } else {
		        alert("Could not find location: " + location);
		    }
		});
		
		
	</jstl:forEach>
var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>
