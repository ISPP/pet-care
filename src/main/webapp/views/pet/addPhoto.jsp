<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${isOwner==true}">
<security:authorize access="hasRole('PETOWNER')">

	<form:form action="pet/petOwner/addPhoto.do" modelAttribute="photoForm" enctype="multipart/form-data">
	
		<form:hidden path="id" />
		<form:hidden path="petId" />
		<div class="col-md-6-2">
		<input type="file" name="file" />
				
		<br/>

		<!-- Buttons -->
	<input type="submit" name="add" class="btnAccept"
		value="<spring:message code="pet.add" />" />

	
	<input type="button" name="c" class="btnCancel"
		value="<spring:message code="pet.back" />"
		onclick="history.back(-1)" />
	<br />
	
		
		</div>
	</form:form>
		
</security:authorize>

<security:authorize access="hasRole('PETSITTER')">

	<form:form action="pet/petSitter/addPhoto.do" modelAttribute="photoForm" enctype="multipart/form-data">
	
		<form:hidden path="id" />
		<form:hidden path="petId" />
			
		<input type="file" name="file"/>
				
		<br/>

		<!-- Buttons -->
	
			<input type="submit" name="add" class="btnAccept"
		value="<spring:message code="pet.add" />" />

	
	<input type="button" name="c" class="btnCancel"
		value="<spring:message code="pet.back" />"
		onclick="history.back(-1)" />
	<br />
		
	</form:form>
		
</security:authorize>
</jstl:if>


