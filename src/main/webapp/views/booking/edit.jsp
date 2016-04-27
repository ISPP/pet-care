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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<link rel="stylesheet" href="styles/jquery.switchButton.css">
<script src="scripts/jquery.switchButton.js"></script>
<!--  <link href="styles/bootstrap-switch.css" rel="stylesheet" type="text/css"> -->

<form:form action="${requestURI}" modelAttribute="bookingForm">

	<form:hidden path="supplier" />






	<form:label path="startMoment">
		<spring:message code="booking.startMoment" />:
			</form:label>
	<form:input code="booking.startMoment" path="startMoment"
		class="blackL datepicker" />


	<form:errors path="startMoment" cssClass="error" />
	<br>
	<div id="endMomentDiv">
		<form:label path="endMoment">
			<spring:message code="booking.endMoment" />:
			</form:label>
		<form:input code="booking.endMoment" path="endMoment"
			class="blackL datepicker" />


		<form:errors path="endMoment" cssClass="error" />
	</div>
	<br>

	


	<c:choose>
		<c:when test="${!forCompany}">
		
		
		<div id="hourDiv">
		<form:label path="startHour">
			<spring:message code="booking.startHour" />:
			</form:label>
		<form:input code="booking.startHour" path="startHour" />


		<form:errors path="startHour" cssClass="error" />
		<br>
		<form:label path="endHour">
			<spring:message code="booking.endHour" />:
			</form:label>
		<form:input code="booking.endHour" path="endHour" />


		<form:errors path="endHour" cssClass="error" />
	</div>

			<form:label path="night">
				<spring:message code="booking.Type" />:
			</form:label>

			<%-- 			<form:radiobutton path="night" value="0"/> --%>
			<%-- 			<form:label path="night"> --%>
			<%-- 				<spring:message code="booking.perHour" /> --%>
			<%-- 			</form:label> --%>

			<spring:message code="booking.perDay" var="perDay" />
			<spring:message code="booking.perHour" var="perHour" />


			<br>
			<div id="nightDiv" class="switch-wrapper" onchange="checkPerDay(this)">
				<form:checkbox path="night" />
			</div>

			<br>





		</c:when>
		<c:otherwise>
			<form:hidden path="night" />

		</c:otherwise>
	</c:choose>






	<input type="submit" name="save" class="button btn btn-primary"
		value="<spring:message code="complaint.save" />" />


	<input type="button" name="cancel" class="button btn btn-primary"
		value="<spring:message code="complaint.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />
</form:form>
<script type="text/javascript">
	$("input[type=checkbox]").switchButton({
		on_label : '<label>${perDay}</label>',
		off_label : '<label>${perHour}</label>',
		width : 40,
		height : 20,
		button_width : 25
	});
	
	
	function checkPerDay(cb) {
		
		cb.checked = !cb.checked;
		
		if(!cb.checked){
			$("#hourDiv").hide();
			$("#endMomentDiv").show();
		
		}else{
			$("#hourDiv").show();
			$("#endMomentDiv").hide();
			
			
		}
			
		

	} 
	//$("#night").bootstrapSwitch('setSizeClass', 'switch-large');

	$(function() {
		
	});
</script>