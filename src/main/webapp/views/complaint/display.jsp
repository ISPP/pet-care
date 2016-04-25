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

<div class="col-md-8-2-noColor panel panel-default">
	 		<div class="wrap-3">
	 		<h3 class="h3-no-bottom">${complaint.title}</h3><hr>
			<!-- <p><jstl:out value="${complaint.description}"/></p>  -->
			<form:form  modelAttribute="complaint">
			<form:textarea readonly="true" class="register-todoAncho" rows="12" path="description" />
			</form:form>
			<p class="text-rigth-small"><fmt:formatDate  value="${complaint.creationMoment}" pattern="dd/MM/yyyy HH:mm" /></p>
			</div>
			
	 	</div>
<jstl:forEach var="com" items="${complaint.comments}"> 
<div class="col-md-8-2-noColor panel panel-default">
	 		<div class="wrap-3">
			<!-- <p><jstl:out value="${complaint.description}"/></p>  -->
			<p class="register-left"><b><jstl:out value="${com.actor.name }"/><jstl:out value="${com.actor.surname}"/></b></p>
			<br/><br/>
			<p class="register-left"><jstl:out value="${com.text}"/></p>
			<p class="text-rigth-small"><fmt:formatDate  value="${com.creationMoment}" pattern="dd/MM/yyyy HH:mm" /></p>
			</div>
			
	 	</div>
</jstl:forEach>	
<a class="text-left-small" href="comment/actor/edit.do?id=${complaint.id}"> <spring:message
						code="cpmlaint_*commentCreate" />
			</a>