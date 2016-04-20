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

<jstl:set var="action" value="company/company/create.do"/>
<form:form action="${action}" modelAttribute="companyForm">

	<form:hidden path="id" />

	<!-- Actor -->
	<acme:textbox code="company.name" path="name"/>
	<acme:textbox code="company.surname" path="surname"/>
	<acme:textbox code="company.email" path="email"/>
	
	<!-- User Account -->
	<acme:textbox code="company.username" path="username"/>
	<acme:password code="company.newPassword" path="password"/>
	<acme:password code="company.newPasswordConfirm" path="passwordConfirm"/>
	
	<!-- Customer -->
	<acme:textbox code="company.address" path="address"/>
	<acme:textarea code="company.description" path="description"/>
	<acme:textbox code="company.homePage" path="homePage"/>
	<acme:textbox code="company.contactPhone" path="contactPhone"/>
	<acme:textbox code="company.daysBeforeCancel" path="daysBeforeCancel"/>
	<acme:textbox code="company.tic" path="tic"/>
	<acme:textbox code="company.priceNight" path="pricePerDay"/>
	
	<!-- Credit Card -->
	<acme:textbox code="company.creditCard.holderName" path="holderName"/>
	<acme:textbox code="company.creditCard.brandName" path="brandName"/>
	<acme:textbox code="company.creditCard.expirationMonth" path="expirationMonth"/>
	<acme:textbox code="company.creditCard.expirationYear" path="expirationYear"/>
	<acme:textbox code="company.creditCard.cvvCode" path="cvvCode"/>
	<acme:textbox placeholder="nnnn-nnnn-nnnn-nnnn" code="company.creditCard.number" path="number"/>
	
	<%-- <form:label path="acceptTermsAndConditions">
		<spring:message code="petOwner.acceptTermsAndConditions4"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petOwner.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petOwner.acceptTermsAndConditions3"/>
	</form:label>
	<form:checkbox path="acceptTermsAndConditions"/> --%>
	
	<acme:submit name="create" code="company.button.apply"/>
	<acme:cancel url="welcome/index.do" code="company.button.cancel"/>
	
	<%-- <jstl:if test="${termsNotAccepted==true}">
		<br/>
		<spring:message code="company.acceptTermsAndConditions"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="company.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="company.acceptTermsAndConditions3"/>
	</jstl:if> --%>
	
	<jstl:if test="${oldYear==true}">
		<br/>
		<spring:message code="company.oldYear"/>
	</jstl:if>
	
</form:form>