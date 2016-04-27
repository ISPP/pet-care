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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${register==true}">

	<jstl:set var="action" value="company/create.do" />
	<form:form action="${action}" modelAttribute="companyForm">
	<form:hidden path="invitationCode"/>
	<form:hidden path="id"/>
	<form:hidden path="daysBeforeCancel"/>
	<div class="col-md-6">
	<spring:message code="company.personalData" />
			<hr>
		<!-- Actor -->
		<acme:textbox code="company.name" path="name" />
		<acme:textbox code="company.surname" path="surname" />
		<acme:textbox code="company.email" path="email" />

		<!-- User Account -->
		<acme:textbox code="company.username" path="username" />
		<acme:password code="company.password" path="password" />
		<acme:password code="company.passwordConfirm" path="passwordConfirm" />

		<!-- Customer -->
		<acme:textbox code="company.address" path="address" />
	
		<acme:textbox code="company.homePage" path="homePage" />
		<acme:textbox code="company.contactPhone" path="contactPhone" />
		<acme:textbox code="company.tic" path="tic"/>
		
		
		
		
		<br />
			<form:label class="register-left" path="description">
				<spring:message code="company.description" />
			</form:label>
			<br />
			<spring:message var="writeHere"
				code="petShipper.description.writeHere" />
			<form:textarea class="register-todoAncho" path="description"
				placeholder="${writeHere}" />

	
</div>
<div class="col-md-6">
<img class="register-todoAncho" alt="Your AVATAR"
				src="images/company-index.jpg">
			<spring:message code="petShipper.creditCard" />
			<hr>
		<!-- Credit Card -->
		<acme:textbox code="company.creditCard.holderName" path="holderName" />
		<acme:textbox code="company.creditCard.brandName" path="brandName" />
		<acme:textbox code="company.creditCard.expirationMonth"
			path="expirationMonth" />
		<acme:textbox code="company.creditCard.expirationYear"
			path="expirationYear" />
		<acme:textbox code="company.creditCard.cvvCode" path="cvvCode" />
		<acme:textbox placeholder="nnnn-nnnn-nnnn-nnnn"
			code="company.creditCard.number" path="number" />
			</div>
		
		<!-- Company -->
		
<%-- 		<acme:textbox code="company.pricePerDay" path="pricePerDay"/> --%>
		<acme:submit name="create" code="company.button.create" />
		<acme:cancel  url="welcome/index.do" code="company.button.cancel" />
		<jstl:if test="${oldYear==true}">
			<br />
			<spring:message code="company.oldYear" />
		</jstl:if>

	</form:form>
</jstl:if>