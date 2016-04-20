<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
		
<acme:out code="company.name" path="${company.name}"/>
<br />
<acme:out code="company.description" path="${company.description}"/>
<br />
<acme:out code="company.address" path="${company.address}"/>
<br />
<acme:out code="company.email" path="${company.email}"/>
<br />
<acme:out code="company.rating" path="${company.rating}"/>
<br />
<acme:out code="company.daysBeforeCancel" path="${company.daysBeforeCancel}"/>
<br />
<acme:out code="company.priceNight" path="${company.pricePerDay}"/>
<br />
<acme:out code="company.tic" path="${company.tic}"/>
<br />
<jstl:if test="${company.homePage != null}">
	<acme:out code="company.homePage" path="${company.homePage}"/>
	<br />
</jstl:if>

<jstl:if test="${principal}">
	<acme:cancel code="company.edit" url="/company/company/edit.do?companyId=${company.id}" />
	<br />
</jstl:if>

<!-- Reviews list with sortable date and rating -->
<h4><spring:message code="company.reviews"/></h4>
<display:table name="reviews" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	<display:column property="creationMoment" titleKey="company.review.creationMoment" sortable="True">
		<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm" />
	</display:column>  	
	
	<display:column property="description" titleKey="company.description" />
	
	<display:column property="rating" titleKey="company.review.rating"  sortable="True"/> 
	
</display:table>