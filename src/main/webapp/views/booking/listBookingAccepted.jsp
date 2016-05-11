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
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



 <div class="col-md-12">
 	<h2><spring:message code="booking.bookings"/></h2><hr>	
 	<jstl:forEach var="booking" items="${bookings}">
 		<div class="col-md-8-2 panel panel-default">
	 		<div class="wrap-2">
	 		<c:set var="status" value="${booking.status}"/>
	 		<c:if test="${fn:contains(status, 'ACCEPTED')}">
   				<img class="max-h-4 img-left-2" alt="Care Person" src="images/acept.png">
				</c:if>
				
				<c:if test="${fn:contains(status, 'REJECTED')}">
   				<img class="max-h-4 img-left-2" src="images/rejc.png">
				</c:if>
	 		
	 		
	 		<c:if test="${fn:contains(status, 'PENDING')}">
   				<img class="max-h-4 img-left-2" src="images/pending.png">
				</c:if>
	 		
	 		<div>
	 		
	 		<h3 class="h3-no-bottom">${booking.supplier.surname},${booking.supplier.name}</h3>
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
	 				<a href="petOwner/petOwner/display.do?petOwnerId=${booking.petOwner.id}"><img  src="images/icons/icon-profile.png" /></a>
	 			</td>
	 			</tr>
	 		</table>
	 		<p > <br/></p>
	 		
	 		
	 		
			
		
	</div>
	 	</div>
	 	</div>
 	</jstl:forEach>
 </div>