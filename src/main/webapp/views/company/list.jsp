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

<jstl:set value="${requestURI}" var="action" />


<display:table class="displaytag" pagesize="5"
	name="companies" id="row" requestURI="${requestURI}">
<%-- 	<display:column titleKey="company.username"> --%>
<%-- 		<jstl:out value="${row.userAccount.username}"/> --%>
<%-- 	</display:column> --%>
	<display:column titleKey="company.name">
		<jstl:out value="${row.name}"/>
	</display:column>
	<display:column titleKey="company.surname">
		<jstl:out value="${row.surname}"/>
	</display:column>
<%-- 	<display:column titleKey="" property="birthDate" format="{0,date,dd/MM/yyyy}"/> --%>
	<jstl:if test="${toBook==true}">
	<display:column>
		<a href="booking/petOwner/createForCompany.do?companyId=${row.id}"> <spring:message
				code="company.book" />
		</a>
	</display:column>
	</jstl:if>
</display:table>


