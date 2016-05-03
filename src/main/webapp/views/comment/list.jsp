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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 

 				

<div class="col-md-12">
 	<h2><spring:message code="comment.comments"/></h2><hr>	
 	<jstl:forEach var="comment" items="${comments}">
 		<div class="col-md-8-2 panel panel-default">
	 		<div class="wrap-2">
	 		<img class="max-h-4 img-left-2" alt="Care Person" src="images/comentario.jpg">
	 		<h3 class="h3-no-bottom">${comment.actor.name}</h3><hr>
	 			<table class="text-rigth-2">
	 			<tr>
	 			
	 			
	 			<td class="table-separate-100">
	 				<h2>${comment.text}</h2>
	 			</td>
	 			</tr>
	 		</table>
			
			
	
			
			
			<p class="text-rigth-small"><fmt:formatDate  value="${comment.creationMoment}" pattern="dd/MM/yyyy" /></p>
			
			</div>
	 	</div>
	 	 
 	</jstl:forEach>
 	<a href="comment/actor/edit.do?id=${idComplaint}"> <spring:message
					code="cpmlaint_*commentCreate" />
			</a>
 	
 </div>
 
