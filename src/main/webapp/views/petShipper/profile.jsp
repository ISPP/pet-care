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

<jstl:set var="action" value="petShipper/petShipper/create.do"/>
<form:form action="${action}" modelAttribute="petShipperForm">

	<form:hidden path="id" />

	<!-- Actor -->
	<acme:textbox code="petShipper.name" path="name"/>
	<acme:textbox code="petShipper.surname" path="surname"/>
	<acme:textbox code="petShipper.email" path="email"/>
	
	<!-- User Account -->
	<acme:textbox code="petShipper.username" path="username"/>
	<acme:password code="petShipper.newPassword" path="password"/>
	<acme:password code="petShipper.newPasswordConfirm" path="passwordConfirm"/>
	
	<!-- Customer -->
	<acme:textbox code="petShipper.address" path="address"/>
	<acme:textarea code="petShipper.description" path="description"/>
	<acme:textbox code="petShipper.homePage" path="homePage"/>
	<acme:textbox code="petShipper.contactPhone" path="contactPhone"/>
	<acme:textbox code="petShipper.daysBeforeCancel" path="daysBeforeCancel"/>
	
	<!-- Credit Card -->
	<acme:textbox code="petShipper.creditCard.holderName" path="holderName"/>
	<acme:textbox code="petShipper.creditCard.brandName" path="brandName"/>
	<acme:textbox code="petShipper.creditCard.expirationMonth" path="expirationMonth"/>
	<acme:textbox code="petShipper.creditCard.expirationYear" path="expirationYear"/>
	<acme:textbox code="petShipper.creditCard.cvvCode" path="cvvCode"/>
	<acme:textbox placeholder="nnnn-nnnn-nnnn-nnnn" code="petShipper.creditCard.number" path="number"/>
	
	<%-- <form:label path="acceptTermsAndConditions">
		<spring:message code="petOwner.acceptTermsAndConditions4"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petOwner.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petOwner.acceptTermsAndConditions3"/>
	</form:label>
	<form:checkbox path="acceptTermsAndConditions"/> --%>
	
	<acme:submit name="create" code="petShipper.button.apply"/>
	<acme:cancel url="welcome/index.do" code="petShipper.button.cancel"/>
	
	<%-- <jstl:if test="${termsNotAccepted==true}">
		<br/>
		<spring:message code="petSitter.acceptTermsAndConditions"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petSitter.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petSitter.acceptTermsAndConditions3"/>
	</jstl:if> --%>
	
	<jstl:if test="${oldYear==true}">
		<br/>
		<spring:message code="petShipper.oldYear"/>
	</jstl:if>
	
</form:form>