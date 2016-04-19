<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
		
<acme:out code="petSitter.name" path="${sitter.name}"/>
<br />
<acme:out code="petSitter.description" path="${sitter.description}"/>
<br />
<acme:out code="petSitter.address" path="${sitter.address}"/>
<br />
<acme:out code="petSitter.email" path="${sitter.email}"/>
<br />
<acme:out code="petSitter.priceHour" path="${sitter.priceHour}"/>
<br />
<acme:out code="petSitter.priceNight" path="${sitter.priceNight}"/>
<br />
<acme:out code="petSitter.rating" path="${sitter.rating}"/>
<br />
<acme:out code="petSitter.daysBeforeCancel" path="${sitter.daysBeforeCancel}"/>
<br />
<jstl:if test="${sitter.homePage != null}">
	<acme:out code="petSitter.homePage" path="${sitter.homePage}"/>
	<br />
</jstl:if>

<jstl:if test="${principal}">
	<acme:cancel code="petSitter.edit" url="/petSitter/petSitter/edit.do?petSitterId=${sitter.id}" />
	<br />
</jstl:if>

<!-- Reviews list with sortable date and rating -->
<h4><spring:message code="petSitter.reviews"/></h4>
<display:table name="reviews" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	<display:column property="creationMoment" titleKey="sitter.review.creationMoment" sortable="True">
		<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm" />
	</display:column>  	
	
	<display:column property="description" titleKey="petSitter.description" />
	
	<display:column property="rating" titleKey="sitter.review.rating"  sortable="True"/> 
	
</display:table>