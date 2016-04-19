<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
		
<acme:out code="petShipper.name" path="${shipper.name}"/>
<br />
<acme:out code="petShipper.description" path="${shipper.description}"/>
<br />
<acme:out code="petShipper.address" path="${shipper.address}"/>
<br />
<acme:out code="petShipper.email" path="${shipper.email}"/>
<br />
<acme:out code="petShipper.rating" path="${shipper.rating}"/>
<br />
<acme:out code="petShipper.daysBeforeCancel" path="${shipper.daysBeforeCancel}"/>
<br />
<jstl:if test="${shipper.homePage != null}">
	<acme:out code="petShipper.homePage" path="${shipper.homePage}"/>
	<br />
</jstl:if>

<jstl:if test="${principal}">
	<acme:cancel code="petShipper.edit" url="/petShipper/petShipper/edit.do?petShipperId=${shipper.id}" />
	<br />
</jstl:if>

<!-- Reviews list with sortable date and rating -->
<h4><spring:message code="petShipper.reviews"/></h4>
<display:table name="reviews" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	<display:column property="creationMoment" titleKey="shipper.review.creationMoment" sortable="True">
		<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm" />
	</display:column>  	
	
	<display:column property="description" titleKey="petShipper.description" />
	
	<display:column property="rating" titleKey="shipper.review.rating"  sortable="True"/> 
	
</display:table>