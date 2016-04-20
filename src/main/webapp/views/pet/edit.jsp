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
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="trips"/>
		<form:hidden path="petShipper"/>
		
		<acme:formInput code="vehicle.title" path="title" />
		<acme:multipleSelect items="sizes" var="size_var" itemLabel="size" code="vehicle.size" path="size" />
		<acme:textarea code="vehicle.description" path="description" />
		<acme:cancel url="vehicle/petShipper/list.do" code="vehicle.cancel"/>
		<acme:submit name="create" code="vehicle.create"/>
	</form:form>
</jstl:if>

<jstl:if test="${isOwner!=true}">
	<spring:message code="vehicle.not.access" />
</jstl:if>
