
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="vehicles" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column titleKey="vehicle.title" property="title"/>
	<display:column titleKey="vehicle.size" property="size"/>
	<acme:columnJstlIfWithLink href="vehicle/petShipper/display.do?vehicleId=${row.id}" codeHeaderColumn="" codeLinkColumn="vehicle.display" test=""/>
	
</display:table>
