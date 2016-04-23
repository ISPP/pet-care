<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
		
<h4><spring:message code="sitter.supplier"/></h4>
<acme:out code="sitter.fullName" path="${supplier.name}"/>
<br />
<acme:out code="sitter.description" path="${supplier.description}"/>
<br />
<acme:out code="sitter.address" path="${supplier.address}"/>
<br />
<acme:out code="sitter.email" path="${supplier.email}"/>
<br />
<jstl:if test="${supplier.homePage != null}">
	<acme:out code="sitter.homePage" path="${supplier.homePage}"/>
	<br />
</jstl:if>
<br />
<acme:out code="sitter.averageRating" path="${supplier.rating}"/>
<br />

<!-- Reviews list with sortable date and rating -->
<h4><spring:message code="sitter.reviews"/></h4>
<display:table name="reviews" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	<display:column property="creationMoment" titleKey="sitter.review.creationMoment" sortable="True">
		<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm" />
	</display:column>  	
	
	<display:column property="description" titleKey="sitter.description" />
	
	<display:column property="rating" titleKey="sitter.review.rating"  sortable="True"/> 
	
	<display:column property="reviewer.name" titleKey="sitter.review.reviewer"/> 
	
</display:table>