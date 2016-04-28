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
<div class="col-md-12">
<form:form action="${requestURI}" modelAttribute="tripForm">

	<form:hidden path="id" />
	<div class="col-md-6">
		<acme:textbox code="trip.startCity" path="startCity"/>
		<table class="labelInput">
			<tr>
			<th class="tableLabel">
				<form:label path="${trip.distance}">
					<spring:message code="trip.distance" />
				</form:label>	
			</th>
			<th class="tableInput">
				<form:select path="distance">
					<form:option value="SHORT" label="SHORT" />
					<form:option value="MEDIUM" label="MEDIUM" />
					<form:option value="LARGE" label="LARGE" />
				</form:select>
			</th>
			<th class="tableError">
			<form:errors path="distance" cssClass="error" />
			</th>
			</tr>
		</table>
		<br/>
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
	<div class="col-md-6">
		<acme:textbox code="trip.endCity" path="endCity"/>
		<table class="labelInput">
			<tr>
			<th class="tableLabel">
				<form:label path="${trip.vehicle}">
					<spring:message code="trip.vehicle" />
				</form:label>	
			</th>
			<th class="tableInput">
				<form:select path="vehicleId">
					 <form:options items="${vehicles}" itemLabel="title" itemValue="id"/>
				</form:select>
			</th>
			<th class="tableError">
			<form:errors path="vehicleId" cssClass="error" />
			</th>
			</tr>
		</table>
		<br/>
		<acme:textbox code="trip.cost" path="cost"/>
		
	</div>
	<div class="col-md-6">
	<form:label class="midl-left" path="descriptionText">
		<spring:message code="trip.descriptionText" />
	</form:label>
	<form:textarea class="register-todoAncho"  path="descriptionText"/>
	</div>
	
	<br />
	<div class="midl-left">
				<input type="submit" name="save" class="button btn btn-primary"
					value="<spring:message code="trip.save" />" />
					
				<jstl:if test="${deletable==true}">
					<input type="submit" name="delete" class="button btn btn-primary"
						value="<spring:message code="trip.delete" />" />
				</jstl:if>
		
				<input type="button" name="cancel" class="button btn btn-primary"
					value="<spring:message code="trip.cancel" />"
					onclick="javascript: window.location.replace('welcome/index.do');" />
	</div>
</form:form>
</div>