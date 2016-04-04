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

<jstl:if test="${register==true}">

<jstl:set var="action" value="petOwner/create.do"/>
<form:form action="${action}" modelAttribute="petOwnerForm">

	<!-- Actor -->
	<acme:textbox code="petOwner.name" path="name"/>
	<acme:textbox code="petOwner.surname" path="surname"/>
	<acme:textbox code="petOwner.email" path="email"/>
	
	<!-- User Account -->
	<acme:textbox code="petOwner.username" path="username"/>
	<acme:password code="petOwner.password" path="password"/>
	<acme:password code="petOwner.passwordConfirm" path="passwordConfirm"/>
	
	<!-- Customer -->
	<acme:textbox code="petOwner.address" path="address"/>
	<acme:textarea code="petOwner.description" path="description"/>
	<acme:textbox code="petOwner.homePage" path="homePage"/>
	<acme:textbox code="petOwner.contactPhone" path="contactPhone"/>
	
	<!-- Credit Card -->
	<acme:textbox code="petOwner.creditCard.holderName" path="holderName"/>
	<acme:textbox code="petOwner.creditCard.brandName" path="brandName"/>
	<acme:textbox code="petOwner.creditCard.expirationMonth" path="expirationMonth"/>
	<acme:textbox code="petOwner.creditCard.expirationYear" path="expirationYear"/>
	<acme:textbox code="petOwner.creditCard.cvvCode" path="cvvCode"/>
	<acme:textbox placeholder="nnnn-nnnn-nnnn-nnnn" code="petOwner.creditCard.number" path="number"/>
	
	<%-- <form:label path="acceptTermsAndConditions">
		<spring:message code="petOwner.acceptTermsAndConditions4"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petOwner.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petOwner.acceptTermsAndConditions3"/>
	</form:label>
	<form:checkbox path="acceptTermsAndConditions"/> --%>
	
	<acme:submit name="create" code="petOwner.button.create"/>
	<acme:cancel url="welcome/index.do" code="petOwner.button.cancel"/>
	
	<%-- <jstl:if test="${termsNotAccepted==true}">
		<br/>
		<spring:message code="petOwner.acceptTermsAndConditions"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petOwner.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petOwner.acceptTermsAndConditions3"/>
	</jstl:if> --%>
	
	<jstl:if test="${oldYear==true}">
		<br/>
		<spring:message code="petOwner.oldYear"/>
	</jstl:if>
		
</form:form>
</jstl:if>


<jstl:if test="${display==true}">
	<fieldset>
		<legend>
			<spring:message code="petOwner.personalData" />
		</legend>
		<form:form modelAttribute="petOwner" id="row">
			<acme:textbox code="petOwner.name" path="name" readonly="true" />
			<acme:textbox code="petOwner.surname" path="surname" readonly="true" />
			<acme:textbox code="petOwner.email" path="email" readonly="true" />
			<acme:textbox code="petOwner.contactPhone" path="contactPhone" readonly="true" />
			<acme:textbox code="petOwner.homePage" path="homePage" readonly="true" />
			<acme:textbox code="petOwner.username" path="userAccount.username" readonly="true" />
			<acme:textarea code="petOwner.description" path="description"/>
			<acme:textbox code="petOwner.creditCard.holderName"
				path="creditCard.holderName" readonly="true" />
			<acme:textbox code="petOwner.creditCard.brandName"
				path="creditCard.brandName" readonly="true" />
			<acme:textbox code="petOwner.creditCard.expirationMonth"
				path="creditCard.expirationMonth" readonly="true" />
			<acme:textbox code="petOwner.creditCard.expirationYear"
				path="creditCard.expirationYear" readonly="true" />
			<acme:textbox code="petOwner.creditCard.cvvCode"
				path="creditCard.cvvCode" readonly="true" />
			<acme:textbox code="petOwner.creditCard.number"
				path="creditCard.number" readonly="true" />
				
			<acme:cancel url="welcome/index.do" code="petOwner.button.cancel"/>
		</form:form>
	</fieldset>
</jstl:if>