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
		<form:label path="size">
			<spring:message code="vehicle.size"/>
		</form:label>
		<form:select path="size">
			<form:option value="S">S</form:option>
			<form:option value="M">M</form:option>
			<form:option value="L">L</form:option>
			<form:option value="XL">XL</form:option>
		</form:select>
		<form:errors path="size" cssClass="error"/>
		<br/>
		<form:label path="description">
			<spring:message code="vehicle.description"/>
		</form:label>
		<form:textarea path="description"/>
		<form:errors path="description" cssClass="error"/>
		<br/>
		<acme:submit name="create" code="vehicle.create"/>
		<acme:cancel url="vehicle/petShipper/list.do" code="vehicle.cancel"/>
	</form:form>
</jstl:if>

<jstl:if test="${isOwner!=true}">
	<spring:message code="vehicle.not.access" />
</jstl:if>
