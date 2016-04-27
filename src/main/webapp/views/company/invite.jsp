<%--
 * edit.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<div class="col-md-6-2 panel panel-default">
<jstl:set var="action" value="${requestURI}"/>
<form:form action="${action}" modelAttribute="invitationForm">

	
	<form:label class="text-center h3-top" path="email">
				<spring:message code="company.email" />
			</form:label>
			
			
			<form:input class="form-control" path="email"/>	
			<form:errors cssClass="error" path="email"/>
	<br>
	

	
	<div class="col-md-12">
	<acme:submit name="create" code="company.send"/>
	<acme:cancel url="welcome/index.do" code="company.button.cancel"/>
	</div>
</form:form>
</div>
