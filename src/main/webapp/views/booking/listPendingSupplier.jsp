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


<display:table name="bookings" id="row" requestURI="${requestUri}"
	class="displaytag" keepStatus="true" pagesize="5">
	<!--Property se refiere al atributo del objeto de la fila que va a ir en la columna-->
	<spring:message code="booking.code" var="codeColumn"></spring:message>
	<display:column property="code" title="${codeColumn}" />

	<spring:message code="booking.startMoment" var="startMomentColumn"></spring:message>
	<display:column property="startMoment" title="${startMomentColumn}" />

	<spring:message code="booking.endMoment" var="endMomentColumn"></spring:message>
	<display:column property="endMoment" title="${endMomentColumn}" />
	
		<spring:message code="booking.price" var="priceColumn"></spring:message>
	<display:column property="price" title="${priceColumn}" />
	
	
	<spring:message code="booking.petOwner" var="petOwnerColumn"></spring:message>
	<display:column property="supplier.name" title="${petOwnerColumn}" />


	<spring:message code="booking.pending" var="pendingColumn"></spring:message>
	<display:column title="${pendingColumn}" >
		
	<a href="booking/supplier/accept.do?id=${row.id}"> <spring:message
						code="booking.acept" />
	</a>
	<br>
	<a href="booking/petOwner/rejected.do?id=${row.id}"> <spring:message
						code="booking.rejected" />
	</a>
	
			
			
	</display:column>
	


	
</display:table>