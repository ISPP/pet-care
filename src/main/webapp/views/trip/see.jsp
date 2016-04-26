<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="col-md-8-2-noColor panel panel-default">
	<div class="wrap-3">
	<h3 class="h3-no-bottom">${trip.startCity} -> ${trip.endCity}</h3><hr>
<div class="col-md-4">
<b><spring:message code="trip.startCity"/></b>: <jstl:out value="${trip.startCity}"></jstl:out>
<br/>
<b><spring:message code="trip.distance"/></b>: <jstl:out value="${trip.distance}"></jstl:out>
<br/>
</div>
<div class="col-md-4">
<b><spring:message code="trip.endCity"/></b>: <jstl:out value="${trip.endCity}"></jstl:out>
<br/>
<b><spring:message code="trip.vehicle"/></b>: <jstl:out value="${trip.vehicle.title}"></jstl:out>
<br/>

</div>
<div class="col-md-4">
<b><spring:message code="trip.cost"/></b>: <jstl:out value="${trip.cost}">&#8364;</jstl:out>
<br/>
<b><spring:message code="trip.moment"/></b>: 
<fmt:formatDate value="${trip.moment}" pattern="dd/MM/yyyy HH:mm" />
<br/>
</div>
<br/>
<b><spring:message code="trip.descriptionText"/></b>: <jstl:out value="${trip.descriptionText}"></jstl:out>

<jstl:if test="${deletable==true}">
	<acme:cancel code="trip.edit" url="/trip/petShipper/edit.do?tripId=${trip.id}" />
</jstl:if>
<!-- 
<input type="button" name="cancel" class="button" value="<spring:message code="trip.cancel" />" onclick="javascript: window.location.replace('welcome/index.do');" />
 -->
<br />
</div>
			
	 	</div>