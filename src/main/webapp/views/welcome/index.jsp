<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="images/slide1.jpg" alt="viaja sin preocupaciones" width="1200" height="700">
        <div class="carousel-caption">
        </div>      
      </div>

      <div class="item">
        <img src="images/slide2.jpg" alt="Chicago" width="1200" height="700">
        <div class="carousel-caption">
        </div>      
      </div>
    
      <div class="item">
        <img src="images/slide3.jpg" alt="Los Angeles" width="1200" height="700">
        <div class="carousel-caption">
        </div>      
      </div>
      
      <div class="item">
        <img src="images/slide4.jpg" alt="Los Angeles" width="1200" height="700">
        <div class="carousel-caption">
        </div>      
      </div>
      
      <div class="item">
        <img src="images/slide5.jpg" alt="Los Angeles" width="1200" height="700">
        <div class="carousel-caption">
        </div>      
      </div>
      
      <div class="item">
        <img src="images/slide6.jpg" alt="Los Angeles" width="1200" height="700">
        <div class="carousel-caption">
        </div>      
      </div>
    </div>

</div>

<div id="users" class="container text-center">
  <h3><spring:message	code="master.page.petcare" /></h3>
  <p><em><spring:message	code="master.page.petcareText1" /></em></p>
  <p><spring:message	code="master.page.petcareText2" /></p>
  <br>
  <div class="row">
    <div class="col-sm-6">
      <p class="text-center"><strong><spring:message code="master.page.petOwner" /></strong></p><br>
      <a href="#demo" data-toggle="collapse">
        <img src="images/petOwner-index.jpg" class="img-circle person" alt="Pet Owner" width="255" height="255">
      </a>
      <div id="demo" class="collapse">
        <p><spring:message code="master.page.petOwnertext1" /></p>
        <p><spring:message code="master.page.petOwnertext2" /></p>
        <p><spring:message code="master.page.petOwnertext3" /></p>
      </div>
    </div>
    <div class="col-sm-6">
      <p class="text-center"><strong><spring:message code="master.page.petSitter" /></strong></p><br>
      <a href="#demo2" data-toggle="collapse">
        <img src="images/petOwner-index.jpg" class="img-circle person" alt="Pet Sitter" width="255" height="255">
      </a>
      <div id="demo2" class="collapse">
        <p><spring:message code="master.page.petSittertext1" /></p>
        <p><spring:message code="master.page.petSittertext2" /></p>
        <p><spring:message code="master.page.petSittertext3" /></p>
      </div>
    </div>
  </div>
</div>
 

<div id="googleMap"></div>


	 <!-- Modal -->
  <div class="modal fade" id="loginForm" role="dialog">
    <div class="modal-dialog">
    
    
    
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
        </div>
        <div class="modal-body">
          <form:form action="j_spring_security_check" modelAttribute="credentials">
            <div class="form-group">
              <form:input class="form-control" path="username" placeholder="Username"/>	
				<form:errors class="error" path="username" />
            </div>
            <div class="form-group">
              <form:password class="form-control" path="password" placeholder="Password"/>	
				<form:errors class="error" path="password" />
            </div>
              <button type="submit" class="btn btn-block">Go! 
                <span class="glyphicon glyphicon-ok"></span>
              </button>
          </form:form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
            <span class="glyphicon glyphicon-remove"></span> Cancel
          </button>
        </div>
      </div>
    </div>
  </div>


<!-- Add Google Maps -->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
var myCenter = new google.maps.LatLng(41.878114, -87.629798);

function initialize() {
var mapProp = {
center:myCenter,
zoom:12,
scrollwheel:false,
draggable:false,
mapTypeId:google.maps.MapTypeId.ROADMAP
};

var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

var marker = new google.maps.Marker({
position:myCenter,
});

marker.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>

