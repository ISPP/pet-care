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
	name="reviews" id="row" requestURI="${requestURI}">
	
	<display:column titleKey="review.booking.code" property="booking.code"/>
	<display:column titleKey="review.rating" property="rating" format="{0,number,0.00}"/>
	<display:column titleKey="review.creationMoment" property="creationMoment" format="{0,date,dd-MM-yyyy}"/>
	<display:column titleKey="review.reviewer" property="reviewer.user.username"/>
	<display:column>
		<a href="review/petSitter/display.do?reviewId=<jstl:out value="${row.id}"/>">	
			<spring:message code="review.display.details"/>
		</a>
	</display:column>
	
</display:table>

