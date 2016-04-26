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
      <security:authorize access="isAnonymous() or hasRole('PETOWNER')">

     
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
       
       </security:authorize>
 </div> 
 
</div>

<div id="users" class="container text-center">
  <!-- <h3><spring:message	code="master.page.petcare" /></h3> -->
  <img width="30%" src="images/logo1lineW.png" alt="PetCare"/>
  <p><em><spring:message	code="master.page.petcareText1" /></em></p>
  <br/>
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
		<a class="btn btn-default" href="petOwner/create.do"><spring:message code="master.page.register.petOwner" /></a>
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
        <a class="btn btn-default" href="petSitter/create.do"><spring:message code="master.page.register.petSitter" /></a>
      </div>
    </div>
  </div>
</div>
 
<!-- 
<div id="googleMap"></div>
 -->
<security:authorize access="isAnonymous()">
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

</security:authorize>
<!-- Add Google Maps -->
<!-- 
<script src="http://maps.googleapis.com/maps/api/js"></script>
-->
<script>

function showEr(er){
    $("#danger-alert").hide();
    alert('hola');
    if(er==true){
        $("#danger-alert").alert();
        $("#danger-alert").fadeTo(2000, 500).slideUp(500, function(){
       $("#danger-alert").alert('close');
    });
    }
};

//var myCenter = new google.maps.LatLng(41.878114, -87.629798);

//function initialize() {
//var mapProp = {
//center:myCenter,
//zoom:12,
//scrollwheel:false,
//draggable:false,
//mapTypeId:google.maps.MapTypeId.ROADMAP
//};

//var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

//var marker = new google.maps.Marker({
//position:myCenter,
//});

//marker.setMap(map);
//}

//google.maps.event.addDomListener(window, 'load', initialize);




</script>

