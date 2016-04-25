
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<!-- 
	<display:table name="pets" uid="pet" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
		<div class="col-md-6">
		<img class="max-h" alt="Your PET" src="images/pet-register.jpg">
		<acme:column codeHeaderColumn="pet.name" property="name" sortable="true"/> 
		<acme:column codeHeaderColumn="pet.kind" property="kind" sortable="true"/> 
		<acme:columnButton href="pet/see.do?petId=${pet.id}" codeButton="pet.see" codeHeaderColumn="pet.see"/>
		</div>			
		<security:authorize access="hasRole('PETOWNER')"> 
			<acme:columnButton href="pet/petOwner/edit.do?petId=${pet.id}" codeButton="pet.edit" codeHeaderColumn="pet.edit"/>
		</security:authorize>
		
		<security:authorize access="hasRole('PETSITTER')"> 
			<acme:columnButton href="pet/petSitter/edit.do?petId=${pet.id}" codeButton="pet.edit" codeHeaderColumn="pet.edit"/>
		</security:authorize>
						
	</display:table>
	 -->
	 <div class="col-md-12">
	<h2><spring:message code="pet.myPets"/></h2><hr>
	</div>
	<jstl:forEach var="pet" items="${pets}">
	 <div style="cursor: pointer;" onclick="location.href='pet/see.do?petId=${pet.id}';" class="col-md-4 panel panel-default">
	 	<div class="wrap">
	 	<a  href="pet/see.do?petId=${pet.id}" codeButton="pet.see" codeHeaderColumn="pet.see">
	 	<jstl:if test="${pet.photos.size()<1}">
	 		<img class="max-h img-left" alt="Your PET" src="images/pet-register.jpg">
	 	</jstl:if>
	 	<jstl:if test="${pet.photos.size()>0}">
		 	<jstl:forEach var="ph" items="${pet.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 	</jstl:forEach>
		 	<img class="max-h img-left" alt="Your PET" src="pet/displayPhoto.do?photoId=${phId}">
		 </jstl:if>
			</a>
			<div class="midl-left">
			<h3><jstl:out value=" ${pet.name}"/></h3>
			<jstl:out value=" ${pet.kind}"/>
			</div>
		
		</div>
		
	</div>
	</jstl:forEach>