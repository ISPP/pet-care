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

<jstl:set var="action" value="petShipper/create.do"/>
<form:form action="${action}" modelAttribute="petShipperForm">
<form:hidden path="invitationCode"/> 
<form:hidden path="daysBeforeCancel"/> 
<div class="col-md-6">
			<spring:message code="petShipper.personalData" />
			<hr>
	<!-- Actor -->
	<acme:textbox code="petShipper.name" path="name"/>
	<acme:textbox code="petShipper.surname" path="surname"/>
	<acme:textbox code="petShipper.email" path="email"/>
	
	<!-- User Account -->
	<acme:textbox code="petShipper.username" path="username"/>
	<acme:password code="petShipper.password" path="password"/>
	<acme:password code="petShipper.passwordConfirm" path="passwordConfirm"/>
	
	<!-- Customer -->
	<acme:textbox code="petShipper.address" path="address"/>
	
	<acme:textbox code="petShipper.homePage" path="homePage"/>
	<acme:textbox code="petShipper.contactPhone" path="contactPhone"/>
	
	
	<br />
			<form:label class="register-left" path="description">
				<spring:message code="petShipper.description" />
			</form:label>
			<br />
			<spring:message var="writeHere"
				code="petShipper.description.writeHere" />
			<form:textarea class="register-todoAncho" path="description"
				placeholder="${writeHere}" />
	</div>
	<div class="col-md-6">
	<img class="register-todoAncho" alt="Your AVATAR"
				src="images/petShipper-index.jpg">
			<spring:message code="petShipper.creditCard" />
			<hr>
	<!-- Credit Card -->
	<acme:textbox code="petShipper.creditCard.holderName" path="holderName"/>
	<acme:textbox code="petShipper.creditCard.brandName" path="brandName"/>
	<acme:textbox code="petShipper.creditCard.expirationMonth" path="expirationMonth"/>
	<acme:textbox code="petShipper.creditCard.expirationYear" path="expirationYear"/>
	<acme:textbox code="petShipper.creditCard.cvvCode" path="cvvCode"/>
	<acme:textbox placeholder="nnnn-nnnn-nnnn-nnnn" code="petShipper.creditCard.number" path="number"/>
	</div>
	<%-- <form:label path="acceptTermsAndConditions">
		<spring:message code="petShipper.acceptTermsAndConditions4"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petShipper.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petShipper.acceptTermsAndConditions3"/>
	</form:label>
	<form:checkbox path="acceptTermsAndConditions"/> --%>
	<div class="col-md-12">
	<acme:submit name="create" code="petShipper.button.create"/>
	<acme:cancel url="welcome/index.do" code="petShipper.button.cancel"/>
	</div>
	<%-- <jstl:if test="${termsNotAccepted==true}">
		<br/>
		<spring:message code="petShipper.acceptTermsAndConditions"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petShipper.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petShipper.acceptTermsAndConditions3"/>
	</jstl:if> --%>
	
	<jstl:if test="${oldYear==true}">
		<br/>
		<spring:message code="petShipper.oldYear"/>
	</jstl:if>
		
</form:form>
</jstl:if>


<jstl:if test="${display==true}">
	<fieldset>
		<legend>
			<spring:message code="petShipper.personalData" />
		</legend>
		<security:authorize access="hasRole('ADMIN')">
			<jstl:if test="${row.blocked==true}">
				<spring:message code="petShipper.bloked"/>
			</jstl:if>
			<jstl:if test="${row.blocked!=true}">
				<a href="supplier/administrator/block.do?supplierId<jstl:out value="${row.id}"/>">
				<spring:message code="petShipper.block"/>
			</a>
			</jstl:if>
		</security:authorize>
		<form:form modelAttribute="petShipper" id="row">
			<acme:textbox code="petShipper.name" path="name" readonly="true" />
			<acme:textbox code="petShipper.surname" path="surname" readonly="true" />
			<acme:textbox code="petShipper.email" path="email" readonly="true" />
			<acme:textbox code="petShipper.contactPhone" path="contactPhone" readonly="true" />
			<acme:textbox code="petShipper.homePage" path="homePage" readonly="true" />
			<acme:textbox code="petShipper.username" path="userAccount.username" readonly="true" />
			<acme:textarea code="petShipper.description" path="description"/>
			<acme:textbox code="petShipper.creditCard.holderName"
				path="creditCard.holderName" readonly="true" />
			<acme:textbox code="petShipper.creditCard.brandName"
				path="creditCard.brandName" readonly="true" />
			<acme:textbox code="petShipper.creditCard.expirationMonth"
				path="creditCard.expirationMonth" readonly="true" />
			<acme:textbox code="petShipper.creditCard.expirationYear"
				path="creditCard.expirationYear" readonly="true" />
			<acme:textbox code="petShipper.creditCard.cvvCode"
				path="creditCard.cvvCode" readonly="true" />
			<acme:textbox code="petShipper.creditCard.number"
				path="creditCard.number" readonly="true" />
				
			<acme:cancel url="welcome/index.do" code="petShipper.button.cancel"/>
		</form:form>
	</fieldset>
</jstl:if>