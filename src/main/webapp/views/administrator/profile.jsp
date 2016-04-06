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

<jstl:set var="action" value="administrator/administrator/create.do"/>
<form:form action="${action}" modelAttribute="administratorForm">

	<form:hidden path="id" />

	<!-- Actor -->
	<acme:textbox code="administrator.name" path="name"/>
	<acme:textbox code="administrator.surname" path="surname"/>
	<acme:textbox code="administrator.email" path="email"/>
	
	<!-- User Account -->
	<acme:textbox code="administrator.username" path="username"/>
	<acme:password code="administrator.newPassword" path="password"/>
	<acme:password code="administrator.newPasswordConfirm" path="passwordConfirm"/>
	
	<acme:submit name="create" code="administrator.button.apply"/>
	<acme:cancel url="welcome/index.do" code="administrator.button.cancel"/>
	
</form:form>