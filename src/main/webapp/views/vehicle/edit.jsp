<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${isOwner==true}">
	<form:form action="vehicle/petShipper/${mode}.do"
		modelAttribute="vehicle">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="trips" />
		<form:hidden path="petShipper" />

		<acme:formInput code="vehicle.title" path="title" />
		<form:label path="size">
			<spring:message code="vehicle.size" />
		</form:label>
		<form:select path="size">
			<form:option value="S">S</form:option>
			<form:option value="M">M</form:option>
			<form:option value="L">L</form:option>
			<form:option value="XL">XL</form:option>
		</form:select>
		<form:errors cssClass="error" path="size" />
		<br />
		<form:label path="description">
			<spring:message code="vehicle.description" />
		</form:label>
		<form:textarea class="register-todoAncho" path="description" />
		<form:errors cssClass="error" path="description" />
		<jstl:if test="${mode==\"create\"}">
			<acme:submit name="create" code="vehicle.create" />
			<acme:cancel url="vehicle/petShipper/list.do" code="vehicle.cancel" />
		</jstl:if>
		<jstl:if test="${mode==\"edit\"}">
			<acme:submit name="edit" code="vehicle.edit" />
			<acme:cancel url="vehicle/petShipper/list.do" code="vehicle.cancel" />
			<div class="modal fade" id="confirmDelete" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h4>
								<span class="glyphicon glyphicon-lock"></span> <spring:message code="vehicle.delete"/>
							</h4>
						</div>
						<div class="modal-body">
							<div class="modal-body">
								<spring:message code="vehicle.confirm.delete"/>
							</div>
								<button type="submit" name="delete" class="btn btn-block">
									<spring:message code="vehicle.delete"/> <span class="glyphicon glyphicon-ok"></span>
								</button>
						</div>
						<div class="modal-footer">
							<button 
								class="btn btn-danger btn-default pull-left"
								data-dismiss="modal">
								<span class="glyphicon glyphicon-remove"></span> <spring:message code="vehicle.cancel"/>
							</button>
						</div>
					</div>
				</div>
			</div>
			<a href="" class="btn btn-primary" data-toggle="modal" data-target="#confirmDelete"><spring:message code="vehicle.delete" /></a>
		</jstl:if>
	</form:form>
</jstl:if>

<jstl:if test="${isOwner!=true}">
	<spring:message code="vehicle.not.access" />
</jstl:if>
