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

	<jstl:set var="action" value="petSitter/create.do" />
	<form:form action="${action}" modelAttribute="petSitterForm">
		<form:hidden path="invitationCode" />
		<form:hidden path="daysBeforeCancel" />
		<div class="col-md-6">
			<spring:message code="petSitter.personalData" />
			<hr>

			<!-- Actor -->
			<acme:textbox code="petSitter.name" path="name" />
			<acme:textbox code="petSitter.surname" path="surname" />
			<acme:textbox code="petSitter.email" path="email" />

			<!-- User Account -->
			<acme:textbox code="petSitter.username" path="username" />
			<acme:password code="petSitter.password" path="password" />
			<acme:password code="petSitter.passwordConfirm"
				path="passwordConfirm" />

			<!-- Customer -->
			<spring:message var="addressHelp" code="petSitter.addressHelp" />
			<acme:textbox code="petSitter.address" path="address" placeholder="${addressHelp}"/>

			<acme:textbox code="petSitter.homePage" path="homePage" />
			<acme:textbox code="petSitter.contactPhone" path="contactPhone" />



			<br />
			<form:label class="register-left" path="description">
				<spring:message code="petSitter.description" />
			</form:label>
			<br />
			<spring:message var="writeHere"
				code="petSitter.description.writeHere" />
			<form:textarea class="register-todoAncho" path="description"
				placeholder="${writeHere}" />



		</div>
		<div class="col-md-6">

			<img class="register-todoAncho" alt="Your AVATAR"
				src="images/petSitter-index.jpg">
			<spring:message code="petSitter.creditCard" />
			<hr>
			<!-- Credit Card -->
			<acme:textbox code="petSitter.creditCard.holderName"
				path="holderName" />
			<acme:textbox code="petSitter.creditCard.brandName" path="brandName" />
			<acme:textbox code="petSitter.creditCard.expirationMonth"
				path="expirationMonth" />
			<acme:textbox code="petSitter.creditCard.expirationYear"
				path="expirationYear" />
			<acme:textbox code="petSitter.creditCard.cvvCode" path="cvvCode" />
			<acme:textbox placeholder="nnnn-nnnn-nnnn-nnnn"
				code="petSitter.creditCard.number" path="number" />
		</div>
		<div class="col-md-12">
			<%-- <form:label path="acceptTermsAndConditions">
		<spring:message code="petSitter.acceptTermsAndConditions4"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petSitter.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petSitter.acceptTermsAndConditions3"/>
	</form:label>
	<form:checkbox path="acceptTermsAndConditions"/> --%>

			<acme:submit name="create" code="petSitter.button.create" />
			<acme:cancel url="welcome/index.do" code="petSitter.button.cancel" />
		</div>
		<%-- <jstl:if test="${termsNotAccepted==true}">
		<br/>
		<spring:message code="petSitter.acceptTermsAndConditions"/>
		<a href="legal/termsAndConditions.do">
			<spring:message code="petSitter.acceptTermsAndConditions2"/>
		</a>
		<spring:message code="petSitter.acceptTermsAndConditions3"/>
	</jstl:if> --%>

		<jstl:if test="${oldYear==true}">
			<br />
			<spring:message code="petSitter.oldYear" />
		</jstl:if>

	</form:form>
</jstl:if>


<jstl:if test="${display==true}">
	<fieldset>
		<legend>
			<spring:message code="petSitter.personalData" />
		</legend>
		<security:authorize access="hasRole('ADMIN')">
			<jstl:if test="${row.blocked==true}">
				<spring:message code="petSitter.bloked" />
			</jstl:if>
			<jstl:if test="${row.blocked!=true}">
				<a
					href="supplier/administrator/block.do?supplierId<jstl:out value="${row.id}"/>">
					<spring:message code="petSitter.block" />
				</a>
			</jstl:if>
		</security:authorize>
		<form:form modelAttribute="petSitter" id="row">
			<acme:textbox code="petSitter.name" path="name" readonly="true" />
			<acme:textbox code="petSitter.surname" path="surname" readonly="true" />
			<acme:textbox code="petSitter.email" path="email" readonly="true" />
			<acme:textbox code="petSitter.contactPhone" path="contactPhone"
				readonly="true" />
			<acme:textbox code="petSitter.homePage" path="homePage"
				readonly="true" />
			<acme:textbox code="petSitter.username" path="userAccount.username"
				readonly="true" />
			<acme:textarea code="petSitter.description" path="description" />
			<acme:textbox code="petSitter.creditCard.holderName"
				path="creditCard.holderName" readonly="true" />
			<acme:textbox code="petSitter.creditCard.brandName"
				path="creditCard.brandName" readonly="true" />
			<acme:textbox code="petSitter.creditCard.expirationMonth"
				path="creditCard.expirationMonth" readonly="true" />
			<acme:textbox code="petSitter.creditCard.expirationYear"
				path="creditCard.expirationYear" readonly="true" />
			<acme:textbox code="petSitter.creditCard.cvvCode"
				path="creditCard.cvvCode" readonly="true" />
			<acme:textbox code="petSitter.creditCard.number"
				path="creditCard.number" readonly="true" />

			<acme:cancel url="welcome/index.do" code="petSitter.button.cancel" />
		</form:form>
	</fieldset>
</jstl:if>