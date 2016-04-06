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

<jstl:set value="organiser/administrator/list.do" var="action" />


<display:table class="displaytag" pagesize="5"
	name="organisers" id="row" requestURI="${requestURI}">
	<display:column titleKey="organiser.username">
		<jstl:out value="${row.userAccount.username}"/>
	</display:column>
	<display:column titleKey="organiser.name">
		<jstl:out value="${row.name}"/>
	</display:column>
	<display:column titleKey="organiser.surname">
		<jstl:out value="${row.surname}"/>
	</display:column>
	<display:column titleKey="organiser.birthDate" property="birthDate" format="{0,date,dd/MM/yyyy}"/>
	<display:column>
		<a href="organiser/administrator/display.do?organiserId=${row.id}"> <spring:message
				code="organiser.display.profile" />
		</a>
	</display:column>
	
</display:table>

