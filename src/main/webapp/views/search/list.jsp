<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- LIST -->
<display:table name="sitters" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 

	<display:column titleKey="sitter.fullName">
		<jstl:out value="${row.name}" /> <jstl:out value="${row.surname}" />
	</display:column>
	
	<display:column property="address" titleKey="sitter.address"/> 
	
	<display:column property="priceNight" titleKey="sitter.priceNight"/> 
	
	<display:column property="priceHour" titleKey="sitter.priceHour"/> 
	
	<display:column titleKey="sitter.display">
		<a href="search/display.do?petSitterId=${row.id}"> <spring:message code="sitter.display"/> </a>
	</display:column>
	
</display:table>