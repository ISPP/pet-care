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
	<h3 class="h3-no-bottom">${vehicle.title}</h3><hr>
<b><spring:message code="vehicle.size"/></b>: <jstl:out value="${vehicle.size}"></jstl:out>
<br/>
<b><spring:message code="vehicle.description"/></b>: <jstl:out value="${vehicle.description}"></jstl:out>
<br/>
<br/>

<jstl:if test="${isOwner==true}">
	<a href="vehicle/petShipper/edit.do?vehicleId=${vehicle.id}" class="btn btn-primary">
		<spring:message code="vehicle.edit"/>
	</a>	
</jstl:if>
<acme:cancel code="vehicle.cancel" url="/vehicle/petShipper/list.do" />
<!-- 
<input type="button" name="cancel" class="button" value="<spring:message code="vehicle.cancel" />" onclick="javascript: window.location.replace('welcome/index.do');" />
 -->
<br />
</div>
			
	 	</div>