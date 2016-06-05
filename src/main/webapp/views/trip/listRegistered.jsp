<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
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
 	<h2><spring:message code="trip.trips"/></h2><hr>	
 	<jstl:forEach var="row" items="${registrations}">
 		<div style="cursor: pointer;" onclick="location.href='trip/petShipper/see.do?tripId=${row.trip.id}';" class="col-md-8-2 container-fluid panel panel-default">
	 		
	 		
	 		
	 		<h2 class="h3-no-bottom">${row.trip.startCity} -> ${row.trip.endCity}</h2>
	 		
	 		
	 			<div class="col-md-6">
	 			
	 				<h3><fmt:formatDate value="${row.moment}" pattern="dd/MM/yyyy HH:mm" />
	 			</h3>
	 			</div>
	 			<div class="col-md-6">
	 				<h3>${row.trip.cost} &#8364;</h3>
	 				</div>
	 			
	 			
	 		
	 		
	 		
	 		
	 	
	
	

	 	
	 	</div>
 	</jstl:forEach>
 </div>

