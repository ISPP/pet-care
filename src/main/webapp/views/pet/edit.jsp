<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${isOwner==true}">

<security:authorize access="hasRole('PETOWNER')">

	<form:form action="pet/petOwner/edit.do" modelAttribute="pet">
	
		<div class="col-md-6">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="photos" />
		<form:hidden path="petOwner" />
			
		<acme:textbox code="pet.name" path="name" />
		<acme:textbox code="pet.breed" path="breed"/>
		<acme:textbox code="pet.kind" path="kind" placeholder="DOG, CAT, BIRD, OTHER"/>
		<br/>
		<form:label class="register-left"  path="description">
			<spring:message code="pet.description" />
		</form:label>
		<br/>
		<spring:message var="writeHere" code="pet.description.writeHere"/>
		<form:textarea class="register-todoAncho" path="description" placeholder="${writeHere}"/>
		
		</div>
		
		<div class="col-md-6">
		<img class="max-h-2" alt="Your PET" src="images/pet-register.jpg">
		</div>
		<br/>

		<!-- Buttons -->
		<div class="col-md-12 button-bottom">
			<acme:submit name="save" code="pet.save" />
			
			<jstl:if test="${pet.id !=0}">
				<spring:message code="pet.confirm.delete" var="varConfirmDelete"/>
				<acme:submit name="delete" code="pet.delete" onclick="return confirm('${varConfirmDelete}')"/>
			</jstl:if>
			
			<input type="button" name="back"
				value="<spring:message code="pet.back" />"
				onClick="history.back(-1)" />
		</div>
	</form:form>
		
</security:authorize>	

<security:authorize access="hasRole('PETSITTER')">

	<form:form action="pet/petSitter/edit.do" modelAttribute="pet">
	
		<form:hidden path="id" />
		<form:hidden path="petSitter" />
			
		<acme:textbox code="pet.name" path="name" />
		<acme:textarea code="pet.description" path="description" />
		<acme:textbox code="pet.breed" path="breed"/>
		<acme:textbox code="pet.kind" path="kind" placeholder="DOG, CAT, BIRD, OTHER"/>
		
		<br/>

		<!-- Buttons -->
	
			<acme:submit name="save" code="pet.save" />
			
			<jstl:if test="${pet.id !=0}">
				<spring:message code="pet.confirm.delete" var="varConfirmDelete"/>
				<acme:submit name="delete" code="pet.delete" onclick="return confirm('${varConfirmDelete}')"/>
			</jstl:if>
			
			<input type="button" name="back"
				value="<spring:message code="pet.back" />"
				onClick="history.back(-1)" />
		
	</form:form>
		
</security:authorize>	


</jstl:if>

