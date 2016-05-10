<%--
 * edit.jsp
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
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<div class="col-md-6">
	<form:form class="form-horizontal" action="${requestURI}" modelAttribute="registrationF">
<div class="form-group text-left">
		<form:label path="startCity">
			<b><spring:message code="trip.startCity" /></b>
		</form:label>



		<form:input class="form-control" path="startCity" />



		<form:errors path="startCity" cssClass="error" />
</div>
<div class="form-group text-left">
		<form:label lass="form-control" path="endCity">
			<b><spring:message code="trip.endCity" /></b>
		</form:label>



		<form:input class="form-control" path="endCity" />



		<form:errors path="endCity" cssClass="error" />
</div>

<div class="form-group text-left">
		<form:label path="moment">
			<b><spring:message code="trip.moment" /></b>
		</form:label>



		<form:input class="form-control blackL datepicker" path="moment" />



		<form:errors path="moment" cssClass="error" />

</div>

		<div class="col-md-12">
			<br> <input type="submit" name="save" class="btnAccept"
				value="<spring:message code="trip.search" />" /> <input
				type="button" name="cancel" class="btnCancel"
				value="<spring:message code="trip.cancel" />"
				onclick="javascript: window.location.replace('welcome/index.do');" />
			<br>
				<br>
		</div>
	</form:form>
</div>
<div class="col-md-1 ">
</div>
<div class="col-md-5 ">
<div class="container-fluid text-center">

<img alt="" class="register-todoAncho img-circle" src="images/dog_in_car.jpg">
</div>
</div>