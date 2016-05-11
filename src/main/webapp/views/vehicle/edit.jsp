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

<div class="col-md-6">
<img alt="Your vehicle" src="images/car-dog.jpg">

</div>
<div class="col-md-6">
<jstl:if test="${isOwner==true}">
	<form:form action="vehicle/petShipper/${mode}.do"
		modelAttribute="vehicle">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="trips" />
		<form:hidden path="petShipper" />
		<div class="col-md-12">
		<!--<acme:formInput code="vehicle.title" path="title" />-->
		
				<form:label style="width:10%;" path="title">
					<b><spring:message code="vehicle.title" /></b>
				</form:label>
				<form:input style="width:65%;" path="title" />
				<form:errors path="title" cssClass="error" />
		
		<form:label style="width:15%;" path="size">
			<spring:message code="vehicle.size" />
		</form:label>
		<form:select style="width:8%;" path="size">
			<form:option value="S">S</form:option>
			<form:option value="M">M</form:option>
			<form:option value="L">L</form:option>
			<form:option value="XL">XL</form:option>
		</form:select>
		<form:errors cssClass="error" path="size" />
		</div>
		<div class="col-md-12">
		<form:label style="padding-top:10px;float:left;" path="description">
			<spring:message code="vehicle.description" />
		</form:label>
		
		<form:textarea class="register-todoAncho" rows="7" path="description" />
		<form:errors cssClass="error" path="description" />
		</div>
		<div style="padding-top:15px;" class="col-md-12">
		<jstl:if  test="${mode==\"create\"}">
		<input type="submit" name="create"  class="btnAccept"
				value="<spring:message code="vehicle.create"  />" />
		<input type="button" name="cancel" class="btnCancel"
				value="<spring:message code="vehicle.cancel"/>"
				onclick="javascript: window.location.replace('vehicle/petShipper/list.do');" />		
		</jstl:if>
		</div>
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
</div>