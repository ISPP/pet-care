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

<form:form action="${requestUri}" modelAttribute="review">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="booking" />
	<form:hidden path="reviewed" />
	<form:hidden path="reviewer" />
	<form:hidden path="creationMoment" />	
	
	<div class="col-md-8-2-noColor panel panel-default">
	 		<div class="wrap-3">
	 		<h3 class="h3-no-bottom"><spring:message code="review.review1"/>: <fmt:formatDate  value="${review.booking.creationMoment}" pattern="dd/MM/yyyy HH:mm" /> </h3><hr>
	<spring:message var="desc" code="review.description" />
	<form:textarea class="register-todoAncho" rows="8" path="description" placeholder="${desc}..."/>
	
	
	<div class="star-rating">
      <div class="star-rating__wrap">
        <spring:message code="review.rating"/>
        <form:radiobutton class="star-rating__input" id="star-rating-3" path="rating" name="rating" value="3"/>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3"></label>
        <form:radiobutton class="star-rating__input" id="star-rating-2" path="rating" name="rating" value="2"/>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2"></label>
        <form:radiobutton class="star-rating__input" id="star-rating-1" path="rating" name="rating" value="1"/>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1"></label>
        <form:radiobutton id="star-rating-0" hidden="hidden" path="rating" name="rating" value="0"/>
      </div>
    </div>
	
	
	<!--  <div>
	<form:label path="rating">
		<spring:message code="review.rating" />
	</form:label>	
	<form:select path="rating">
		<form:option value="5.0" label="5" />
		<form:option value="4.0" label="4" />
		<form:option value="3.0" label="3" />
		<form:option value="2.0" label="2" />
		<form:option value="1.0" label="1" />		
		<form:option value="0.0" label="0" />		
		
	</form:select>
	<form:errors path="rating" cssClass="error" />
	</div>
	-->
	<div>
		<p class="text-rigth-small"><fmt:formatDate  value="${review.creationMoment}" pattern="dd/MM/yyyy HH:mm" /></p>
	</div>
	</div>
	</div>
<input type="submit" name="save" class="button"
		value="<spring:message code="review.save" />" />

	
	<input type="button" name="cancel" class="button"
		value="<spring:message code="review.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />
</form:form>