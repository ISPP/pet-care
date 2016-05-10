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
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="col-md-6-2 panel panel-default">
<form:form action="${requestURI}" modelAttribute="comment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="creationMoment" />
	<form:hidden path="complaint" />
	<form:hidden path="actor" />	
	
	
	
	
	
	<form:label class="text-center h3-top" path="text">
				<spring:message code="comment.text" />
			</form:label>
			
			
			<form:textarea class="form-control" path="text"/>	
			<form:errors cssClass="error" path="text"/>
	<br>
	
	<div class="col-md-12 text-center">
	</div>

<input type="submit" name="save" class="btnAccept"
		value="<spring:message code="comment.save" />" />

	
	<input type="button" name="cancel" class="btnCancel"
		value="<spring:message code="comment.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />
	<br />
</form:form>
</div>