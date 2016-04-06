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
			
		<input type="file" name="file" />
				
		<br/>

		<!-- Buttons -->
	
			<acme:submit name="add" code="pet.add" />
		
			<input type="button" name="back"
				value="<spring:message code="pet.back" />"
				onClick="history.back(-1)" />
		
	</form:form>
		
</security:authorize>

<security:authorize access="hasRole('PETSITTER')">

	<form:form action="pet/petSitter/addPhoto.do" modelAttribute="photoForm" enctype="multipart/form-data">
	
		<form:hidden path="id" />
		<form:hidden path="petId" />
			
		<input type="file" name="file" />
				
		<br/>

		<!-- Buttons -->
	
			<acme:submit name="add" code="pet.add" />
		
			<input type="button" name="back"
				value="<spring:message code="pet.back" />"
				onClick="history.back(-1)" />
		
	</form:form>
		
</security:authorize>
</jstl:if>


