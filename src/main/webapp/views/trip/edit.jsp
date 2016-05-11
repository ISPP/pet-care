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
<div class="col-md-12">
	<form:form action="${requestURI}" modelAttribute="tripForm">

		<form:hidden path="id" />
		<div class="col-md-6">
			<div class="form-group text-left">
				<form:label path="startCity">
					<b><spring:message code="trip.startCity" /></b>
				</form:label>



				<form:input class="form-control" path="startCity" />



				<form:errors path="startCity" cssClass="error" />
			</div>


			<div class="form-group text-left">
				<form:label path="endCity">
					<b><spring:message code="trip.endCity" /></b>
				</form:label>



				<form:input class="form-control" path="endCity" />



				<form:errors path="endCity" cssClass="error" />
			</div>

			<div class="form-group text-left">
				<form:label path="${trip.distance}">
					<spring:message code="trip.distance" />
				</form:label>
				<form:select class="form-control" path="distance">
					<form:option value="SHORT" label="SHORT" />
					<form:option value="MEDIUM" label="MEDIUM" />
					<form:option value="LARGE" label="LARGE" />
				</form:select>
				<form:errors path="distance" cssClass="error" />

			</div>

			<div class="form-group text-left">
				<form:label path="${trip.moment}">
					<b><spring:message code="trip.moment" /></b>
				</form:label>
				<spring:message var="moment" code="trip.moment" />
				<form:input class="form-control blackL datepicker" path="moment" />
				<form:errors path="moment" cssClass="error" />
			</div>

		</div>

		<div class="col-md-6">
		
			<div class="form-group text-left">

				<form:label path="vehicleId">
					<spring:message code="trip.vehicle" />
				</form:label>
				<form:select class="form-control" path="vehicleId">
					<form:options items="${vehicles}" itemLabel="title" itemValue="id" />
				</form:select>
				<form:errors path="vehicleId" cssClass="error" />
			</div>


			<div class="form-group text-left">
				<form:label path="cost">
					<spring:message code="trip.cost" />

				</form:label>

				<form:input class="form-control" path="cost" />
				<form:errors path="cost" class="error"></form:errors>
			</div>


			<div class="form-group text-left">
				<form:label path="descriptionText">
					<spring:message code="trip.descriptionText" />
				</form:label>
				<form:textarea class="form-control register-todoAncho"
					path="descriptionText" />
				<form:errors path="descriptionText" class="error" />
			</div>
		</div>


		<div class="col-md-12">
			<input type="submit" name="save" class="btnAccept"
				value="<spring:message code="trip.save" />" />

			<jstl:if test="${deletable==true}">
				<div class="modal fade" id="confirmDelete" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">×</button>
								<h4>
									<span class="glyphicon glyphicon-lock"></span>
									<spring:message code="vehicle.delete" />
								</h4>
							</div>
							<div class="modal-body">
								<div class="modal-body">
									<spring:message code="vehicle.confirm.delete" />
								</div>
								<button type="submit" name="delete" class="
btn-block btnAcceptNoMargin">
									<spring:message code="vehicle.delete" />
									<span class="glyphicon glyphicon-ok"></span>
								</button>
							</div>
							<div class="modal-footer">
								<button class="btnCancelNoMargin pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span>
									<spring:message code="vehicle.cancel" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<jstl:if test="${showDeleteButton==true}">
					<a href="" class="btnCancel" data-toggle="modal"
						data-target="#confirmDelete"><spring:message
							code="trip.delete" /></a>
				</jstl:if>
			</jstl:if>
			<input type="button" name="cancel" class="btnCancel"
				value="<spring:message code="trip.cancel" />"
				onclick="javascript: window.location.replace('welcome/index.do');" />
		</div>
	</form:form>
</div>