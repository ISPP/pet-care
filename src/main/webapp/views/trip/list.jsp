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
 	<jstl:forEach var="row" items="${trips}">
 		<div style="cursor: pointer;" onclick="location.href='trip/petShipper/see.do?tripId=${row.id}';" class="col-md-8-2 panel panel-default">
	 		
	 		
	 		
	 		<h3 class="h3-no-bottom">${row.startCity} -> ${row.endCity}</h3>
	 		<table class="text-rigth-2">
	 			<tr>
	 			
	 			<td class="table-separate-100">
	 				<fmt:formatDate value="${row.moment}" pattern="dd/MM/yyyy HH:mm" />
	 			</td>
	 			<td class="table-separate-100">
	 				<h2>${row.cost} &#8364;</h2>
	 			</td>
	 			</tr>
	 		</table>
	 		<p > <br/></p>
	 		<security:authorize access="hasRole('PETOWNER')">
	
		<a href="trip/petOwner/register.do?tripId=<jstl:out value="${row.id}"/>">	
			<spring:message code="trip.register"/>
		</a>
	
	</security:authorize>
	 		<security:authorize access="hasRole('PETSHIPPER')">
	 		<a class="text-left-small" href="registration/petShipper/list.do?tripId=<jstl:out value="${row.id}"/>">	
			<spring:message code="trip.registrations"/></a></security:authorize>
			
	<br>
	
	

	 	
	 	</div>
 	</jstl:forEach>
 </div>

