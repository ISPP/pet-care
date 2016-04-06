
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

	<display:table name="pets" uid="pet" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
			
		<acme:column codeHeaderColumn="pet.name" property="name" sortable="true"/> 
		<acme:column codeHeaderColumn="pet.kind" property="kind" sortable="true"/> 
		<acme:columnButton href="pet/see.do?petId=${pet.id}" codeButton="pet.see" codeHeaderColumn="pet.see"/>
					
		<security:authorize access="hasRole('PETOWNER')"> 
			<acme:columnButton href="pet/petOwner/edit.do?petId=${pet.id}" codeButton="pet.edit" codeHeaderColumn="pet.edit"/>
		</security:authorize>
		
		<security:authorize access="hasRole('PETSITTER')"> 
			<acme:columnButton href="pet/petSitter/edit.do?petId=${pet.id}" codeButton="pet.edit" codeHeaderColumn="pet.edit"/>
		</security:authorize>
						
	</display:table>
	