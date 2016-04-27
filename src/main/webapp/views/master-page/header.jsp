<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<div id="divTop"></div>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="" onclick="jumpTo('./');">
		 <img alt="PetCare" width="50%" src="images/logo-header.png"/> 
		<!--PetCare-->
		</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
		<security:authorize access="hasRole('ADMIN')">
		<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.complaint" />
				<span class="caret"></span></a>
				<ul class="dropdown-menu">
			<li><a href="" onclick="jumpTo('complaint/administrator/listComplaintAdminNotSolved.do');"><spring:message code="master.page.complaint.listComplaintAdminNotSolved" /></a></li>
			<li><a href="" onclick="jumpTo('complaint/administrator/listWithoutAdmin.do');"><spring:message code="master.page.complaint.listComplaintWithoutAdmin" /></a></li>
			<li><a href="" onclick="jumpTo('complaint/administrator/listToSolve.do');"><spring:message code="master.page.complaint.listComplaintToSolve" /></a></li>
										
				</ul>
			
			
			<li><a href="" onclick="jumpTo('administrator/dashboard.do');">
			<spring:message code="master.page.administrator.dashboard" />
			<!--<span class="glyphicon glyphicon-user"></span>-->
			</a></li>	
			
			<li><a href="" onclick="jumpTo('administrator/administrator/displayOwn.do');">
			<spring:message code="master.page.myProfile" />
			<!--<span class="glyphicon glyphicon-user"></span>-->
			</a></li>	
			
			<li><a href="" onclick="jumpTo('supplier/administrator/list.do');">
			<spring:message code="master.page.supplier.list" />
			<!--<span class="glyphicon glyphicon-user"></span>-->
			</a></li>	
			
			<li><a href="" onclick="jumpTo('search/searchSuppliers.do');">
			<spring:message code="master.page.searchSitters" />
			<!--<span class="glyphicon glyphicon-user"></span>-->
			</a></li>
			
			<li><a href="" onclick="jumpTo('booking/administrator/listToPay.do');">
			<spring:message code="master.page.payShipper" />
			<!--<span class="glyphicon glyphicon-user"></span>-->
			</a></li>	
			
			
			
			
			
		</security:authorize>
		
		
		<security:authorize access="hasRole('PETOWNER')">
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.complaint" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
				<ul class="dropdown-menu">
						<li><a href="" onclick="jumpTo('complaint/customer/create.do');"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerId.do');"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerIdNotSolved.do');"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
				</ul>
			</li>	
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.booking" />
			<!--<span class="glyphicon glyphicon-book"></span>-->
			<span class="caret"></span></a>
          		<ul class="dropdown-menu">
						
						<li><a href="" onclick="jumpTo('booking/petOwner/listBookingCanCancelPetOwner.do');"><spring:message code="master.page.booking.cancel" /></a></li>
						<li><a href="" onclick="jumpTo('booking/petOwner/list.do');"><spring:message code="master.page.booking.list" /></a></li>
						<li><a href="" onclick="jumpTo('petSitter/listToBook.do');"><spring:message code="master.page.booking.bookpetSitter" /></a></li>
					
				</ul>
			</li>
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.petSitter.review" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
		    	<ul class="dropdown-menu">
			<li><a href="" onclick="jumpTo('review/petOwner/list.do')"><spring:message code="master.page.petsitter.review.list"/></a></li>
			
				</ul>	
			
			<li><a href="" onclick="jumpTo('petOwner/petOwner/displayOwn.do');">
			<spring:message code="master.page.myProfile" />
			<!--<span class="glyphicon glyphicon-user"></span>-->
			</a></li>				

			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.pets" />
			<!--<span class="glyphicon glyphicon-piggy-bank"></span>-->
			<span class="caret"></span></a>
          		<ul class="dropdown-menu">
					<li><a href="" onclick="jumpTo('pet/petOwner/list.do');"><spring:message code="master.page.list" /></a></li>				
					<li><a href="" onclick="jumpTo('pet/petOwner/create.do');"><spring:message code="master.page.create" /></a></li>	
				</ul>
			</li>
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.trips" />
			<!--<span class="glyphicon glyphicon-piggy-bank"></span>-->
			<span class="caret"></span></a>
          		<ul class="dropdown-menu">
					<li><a href="" onclick="jumpTo('trip/petOwner//findTrips.do');"><spring:message code="master.page.tripsToRegister" /></a></li>				
					
				</ul>
			</li>
			
			
			
			
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.language" />
			<!--<span class="glyphicon glyphicon-cog"></span>-->
			<span class="caret"></span></a>
          	<ul class="dropdown-menu">
            	<li> <a href="" onclick="jumpTo('./?language=en');"><img width="35" height="23" src="images/United-Kingdom.png">EN</a></li>
            	<li><a href="" onclick="jumpTo('./?language=es');"><img width="35" height="23" src="images/Spain.png">ESP</a></li>
          	</ul>
        	</li>				

		</security:authorize>
		
		<security:authorize access="hasRole('PETSHIPPER')">
			
			<li class="dropdown"><a class="dropdown-toffle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.vehicle"/>
			<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="" onclick="jumpTo('vehicle/petShipper/list.do')"><spring:message code="master.page.vehicle.list" /></a></li>
					<li><a href="" onclick="jumpTo('vehicle/petShipper/create.do')"><spring:message code="master.page.vehicle.create" /></a></li>
				</ul>
			</li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.trips" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
		    	<ul class="dropdown-menu">
					
					
						<li><a href="" onclick="jumpTo('trip/petShipper/listWithRegistrations.do')"><spring:message code="master.page.tripRegistrations" /></a></li>
						<li><a href="" onclick="jumpTo('trip/petShipper/list.do')"><spring:message code="master.page.myTrips" /></a></li>
						<li><a href="" onclick="jumpTo('trip/petShipper/create.do')"><spring:message code="master.page.createTrip" /></a></li>
			
				</ul>
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.complaint" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
		    	<ul class="dropdown-menu">
					
					
						
						<li><a href="" onclick="jumpTo('complaint/customer/create.do')"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerId.do')"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerIdNotSolved.do')"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
			
				</ul>
				
				
			
				
				
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.petSitter.review" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
		    	<ul class="dropdown-menu">
			<li><a href="" onclick="jumpTo('review/supplier/list.do')"><spring:message code="master.page.petsitter.review.list"/></a></li>
			
				</ul>
				
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.booking" />
			
			
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
		    	<ul class="dropdown-menu">
		    	<li><a href="" onclick="jumpTo('booking/supplier/listBookingAccepted.do')"><spring:message code="master.page.booking.listBookingAccepted" /></a></li>
		    	<li><a href="" onclick="jumpTo('booking/supplier/listPendingSupplier.do')"><spring:message code="master.page.booking.listPendingSupplier" /></a></li> 
		    	</ul>
		    	</li>
		    
		    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.language" />
			<!--<span class="glyphicon glyphicon-cog"></span>-->
			<span class="caret"></span></a>
          	<ul class="dropdown-menu">
            	<li> <a href="" onclick="jumpTo('./?language=en');"><img width="35" height="23" src="images/United-Kingdom.png">EN</a></li>
            	<li><a href="" onclick="jumpTo('./?language=es');"><img width="35" height="23" src="images/Spain.png">ESP</a></li>
          	</ul>
        	</li>	
			
		
	</security:authorize>
		
		<security:authorize access="hasRole('PETSITTER')">
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.complaint" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
				<ul class="dropdown-menu">
						<li><a href="" onclick="jumpTo('complaint/customer/create.do');"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerId.do');"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerIdNotSolved.do');"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
				</ul>
			</li>
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.petSitter.review" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
		    	<ul class="dropdown-menu">
			<li><a href="" onclick="jumpTo('review/supplier/list.do')"><spring:message code="master.page.petsitter.review.list"/></a></li>
			
				</ul>
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.booking" />
			<!--<span class="glyphicon glyphicon-book"></span>-->
			<span class="caret"></span></a>
          		<ul class="dropdown-menu">
						
						<li><a href="" onclick="jumpTo('booking/supplier/listBookingAccepted.do');"><spring:message code="master.page.booking.listBookingAccepted" /></a></li>
						<li><a href="" onclick="jumpTo('booking/supplier/listPendingSupplier.do');"><spring:message code="master.page.booking.listPendingSupplier" /></a></li>
				</ul>
			</li>

			<li><a href="" onclick="jumpTo('petSitter/petSitter/displayOwn.do');"><spring:message code="master.page.myProfile" /></a></li>			

			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.pets" />
			<!--<span class="glyphicon glyphicon-piggy-bank"></span>-->
			<span class="caret"></span></a>
          		<ul class="dropdown-menu">
					<li><a href="" onclick="jumpTo('pet/petSitter/list.do');"><spring:message code="master.page.list" /></a></li>				
					<li><a href="" onclick="jumpTo('pet/petSitter/create.do');"><spring:message code="master.page.create" /></a></li>	
				</ul>
			</li>
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.petSitter.review" />
			<!--<span class="glyphicon glyphicon-bell"></span>-->
			<span class="caret"></span></a>
		    	<ul class="dropdown-menu">
			<li><a href="" onclick="jumpTo('review/supplier/list.do')"><spring:message code="master.page.petsitter.review.list"/></a></li>
			
				</ul>
			<li><a href="" onclick="jumpTo('search/searchSuppliers.do');"><spring:message code="master.page.searchSitters" /></a></li>
			
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.petSitter.invite" />
			<!--<span class="glyphicon glyphicon-piggy-bank"></span>-->
			<span class="caret"></span></a>
          		<ul class="dropdown-menu">
					<li><a href="" onclick="jumpTo('petSitter/invite.do');"><spring:message code="master.page.petSitter.inviteText" /></a></li>				
				</ul>
			</li>
			
		</security:authorize>
		
		
		<security:authorize access="hasRole('COMPANY')">
		
		
		<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.complaint" />
			<span class="caret"></span></a>
			
				<ul class="dropdown-menu">
					
					
						
						<li><a href="" onclick="jumpTo('complaint/customer/create.do')"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerId.do')"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="" onclick="jumpTo('complaint/customer/listComplaintCustomerIdNotSolved.do')"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
				</ul>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
		<spring:message	code="master.page.booking" />
		<span class="caret"></span></a>
					<ul class="dropdown-menu">
					
					
						<li><a href="" onclick="jumpTo('booking/supplier/listBookingAccepted.do')"><spring:message code="master.page.booking.listBookingAccepted" /></a></li>
					<li><a href="" onclick="jumpTo('booking/supplier/listPendingSupplier.do')"><spring:message code="master.page.booking.listPendingSupplier" /></a></li>
						
				</ul>
				
				
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="master.page.petSitter.review"/>
				<span class="caret"></span></a>
					<ul class="dropdown-menu">
					
					<li><a href="" onclick="jumpTo('review/supplier/list.do')"><spring:message code="master.page.petsitter.review.list"/></a></li>
				</ul>
		
			
							
			
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
			<div hidden="true">
			<a id="autoClick" href="" data-toggle="modal" data-target="#loginForm">
			hidden
			</a>
			</div>
			<li><a style="cursor: pointer;" onclick="clickLogin();"><spring:message code="master.page.login" /></a></li>
        	<li><a href="#users"><spring:message code="master.page.users" /></a></li>
        	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.language" /><span class="caret"></span></a>
          	<ul class="dropdown-menu">
            	<li> <a href="" onclick="jumpTo('./?language=en');"><img width="35" height="23" src="images/United-Kingdom.png">EN</a></li>
            	<li><a href="" onclick="jumpTo('./?language=es');"><img width="35" height="23" src="images/Spain.png">ESP</a></li>
          	</ul>
        	</li>

		</security:authorize>
	
		<security:authorize access="isAuthenticated()">
			<li><a href="" onclick="jumpTo('j_spring_security_logout');">
			<spring:message code="master.page.logout" />
			<!--<span class="glyphicon glyphicon-remove-sign"></span>-->
			</a></li>
		</security:authorize>
	

</div>
</div>
</nav>







<script>

function starting(){
	if("${autoLogin}"=='true'){
		document.getElementById('autoClick').click();
	}
}

function jumpTo(url){
	window.location.replace(url);
}

function clickLogin(){
	jumpTo('./welcome/index.do?autoLogin=true');
}
window.onload = starting;
</script>
