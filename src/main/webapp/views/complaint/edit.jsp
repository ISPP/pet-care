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

<form:form action="${requestURI}" modelAttribute="complaintForm">

	<form:hidden path="id" />
	<form:hidden path="resolution" />
	
	
	
	<div class="col-md-12">
	
		<form:label class="register-left" path="title" >
		<spring:message code="complaint.name" />
		<spring:message var="titleText" code="complaint.name" />
		</form:label>
		<form:input  path="title" placeholder="${titleText}" size="100%"/>
		<form:errors path="title" cssClass="error" />
		<br/>
		<br/>
		<form:label class="register-left"  path="description">
		<spring:message code="complaint.description" />
		</form:label>
		<br/>
		<spring:message var="writeHere" code="complaint.description.writeHere"/>
		<form:textarea class="register-todoAncho" rows="8" path="description" placeholder="${writeHere}"/>

	<input type="submit" name="save" class="button"
		value="<spring:message code="complaint.save" />" />


	<input type="button" name="cancel" class="button"
		value="<spring:message code="complaint.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	</div>
	<br />
</form:form>