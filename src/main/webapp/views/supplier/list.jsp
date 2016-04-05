<%--
 * action-1.jsp
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

<fieldset>
	<legend>
		<h1>
			<spring:message code="sitter.sitter" />
		</h1>
	</legend>
	<display:table class="displaytag" pagesize="5" name="petSitters"
		id="row" requestURI="${requestURI}">

		<display:column titleKey="sitter.fullName">
			<jstl:out value="${row.name}" />
			<jstl:out value="${row.surname}" />
		</display:column>

		<display:column titleKey="sitter.email" property="email" />

		<display:column titleKey="sitter.review.rating" property="rating" />

		<display:column property="address" titleKey="sitter.address" />

		<display:column property="priceNight" titleKey="sitter.priceNight" />

		<display:column property="priceHour" titleKey="sitter.priceHour" />
		<display:column>
			<jstl:if test="${row.blocked==true}">
				<spring:message code="petSitter.blocked" />
			</jstl:if>
			<jstl:if test="${row.blocked!=true}">
				<spring:message code="supplier.confirm.block" var="confirmBlock" />
				<form:form method="post" name="blockForm" id="blockForm"
					action="supplier/administrator/blockSitter.do">
					<input type="hidden" value="${row.id}" name="supplierId"
						id="supplierId" />
					<a href="javascript:{}" onclick="confirmSubmit('${confirmBlock}')"> <spring:message
							code="petSitter.block" />
					</a>
					<script>
						function confirmSubmit(message) {
							result = confirm(message);
							if(result==true){
								document.getElementById('blockForm').submit();
							}
						}
					</script>
				</form:form>
			</jstl:if>
		</display:column>
	</display:table>
</fieldset>
