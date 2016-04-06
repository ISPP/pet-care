<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
		
<h3><spring:message code="administrator.dashboard.supplierWithMoreBookings"/></h3>
<jstl:out value="${supplierWithMoreBookings.user.username}"/> - <jstl:out value="${length(supplierWithMoreBookings.bookings)}"/>
<br/>
<h3><spring:message code="administrator.dashboard.supplierWithZeroBookings"/></h3>
<jstl:out value="${supplierWithZeroBookings}"/><!-- TODO:Change to table in sprint 3 -->
<br/>
<h3><spring:message code="administrator.dashboard.suppliersWithMoreThan10ReviewsWithZeroRating"/></h3>
<display:table name="suppliersWithMoreThan10ReviewsWithZeroRating" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	<display:column titleKey="administrator.dashboard.username">
		<jstl:out value="${row.user.username}"/>
	</display:column>
	<display:column titleKey="administrator.dashboard.reviewsNumber">
		<jstl:out value="${length(row.reviews)}"/>
	</display:column>
	
</display:table>