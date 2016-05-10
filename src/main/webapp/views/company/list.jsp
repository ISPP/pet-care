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


<jstl:forEach var="company" items="${companies}">
		 <div style="cursor: pointer;" onclick="location.href='booking/petOwner/createForCompany.do?companyId=${company.id}';"  class="col-md-4 panel panel-default">
		 	<div class="wrap">
		 	<jstl:if test="${toBook==true}">
		 	<img class="max-h-little img-center" alt="Your COMPANY" src="images/petOwner-index.jpg">
			<span  class="hM3 carousel-caption desc"><jstl:out value=" ${company.pricePerDay}*"/>&#8364;</span>
		 	</jstl:if>
			
			<jstl:if test="${toBook == false}">
			<img class="max-h-little img-center" alt="Your COMPANY" src="images/petOwner-index.jpg">
			<span  class="hM3 carousel-caption desc"><jstl:out value=" ${company.pricePerDay}*"/>&#8364;</span>
			</jstl:if>
			</div>
			
			<div>
			<p class="list-name"><jstl:out value="${company.name}"/><hr class="linea-pegada"/>
			<jstl:out value=" ${company.address}"/>
			<br/>
			</div>
			
			<jstl:if test="${company.rating<1.0}">
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>

        	<jstl:if test="${company.rating>=1.0 and company.rating<2.0}">
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=2.0 and company.rating<3.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=3.0 and company.rating<4.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=4.0 and company.rating<5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating==5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
        	</jstl:if>
			
		</div>
		</jstl:forEach>
		<jstl:if test="${suppliers.size()==0}">
			<h2><spring:message code="search.noResults"/></h2>
		</jstl:if>
		<jstl:if test="${toBook==false}">
		<spring:message code="search.mustRegister"/><br/>
		</jstl:if>
		<spring:message code="search.priceShowsNight"/>




<%-- 
<display:table class="displaytag" pagesize="5"
	name="companies" id="row" requestURI="${requestURI}">
	<display:column titleKey="company.username">
		<jstl:out value="${row.userAccount.username}"/>
	</display:column>
	<display:column titleKey="company.name">
		<jstl:out value="${row.name}"/>
	</display:column>
	<display:column titleKey="company.surname">
		<jstl:out value="${row.surname}"/>
	</display:column>
	<display:column titleKey="" property="birthDate" format="{0,date,dd/MM/yyyy}"/>
	<jstl:if test="${toBook==true}">
	<display:column>
		<a href="booking/petOwner/createForCompany.do?companyId=${row.id}"> <spring:message
				code="company.book" />
		</a>
	</display:column>
	</jstl:if>
</display:table>

 --%>
