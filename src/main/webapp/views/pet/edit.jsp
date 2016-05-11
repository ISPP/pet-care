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

	<security:authorize access="hasRole('PETOWNER')">

		<form:form action="pet/petOwner/edit.do" modelAttribute="pet">

			<div class="col-md-6">
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="photos" />
				<form:hidden path="petOwner" />
				
				
				<div class="form-group text-left">

			<form:label path="name">
			<spring:message code="pet.name" />:
			</form:label>
			<form:input code="pet.name" path="name"
			class="form-control" />
		

			<form:errors path="name" cssClass="error" />
			</div>
			
				<div class="form-group text-left">

			<form:label path="breed">
			<spring:message code="pet.breed" />:
			</form:label>
			<form:input code="pet.breed" path="breed"
			class="form-control" />
		

			<form:errors path="breed" cssClass="error" />
			</div>
			
				<div class="form-group text-left">

			<form:label path="kind">
			<spring:message code="pet.kind" />:
			</form:label>
			<form:input code="pet.kind" path="kind"
			class="form-control" 
			placeholder="DOG, CAT, BIRD, OTHER"/>
		

			<form:errors path="kind" cssClass="error" />
			</div>
			<div class="form-group text-left">
				<form:label class="register-left" path="description">
					<spring:message code="pet.description" />
				</form:label>
				<br />
				<spring:message var="writeHere" code="pet.description.writeHere" />
				<form:textarea class="form-control" path="description"
					placeholder="${writeHere}" />
			</div>
			</div>

			<div class="col-md-6">
				<img class="max-h-2" alt="Your PET" src="images/pet-register.jpg">
			</div>
			<br />

			<!-- Buttons -->
			<div class="col-md-12 button-bottom">
				<input type="submit" name="save" class="btnAccept"
		value="<spring:message code="pet.save" />" />

				<jstl:if test="${pet.id !=0}">
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
									<button type="submit" name="delete" class="btn btn-block">
										<spring:message code="vehicle.delete" />
										<span class="glyphicon glyphicon-ok"></span>
									</button>
								</div>
								<div class="modal-footer">
									<button class="btn btn-danger btn-default pull-left"
										data-dismiss="modal">
										<span class="glyphicon glyphicon-remove"></span>
										<spring:message code="vehicle.cancel" />
									</button>
								</div>
							</div>
						</div>
					</div>
					<%-- <spring:message code="pet.confirm.delete" var="varConfirmDelete"/> --%>
					<jstl:if test="${deleteable==true}">
						<a href="" class="btnCancel" data-toggle="modal"
							data-target="#confirmDelete"><spring:message code="pet.delete" /></a>
					</jstl:if>
				</jstl:if>

				<input type="button" class="btnCancel" name="back"
					value="<spring:message code="pet.back" />"
					onClick="history.back(-1)" />


			</div>
		</form:form>

	</security:authorize>

	<security:authorize access="hasRole('PETSITTER')">
	

		<form:form action="pet/petSitter/edit.do" modelAttribute="pet">
	<div class="col-md-6">
			<form:hidden path="id" />
			<form:hidden path="petSitter" />

				
				<div class="form-group text-left">

			<form:label path="name">
			<spring:message code="pet.name" />:
			</form:label>
			<form:input code="pet.name" path="name"
			class="form-control" />
		

			<form:errors path="name" cssClass="error" />
			</div>
			
				<div class="form-group text-left">

			<form:label path="breed">
			<spring:message code="pet.breed" />:
			</form:label>
			<form:input code="pet.breed" path="breed"
			class="form-control" />
		

			<form:errors path="breed" cssClass="error" />
			</div>
			
				<div class="form-group text-left">

			<form:label path="kind">
			<spring:message code="pet.kind" />:
			</form:label>
			<form:input code="pet.kind" path="kind"
			class="form-control" 
			placeholder="DOG, CAT, BIRD, OTHER"/>
		

			<form:errors path="kind" cssClass="error" />
			</div>
			<div class="form-group text-left">
				<form:label class="register-left" path="description">
					<spring:message code="pet.description" />
				</form:label>
				<br />
				<spring:message var="writeHere" code="pet.description.writeHere" />
				<form:textarea class="form-control" path="description"
					placeholder="${writeHere}" />
			</div>
		
</div>

	<div class="col-md-6">
				<img class="max-h-2" alt="Your PET" src="images/pet-register.jpg">
			</div>

			<!-- Buttons -->
<div class="col-md-12">
			<input type="submit" name="save" class="btnAccept"
		value="<spring:message code="pet.save" />" />

			<jstl:if test="${pet.id !=0}">
				<spring:message code="pet.confirm.delete" var="varConfirmDelete" />
				<input type="submit" class="btnCancel" name="delete" 
					onclick="return confirm('${varConfirmDelete}')" 
					value="<spring:message code="pet.delete" />"  />
			</jstl:if>

			<input class="btnCancel" type="button" name="back"
				value="<spring:message code="pet.back" />"
				onClick="history.back(-1)" />
		
		</div>
		</form:form>

	</security:authorize>


</jstl:if>

