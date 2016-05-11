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
<form:form action="${requestURI}" modelAttribute="complaintForm">

	<form:hidden path="id" />
	<form:hidden path="resolution" />
	
	
	
	
	<div class="form-group text-left">

	<form:label path="title">
		<spring:message code="complaint.name" />:
			</form:label>
	<form:textarea code="complaint.name" path="title"
		class="form-control blackL datepicker" />


	<form:errors path="title" cssClass="error" />
	</div>
	
	<div class="form-group text-left">

	<form:label path="description">
		<spring:message code="complaint.description" />:
			</form:label>
	<form:textarea code="complaint.description" path="description"
		class="form-control blackL datepicker" />


	<form:errors path="description" cssClass="error" />
	</div>
	
	
	
	
	
		
	
		<jstl:set var="now" value="<%=new java.util.Date()%>" />
		<p class="text-rigth-small"><fmt:formatDate  value="${now}" pattern="dd/MM/yyyy HH:mm" /></p>
		
	
	<input type="submit" name="save" class="btnAccept"
		value="<spring:message code="complaint.save" />" />


	<input type="button" name="cancel" class="btnCancel"
		value=" <spring:message code="complaint.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do'); " />
	
	<br />
</form:form>
</div>

<div class="col-md-1 ">
</div>
<div class="col-md-5 ">
<div class="container-fluid text-center">

<img alt="" class="img-circle" src="images/complaint.png">
</div>
</div>