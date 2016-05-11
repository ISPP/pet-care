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
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 
<display:table name="complaints" id="row" requestURI="${requestUri}"
	class="displaytag" keepStatus="true" pagesize="5">
	<!--Property se refiere al atributo del objeto de la fila que va a ir en la columna-->
	<!--
	<spring:message code="cpmlaint_*title" var="titleColumn"></spring:message>
	<display:column property="title" title="${titleColumn}" />

	<spring:message code="cpmlaint_*description" var="descriptionColumn"></spring:message>
	<display:column property="description" title="${descriptionColumn}" />

	<spring:message code="cpmlaint_*creationMoment" var="creationMomentColumn"></spring:message>
	<display:column property="creationMoment" title="${creationMomentColumn}" />
	
	
	<spring:message code="cpmlaint_*comment"
		var="commentColumn"></spring:message>
		
		<display:column title="${commentColumn}">
		
			<jstl:if test="${row.comments.size()==0}">
				<a href="comment/actor/edit.do?id=${row.id}"> <spring:message
						code="cpmlaint_*commentCreate" />
				</a>
			</jstl:if>
			<jstl:if test="${row.comments.size()>0}">
				<a href="comment/actor/list.do?id=${row.id}"> <spring:message
						code="cpmlaint_*commentList" />
				</a>
			</jstl:if>
			
			
			
		</display:column>
		
</display:table>
 -->
 
 <div class="col-md-12">
 	<h2><spring:message code="complaint.complaints"/></h2><hr>	
 	<jstl:forEach var="com" items="${complaints}">
 		<div style="cursor: pointer;" onclick="location.href='complaint/customer/display.do?complaintId=${com.id}';" class="col-md-8-2 panel panel-default">
	 		<div class="wrap-2">
	 		<h3 class="h3-no-bottom">${com.title}</h3><hr>
	 		<jstl:set var="shortDes" value="${fn:substring(com.description, 0, 100)}"></jstl:set>
			<jstl:if test="${(fn:length(com.description))>100}">
			<p><jstl:out value="${shortDes}..."/></p>
			</jstl:if>
			<jstl:if test="${fn:length(com.description)<101}">
			<p><jstl:out value="${shortDes}"/></p>
			</jstl:if>
			
			<jstl:if test="${row.comments.size()>0}">
			<spring:message var="coms" code="complaint.comments" />
			<p class="text-left-small"><jstl:out value="${com.comments.size()}"/> <jstl:out value="${coms}"/> - </p>
			</jstl:if>
			
			
			<jstl:if test="${row.comments.size()==0}">
			<a href="comment/actor/edit.do?id=${row.id}"><img class="max-h-4 img-left-2"  src="images/comment.png" /></a>
			
			</jstl:if>
			<p class="text-rigth-small"><fmt:formatDate  value="${com.creationMoment}" pattern="dd/MM/yyyy HH:mm" /></p>
			</div>
	 	</div>
 	</jstl:forEach>
 </div>