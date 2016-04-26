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

<form:form action="${requestURI}" modelAttribute="registrationF">
	<div class="col-md-8">
		<acme:textbox code="trip.startCity" path="startCity"/>
		<acme:textbox code="trip.endCity" path="endCity"/>
	</div>
	<div class="col-md-8">
		<table class="labelInput">
			<tr>
			<th class="tableLabel">
			<form:label path="${trip.moment}" >
				<b><spring:message code="trip.moment" /></b>
			</form:label>	
			</th>
			<th class="tableInput">
				<spring:message var="moment" code="trip.moment"/>
				<form:input class="blackL datepicker" path="moment"/>	
			</th>
			<th class="tableError">
			
			<form:errors path="moment" cssClass="error" />
			</th>
			</tr>
		</table>
	</div>
	<div class="col-md-8">
	<input type="submit" name="save" class="button"
			value="<spring:message code="trip.save" />" />
	
		
		<input type="button" name="cancel" class="button"
			value="<spring:message code="trip.cancel" />"
			onclick="javascript: window.location.replace('welcome/index.do');" />
		<br />
	</div>
</form:form>
