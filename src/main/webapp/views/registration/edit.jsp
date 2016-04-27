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
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestUri}" modelAttribute="review">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="booking" />
	<form:hidden path="reviewed" />
	<form:hidden path="reviewer" />
	<form:hidden path="creationMoment" />	
	
	<acme:textarea code="review.description" path="description" readonly="false"/>
	
	<form:label path="rating">
		<spring:message code="review.rating" />
	</form:label>	
	<form:select path="rating">
		<form:option value="5.0" label="5" />
		<form:option value="4.0" label="4" />
		<form:option value="3.0" label="3" />
		<form:option value="2.0" label="2" />
		<form:option value="1.0" label="1" />		
		<form:option value="0.0" label="0" />		
		
	</form:select>
	<form:errors path="rating" cssClass="error" />

<input type="submit" name="save" class="button"
		value="<spring:message code="review.save" />" />

	
	<input type="button" name="cancel" class="button btn-primary"
		value="<spring:message code="review.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />
</form:form>