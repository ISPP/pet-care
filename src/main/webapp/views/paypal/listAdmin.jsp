
<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


 <div class="col-md-12">
 	<h2><spring:message code="booking.bookings"/></h2><hr>	
 	<jstl:forEach var="booking" items="${bookings}">
 		<div class="col-md-8-2 panel panel-default">
	 		<div class="wrap-2">
	 		
	 		<div>
	 		<h3 class="h3-no-bottom">${booking.supplier.name}</h3>
	 		<table class="text-rigth-2">
	 			<tr>
	 			<td class="table-separate-100">
	 				${booking.supplier.address}
	 			</td>
	 			<td class="table-separate-100">
	 				<fmt:formatDate value="${booking.startMoment}" pattern="dd/MM/yyyy HH:mm" />
	 			</td>
	 			<td class="table-separate-100">
	 				<h2>${booking.price} &#8364;</h2>
	 			</td>
	 			
	 			<td class="table-separate-100">
	 				<a href="paypal/payAdmin.do?id=${booking.id}"><img class="max-h-4 img-left-2"  src="images/dinero.png" /></a>
	 			</td>
	 			</tr>
	 		</table>
	 		<p > <br/></p>
	 		
	</div>
	 	</div>
	 	</div>
 	</jstl:forEach>
 </div>


 <div class="col-md-12">
 	<h2><spring:message code="registration.registrations"/></h2><hr>	
 	<jstl:forEach var="registration" items="${registrations}">
 		<div class="col-md-8-2 panel panel-default">
	 		<div class="wrap-2">
	 		<img class="max-h-4 img-left-2" alt="Care Person" src="images/petOwner-index.jpg">
	 		<img class="max-h-4 img-left-2-1" alt="Your PET" src="images/pet-register.jpg">
	 		<div>
	 		<h3 class="h3-no-bottom">${registration.trip.startCity} -> ${registration.trip.endCity}</h3>
	 		<table class="text-rigth-2">
	 			<tr>
	 			
	 			<td class="table-separate-100">
	 				<fmt:formatDate value="${registration.trip.moment}" pattern="dd/MM/yyyy HH:mm" />
	 			</td>
	 			<td class="table-separate-100">
	 				<h2>${registration.trip.cost} &#8364;</h2>
	 			</td>
	 			</tr>
	 		</table>
	 		<p > <br/></p>
	 		<a href="paypal/payAdminRegistration.do?id=${registration.id}"> <spring:message
						code="paypal.payNow" />
	</a>
	</div>
	 	</div>
	 	</div>
 	</jstl:forEach>
 </div>
 
 
  <div class="col-md-12">
 	<h2><spring:message code="bookingsRembolse.bookingsRembolse"/></h2><hr>	
 	<jstl:forEach var="bookingsRembolse" items="${bookingsRembolse}">
 		<div class="col-md-8-2 panel panel-default">
	 		<div class="wrap-2">
	 		<img class="max-h-4 img-left-2" alt="Care Person" src="images/petOwner-index.jpg">
	 		<img class="max-h-4 img-left-2-1" alt="Your PET" src="images/pet-register.jpg">
	 		<div>
	 		<h3 class="h3-no-bottom">${bookingsRembolse.supplier.name}</h3>
	 		<table class="text-rigth-2">
	 			<tr>
	 			<td class="table-separate-100">
	 				${bookingsRembolse.supplier.address}
	 			</td>
	 			<td class="table-separate-100">
	 				<fmt:formatDate value="${bookingsRembolse.startMoment}"
	pattern="dd/MM/yyyy HH:mm" />
	 			</td>
	 			<td class="table-separate-100">
	 				<h2>${bookingsRembolse.price} &#8364;</h2>
	 			</td>
	 			</tr>
	 		</table>
	 		<p > <br/></p>
	 		<a href="paypal/payAdminRembolse.do?id=${bookingsRembolse.id}"> <spring:message
						code="paypal.payNow" />
	</a>
	</div>
	 	</div>
	 	</div>
 	</jstl:forEach>
 </div>
