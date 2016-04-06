<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
		
<acme:out code="administrator.name" path="${administrator.name}"/>
<br />
<acme:out code="administrator.surname" path="${administrator.surname}"/>
<br />
<acme:out code="administrator.email" path="${administrator.email}"/>
<br />

<jstl:if test="${principal}">
	<acme:cancel code="administrator.edit" url="/administrator/administrator/edit.do?administratorId=${administrator.id}" />
	<br />
</jstl:if>