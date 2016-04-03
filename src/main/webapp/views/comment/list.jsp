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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="comments" id="row" requestURI="${requestUri}"
	class="displaytag" keepStatus="true" pagesize="5" >
	<!--Property se refiere al atributo del objeto de la fila que va a ir en la columna-->	
	<spring:message code="comment_*text" var="textColumn" ></spring:message>
	<display:column property="text" title="${textColumn}" />
	
	<spring:message code="comment_*creationMoment" var="creationMomentColumn" ></spring:message>
	<display:column property="creationMoment" title="${creationMomentColumn}" />
	
	<spring:message code="comment_*actor" var="actorColumn" ></spring:message>
	<display:column property="actor.name" title="${actorColumn}" />
</display:table>
			<a href="comment/actor/edit.do?id=${row.complaint.id}"> <spring:message
					code="cpmlaint_*commentCreate" />
			</a>