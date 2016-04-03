<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- SEARCHER -->
<form:form action="search/searchSitters.do" modelAttribute="searchSittersForm" method="POST">
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
	
	<acme:submit code="sitter.search.go" name="search" />	
</form:form>
<br/>
<br/>
