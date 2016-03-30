<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="complaints" id="row" requestURI="${requestUri}"
	class="displaytag" keepStatus="true" pagesize="5">
	<!--Property se refiere al atributo del objeto de la fila que va a ir en la columna-->
	<spring:message code="cpmlaint_*title" var="titleColumn"></spring:message>
	<display:column property="title" title="${titleColumn}" />

	<spring:message code="cpmlaint_*description" var="descriptionColumn"></spring:message>
	<display:column property="description" title="${descriptionColumn}" />

	<spring:message code="cpmlaint_*resolution" var="resolutionColumn"></spring:message>
	<display:column property="resolution" title="${resolutionColumn}" />

	<spring:message code="cpmlaint_*creationMoment" var="creationMomentColumn"></spring:message>
	<display:column property="creationMoment" title="${creationMomentColumn}" />

<jstl:if test="${toAssign==true}">
	<spring:message code="complaint.assign"
		var="assignColumn"></spring:message>
		<display:column title = "${assignColumn}">
			
				<a href="complaint/administrator/assign.do?id=${row.id}"> <spring:message
						code="complaint.assign" />
				</a>
		
		</display:column>
	</jstl:if>



	<jstl:if test="${toSolve==true}">
	<spring:message code="complaint.solve"
		var="solveColumn"></spring:message>
		<display:column title = "${assignColumn}">
			
				<a href="complaint/administrator/solve.do?id=${row.id}"> <spring:message
						code="complaint.solve" />
				</a>
		
		</display:column>
	</jstl:if> --%>


</display:table>