<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="col-md-12">
 	<h2><spring:message code="vehicle.myVehicles"/></h2><hr>	
 	<jstl:forEach var="row" items="${vehicles}">
		<div style="cursor: pointer;" onclick="location.href='vehicle/petShipper/display.do?vehicleId=${row.id}';" class="col-md-8-2 panel panel-default">
			<div class="wrap-3">
			<h3 class="h3-no-bottom">${row.title}</h3>
			<p class="register-left"><jstl:out value="${row.description}"/></p>
			<span class="text-rigth-2"><jstl:out value="${row.size}"/></span>
			<br/>
			</div>
		</div>
	</jstl:forEach>
</div>
<!-- 
<display:table name="vehicles" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column titleKey="vehicle.title" property="title"/>
	<display:column titleKey="vehicle.size" property="size"/>
	<display:column titleKey="vehicle.description" property="description"/>
	<display:column>
		<a href="vehicle/petShipper/display.do?vehicleId=${row.id}">
			<spring:message code="vehicle.display"/>
		</a>
	</display:column>
	
</display:table>
 -->