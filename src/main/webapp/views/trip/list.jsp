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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<display:table class="displaytag" pagesize="5"
	name="trips" id="row" requestURI="${requestURI}">
	
	<display:column titleKey="trip.moment" property="moment" format="{0,date,dd-MM-yyyy}"/>
	<display:column titleKey="trip.cost" property="cost"/>
	<display:column titleKey="trip.startCity" property="startCity" />
	<display:column titleKey="trip.endCity" property="endCity"/>
	<display:column>
		<a href="trip/petOwner/register.do?tripId=<jstl:out value="${row.id}"/>">	
			<spring:message code="trip.register"/>
		</a>
	</display:column>
	
</display:table>

