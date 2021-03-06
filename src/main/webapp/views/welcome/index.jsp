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
<!-- Si no es movil -->
<div id="myCarousel" class="carousel slide zonaResponsiva" data-ride="carousel">
    <!-- Indicators -->

    <!-- Wrapper for slides -->
    <div id="alturaControlada" class="carousel-inner" role="listbox" >
      <div class="item active">
        <img class="todoAncho" src="images/SlideB.png" alt="Presentation 1" >
      </div>

      <div class="item">
        <img class="todoAncho" src="images/SlideC.png" alt="Presentation 2" >
      </div>
    
      <div class="item">
        <img class="todoAncho" src="images/SlideD.png" alt="Presentation 3" >
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideA.png" alt="Presentation 4" >
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideE.png" alt="Presentation 5" >
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideF.png" alt="Presentation 6" >
      </div>
    
      <div class="item">
        <img class="todoAncho" src="images/SlideG.png" alt="Presentation 7"  >
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideH.png" alt="Presentation 8" >
      </div>
      
      <div class="item">
        <img class="todoAncho" src="images/SlideI.png" alt="Presentation 9" >
      </div>
      <security:authorize access="isAnonymous() or hasRole('PETOWNER')">

     
		 <div class="carousel-caption center-bottom container-fluid buscadorResponsivo" style="position:absolute;">
          	<form:form action="search/searchSuppliers.do" modelAttribute="searchSuppliersForm" method="POST">

			<form:hidden path="id"/>
			<fieldset class="col-xs-12">
			<h2><spring:message code="master.page.searchSuppliers"/></h2>
			<div class="col-xs-12">
			<spring:message var="startD" code="sitter.startDate"/>
			<form:input id="datepicker" class="blackL datepicker" path="startDate"  placeholder="${startD}"/>
			<form:errors path="startDate" cssClass="error" />
			<spring:message var="endD" code="sitter.endDate"/>
			<form:input class="blackL datepicker" path="endDate" placeholder="${endD}"/>
			<form:errors path="endDate" cssClass="error" />
			<spring:message var="addrs" code="sitter.address"/>
			<form:input class="blackL addrss" path="address" placeholder="${addrs}"/>
			<form:errors path="address" cssClass="error" />
			</div>
			<div class="col-xs-12">
			<form:label path="type">
				<spring:message code="supplier.type" />
			</form:label>	
			<form:select path="type" cssStyle="color:black">
				<form:option value="1"><spring:message code="supplier.petSitter" /></form:option>
				<form:option value="2"><spring:message code="supplier.petShipper" /></form:option>
				<form:option value="3"><spring:message code="supplier.company" /></form:option>
			</form:select>
			<form:errors path="type" cssClass="error" />
			</div>
				<acme:submit code="sitter.search.go" name="search" />
			<br/>
		
			</fieldset>
			</form:form>


<br/>
<br/>
        
        </div> 
       
       </security:authorize>
 </div> 
 
</div>


<!-- Si es movil -->
<div id="myCarousel2" class="zonaResponsiva">
    <!-- Indicators -->

    <!-- Wrapper for slides -->
    <div id="alturaControlada2">
      <security:authorize access="isAnonymous() or hasRole('PETOWNER')">

     
		 <div class="text-center buscadorResponsivo">
          	<form:form action="search/searchSuppliers.do" modelAttribute="searchSuppliersForm" method="POST">

			<form:hidden path="id"/>
			<fieldset class="col-xs-12">
			<h2 id="tituloMobile"><spring:message code="master.page.searchSuppliers"/></h2>
			<div class="col-xs-12">
			<spring:message var="startD" code="sitter.startDate"/>
			<form:input id="datepicker" class="blackL datepicker" path="startDate"  placeholder="${startD}"/>
			<form:errors path="startDate" cssClass="error" />
			</div>
			<div class="col-xs-12">
			<spring:message var="endD" code="sitter.endDate"/>
			<form:input class="blackL datepicker" path="endDate" placeholder="${endD}"/>
			<form:errors path="endDate" cssClass="error" />
			</div>
			<div class="col-xs-12">
			<spring:message var="addrs" code="sitter.address"/>
			<form:input class="blackL addrss" path="address" placeholder="${addrs}"/>
			<form:errors path="address" cssClass="error" />
			</div>
			<div class="col-xs-12">
			<form:label path="type">
				<spring:message code="supplier.type" />
			</form:label>	
			<form:select path="type" cssStyle="color:black">
				<form:option value="1"><spring:message code="supplier.petSitter" /></form:option>
				<form:option value="2"><spring:message code="supplier.petShipper" /></form:option>
				<form:option value="3"><spring:message code="supplier.company" /></form:option>
			</form:select>
			<form:errors path="type" cssClass="error" />
			</div>
				<acme:submit code="sitter.search.go" name="search" />
			<br/>
		
			</fieldset>
			</form:form>


<br/>
<br/>
        
        </div> 
       
       </security:authorize>
 </div> 
 
</div>




<div id="users" class="container text-center noCortes">
  <!-- <h3><spring:message	code="master.page.petcare" /></h3> -->
  <img width="30%" src="images/logo1lineW.png" alt="PetCare"/>
  <br/>
  <br/>
  <p><spring:message	code="master.page.petcareText2" /></p>
  <br>
  <div class="row">
    <div class="col-sm-3">
      <span class="text-uppercase petOwnerRegister-text"><strong><spring:message code="master.page.petowner" /></strong></span>
      <a href="#demo" data-toggle="collapse">
        <img src="images/petOwner-index.jpg" class="img-circle person" alt="Pet Owner" width="255" height="255">
      </a>
      <div id="demo" class="collapse">
        <p>
			<spring:message code="master.page.petOwnertext1_1" />
        	<span class="text-uppercase petOwnerRegister-text"><spring:message code="master.page.petOwnertext1_2" /></span>
        	<spring:message code="master.page.petOwnertext1_3" />
        	<span class="text-uppercase petOwnerRegister-text"><spring:message code="master.page.petOwnertext1_4" /></span>
        	<spring:message code="master.page.petOwnertext1_5" />
        	<span class="text-uppercase petOwnerRegister-text"><spring:message code="master.page.petOwnertext1_6" /></span>
        	<spring:message code="master.page.petOwnertext1_7" />
        	<span class="text-uppercase petOwnerRegister-text"><spring:message code="master.page.petOwnertext1_8" /></span>
        	<spring:message code="master.page.petOwnertext1_9" />
		</p>
        
		<security:authorize access="!hasAnyRole('PETOWNER','PETSITTER','PETSHIPPER','COMPANY')">
		<a class="btnAcceptNoMargin text-uppercase" href="petOwner/create.do"><spring:message code="master.page.register.petOwner2" /></a>
      	</security:authorize>
      </div>
    </div>
    <div class="col-sm-3">
      <span class="text-uppercase petSitterRegister-text"><strong><spring:message code="master.page.petsitter" /></strong></span>
      <a href="#demo2" data-toggle="collapse">
        <img src="images/petSitter-index.jpg" class="img-circle person" alt="Pet Sitter" width="255" height="255">
      </a>
      <div id="demo2" class="collapse">
        <p>
        	<spring:message code="master.page.petSittertext1_1" />
        	<span class="text-uppercase petSitterRegister-text"><spring:message code="master.page.petSittertext1_2" /></span>
        	<spring:message code="master.page.petSittertext1_3" />
        	<span class="text-uppercase petSitterRegister-text"><spring:message code="master.page.petSittertext1_4" /></span>
        	<spring:message code="master.page.petSittertext1_5" />
        	<span class="text-uppercase petSitterRegister-text"><spring:message code="master.page.petSittertext1_6" /></span>
        </p>
      </div>
    </div>
    <div class="col-sm-3">
      <span class="text-uppercase petShipperRegister-text"><strong><spring:message code="master.page.petshipper" /></strong></span>
      <a href="#demo3" data-toggle="collapse">
        <img src="images/petShipper-index.jpg" class="img-circle person" alt="Pet Shipper" width="255" height="255">
      </a>
      <div id="demo3" class="collapse">
        <p>
        	<spring:message code="master.page.petShippertext1_1" />
        	<span class="text-uppercase petShipperRegister-text"><spring:message code="master.page.petShippertext1_2" /></span>
        	<spring:message code="master.page.petShippertext1_3" />
        	<span class="text-uppercase petShipperRegister-text"><spring:message code="master.page.petShippertext1_4" /></span>
        	<spring:message code="master.page.petShippertext1_5" />
        	<span class="text-uppercase petShipperRegister-text"><spring:message code="master.page.petShippertext1_6" /></span>
        	<spring:message code="master.page.petShippertext1_7" />
       	</p>
       </div>
    </div>
    <div class="col-sm-3">
      <span class="text-uppercase companyRegister-text"><strong><spring:message code="master.page.company" /></strong></span>
      <a href="#demo4" data-toggle="collapse">
        <img src="images/company-index.jpg" class="img-circle person" alt="Companies" width="255" height="255">
      </a>
      <div id="demo4" class="collapse">
        <p>
        	<spring:message code="master.page.companytext1_1" />
        	<span class="text-uppercase companyRegister-text"><spring:message code="master.page.companytext1_2" /></span>
        	<spring:message code="master.page.companytext1_3" />
        	<span class="text-uppercase companyRegister-text"><spring:message code="master.page.companytext1_4" /></span>
        	<spring:message code="master.page.companytext1_5" />
        	<spring:message code="master.page.companytext1_6" />
        	<span class="text-uppercase companyRegister-text"><spring:message code="master.page.companytext1_7" /></span>
        	<spring:message code="master.page.companytext1_8" />
        	<span class="text-uppercase companyRegister-text"><spring:message code="master.page.companytext1_9" /></span>
        </p>
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
          <button type="button" class="close" data-dismiss="modal">�</button>
          <h4><span class="glyphicon glyphicon-lock"></span> <spring:message code="welcome.login"/></h4>
        </div>
        <jstl:if test="${showError==true}">
				<div class="alert alert-danger" id="danger-alert" onload="alert('hola');">
			    <button type="button" class="close" data-dismiss="alert">x</button>
			    <strong><spring:message code="master.page.danger"/></strong>
			    <spring:message code="master.page.credentialError"/>
				</div>
			</jstl:if>
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
              <button type="submit" class="btnAcceptNoMargin btn-block"><spring:message code="welcome.go"/> 
                <span class="glyphicon glyphicon-ok"></span>
              </button>
          </form:form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btnCancelNoMargin pull-left" data-dismiss="modal">
            <span class="glyphicon glyphicon-remove"></span> <spring:message code="welcome.cancel"/>
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

