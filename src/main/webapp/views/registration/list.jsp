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
	name="registrations" id="row" requestURI="${requestURI}">
	
	<display:column titleKey="registration.moment" property="moment" format="{0,date,dd-MM-yyyy HH:mm}"/>
	<display:column titleKey="registration.petowner.name" property="petOwner.name" />
	<display:column>
		<a href="petOwner/petOwner/display.do?petOwnerId=<jstl:out value="${row.id}"/>">	
			<spring:message code="registration.petowner.profile"/>
		</a>
	</display:column>
	
	
	
</display:table>

