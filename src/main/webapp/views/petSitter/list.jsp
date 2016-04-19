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

<!-- 
<display:table class="displaytag" pagesize="5"
	name="petSitters" id="row" requestURI="${requestURI}">
<%-- 	<display:column titleKey="petSitter.username"> --%>
<%-- 		<jstl:out value="${row.userAccount.username}"/> --%>
<%-- 	</display:column> --%>
	<display:column titleKey="petSitter.name">
		<jstl:out value="${row.name}"/>
	</display:column>
	<display:column titleKey="petSitter.surname">
		<jstl:out value="${row.surname}"/>
	</display:column>
<%-- 	<display:column titleKey="" property="birthDate" format="{0,date,dd/MM/yyyy}"/> --%>
	<jstl:if test="${toBook==true}">
	<display:column>
		<a href="booking/petOwner/create.do?petSitterId=${row.id}"> <spring:message
				code="petSitter.book" />
		</a>
	</display:column>
	</jstl:if>
</display:table>
 -->
 
 <div class="col-md-12">
	</div>
	<jstl:forEach var="petSitter" items="${petSitters}">
	 <div class="col-md-6">
	 	<jstl:if test="${toBook==true}">
	 	<a href="booking/petOwner/create.do?petSitterId=${petSitter.id}">
	 	</jstl:if>
		<img class="max-h img-left" alt="Your PETSITTER" src="images/petOwner-index.jpg">
		<jstl:out value=" ${petSitter.priceNight}"/>
		<jstl:if test="${toBook==true}">
		</a>
		</jstl:if>
		<div class="midl-left">
		<h3><jstl:out value="${petSitter.name} ${petSitters.surname}"/></h3>
		<jstl:out value=" ${petSitter.address}"/>
		<jstl:out value=" ${petSitter.priceHour}"/>
		</div>
		
	</div>
	</jstl:forEach>

