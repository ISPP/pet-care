<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<b><spring:message code="trip.descriptionText"/></b>: <jstl:out value="${trip.descriptionText}"></jstl:out>
<br/>
<b><spring:message code="trip.startCity"/></b>: <jstl:out value="${trip.startCity}"></jstl:out>
<br/>
<b><spring:message code="trip.endCity"/></b>: <jstl:out value="${trip.endCity}"></jstl:out>
<br/>
<b><spring:message code="trip.distance"/></b>: <jstl:out value="${trip.distance}"></jstl:out>
<br/>
<b><spring:message code="trip.cost"/></b>: <jstl:out value="${trip.cost}"></jstl:out>
<br/>
<b><spring:message code="trip.vehicle"/></b>: <jstl:out value="${trip.vehicle.title}"></jstl:out>
<br/>
<b><spring:message code="trip.moment"/></b>: 
<fmt:formatDate value="${trip.moment}" pattern="dd/MM/yyyy" />
<br/>

<jstl:if test="${deletable==true}">
	<acme:cancel code="trip.edit" url="/trip/petShipper/edit.do?tripId=${trip.id}" />
</jstl:if>
<input type="button" name="cancel" class="button" value="<spring:message code="trip.cancel" />" onclick="javascript: window.location.replace('welcome/index.do');" />
<br />