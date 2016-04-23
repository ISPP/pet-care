<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- SEARCHER -->
<form:form action="search/searchSuppliers.do" modelAttribute="searchSuppliersForm" method="POST">
	<form:hidden path="id"/>
	
	<spring:message code="sitter.startDate"/>:
	<form:input path="startDate"/>
	<form:errors path="startDate" cssClass="error" />
	<br/>
	
	<spring:message code="sitter.endDate"/>:
	<form:input path="endDate"/>
	<form:errors path="endDate" cssClass="error" />
	<br/>
	
	<spring:message code="sitter.address"/>:
	<form:input path="address"/>
	<form:errors path="address" cssClass="error" />
	<br/>
	
	<form:label path="type">
		<spring:message code="supplier.type" />
	</form:label>	
	<form:select path="type">
		<form:option value="1"><spring:message code="supplier.petSitter" /></form:option>
		<form:option value="2"><spring:message code="supplier.petShipper" /></form:option>
		<form:option value="3"><spring:message code="supplier.company" /></form:option>
	</form:select>
	<form:errors path="type" cssClass="error" />
	
	<acme:submit code="sitter.search.go" name="search" />	
</form:form>
<br/>
<br/>
