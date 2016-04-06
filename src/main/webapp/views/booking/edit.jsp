<%--
 * edit.jsp
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
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="bookingForm">

	<form:hidden path="supplier" />
	
	
	
	

	
		<acme:textbox code="booking.startMoment" path="startMoment"/>
		<acme:textbox code="booking.endMoment" path="endMoment"/>
			
			
			<form:label path="night">
				<spring:message code="booking.Type" />:
			</form:label>
			
			<form:radiobutton path="night" value="0"/>
			<form:label path="night">
				<spring:message code="booking.perHour" />
			</form:label>
			<form:radiobutton path="night" value="1"/>
			<form:label path="night">
				<spring:message code="booking.perDay" />
			</form:label>
		
			<form:errors cssClass="error" path="night" />
			
	

	<input type="submit" name="save" class="button"
		value="<spring:message code="complaint.save" />" />


	<input type="button" name="cancel" class="button"
		value="<spring:message code="complaint.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />
</form:form>