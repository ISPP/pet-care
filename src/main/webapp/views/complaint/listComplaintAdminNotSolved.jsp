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
	<h2>
		<spring:message code="complaint.complaints" />
	</h2>
	<hr>
	<jstl:forEach var="com" items="${complaints}">
		<div class="col-md-8-2 panel panel-default">
			<div class="wrap-2">
				<h3 class="h3-no-bottom">${com.title}</h3>
				<hr>
				<jstl:set var="shortDes"
					value="${fn:substring(com.description, 0, 100)}"></jstl:set>
				<p>
					<jstl:out value="${shortDes}..." />
				</p>


				<jstl:if test="${toAssign==true}">
					


					<a class="btn btn-primary"href="complaint/administrator/assign.do?id=${com.id}"> <spring:message
							code="complaint.assign" />
					</a>


				</jstl:if>



				<jstl:if test="${toSolve==true}">
				


					<a class="btn btn-primary" href="complaint/administrator/solve.do?id=${com.id}"> <spring:message
							code="complaint.solve" />
					</a>


				</jstl:if>
				
				
				
				<jstl:if test="${com.comments.size()==0}">
			<jstl:if test="${not empty com.administrator}">
				<a class="btn btn-primary" href="comment/actor/edit.do?id=${com.id}"> <spring:message
						code="cpmlaint_*commentCreate" />
				</a>
			</jstl:if>
		</jstl:if>
		<jstl:if test="${com.comments.size()>0}">
		<jstl:if test="${not empty com.administrator}">
			<a class="btn btn-primary" href="comment/actor/list.do?id=${com.id}"> <spring:message
					code="cpmlaint_*commentList" />
					
			</a>
			</jstl:if>
		</jstl:if>

				
				

<br>
				<p class="text-rigth-small">
					<fmt:formatDate value="${com.creationMoment}"
						pattern="dd/MM/yyyy HH:mm" />
				</p>

			</div>
		</div>
	</jstl:forEach>
</div>

