<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<h2><spring:message code="supplier.suppliers"/></h2><hr>
<!-- <div class="col-md-6-3"> -->
 	
 		<div class="col-md-12">
 	<jstl:forEach var="row" items="${suppliers}">
 	<div class="col-md-12 panel panel-default">
 	<c:set var="authority" value="${row.user.authorities[0].authority}"/>

 	<br>
	<div class="col-md-1">
	
		
			 		<jstl:if test="${not empty row.photos}">
		 	<jstl:forEach var="ph" items="${row.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 		</jstl:forEach>
	<jstl:if test="${not empty phId}">
	<img class="userLittle img-circle img-center" alt="Your AVATAR" src="customer/viewPhoto.do?photoId=${phId}">
	</jstl:if>
	<jstl:if test="${empty phId}">
	
	
		 	<c:if test="${fn:contains(authority, 'PETSITTER')}"> 
   				<img class="img-circle userLittle img-center" src="images/petSitter-index.jpg">
 				</c:if> 
 				
 				<c:if test="${fn:contains(authority, 'PETSHIPPER')}"> 
   				<img class="img-circle userLittle img-center" src="images/petShipper-index.jpg">
 				</c:if> 
 				<c:if test="${fn:contains(authority, 'COMPANY')}"> 
   				<img class="img-circle userLittle img-center" src="images/company-index.jpg">
 				</c:if> 
	
	
	</jstl:if>
		</jstl:if>
			<jstl:if test="${empty row.photos}">
<!-- 						 	<img class="max-h-little img-center" alt="Your COMPANY" src="images/company-index.jpg"> -->
						 	
						 	
						 	<c:if test="${fn:contains(authority, 'PETSITTER')}"> 
   				<img class="img-circle userLittle img-center" src="images/petSitter-index.jpg">
 				</c:if> 
 				
 				<c:if test="${fn:contains(authority, 'PETSHIPPER')}"> 
   				<img class="img-circle userLittle img-center" src="images/petShipper-index.jpg">
 				</c:if> 
 				<c:if test="${fn:contains(authority, 'COMPANY')}"> 
   				<img class="img-circle userLittle img-center" src="images/company-index.jpg">
 				</c:if> 
			</jstl:if>
			 	
			 	
			 	
			 	
				
				
				
				
				
	
  	
 	
	 		</div>
	 	
	 		<div class="col-md-10">
	 		<h3 class="h3-no-bottom">${row.surname}, ${row.name} (${row.address})</h3>
	 		<table class="text-rigth-2">
	 			<tr>
	 			
	 			<td class="table-separate-100">
	 				<h3>${row.email}</h3>
	 			</td>
	 			<td class="table-separate-100">
	 				
	 		<jstl:if test="${row.rating<1.0}">
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>

        	<jstl:if test="${row.rating>=1.0 and row.rating<2.0}">
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${row.rating>=2.0 and row.rating<3.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${row.rating>=3.0 and row.rating<4.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${row.rating>=4.0 and row.rating<5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${row.rating==5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
        	</jstl:if>
	 			</td>
	 			
	 			
	 			<td class="table-separate-100">
	 				<jstl:if test="${row.blocked!=true}">
				<spring:message code="supplier.confirm.block" var="confirmBlock" />
				<form:form method="post" name="blockForm" id="blockForm"
					action="supplier/administrator/blockSitter.do">
					<input type="hidden" value="${row.id}" name="supplierId"
						id="supplierId" />
					<a href="javascript:{}" onclick="confirmSubmit('${confirmBlock}')"> <spring:message
							code="petSitter.block" />
					</a>
					<script>
						function confirmSubmit(message) {
							result = confirm(message);
							if(result==true){
								document.getElementById('blockForm').submit();
							}
						}
					</script>
				</form:form>
			</jstl:if>
	 			</td>
	 			
	 			</tr>
	 		</table>
	 		<p > <br/></p>
	 		<security:authorize access="hasRole('PETOWNER')">
	
		<a href="trip/petOwner/register.do?tripId=<jstl:out value="${row.id}"/>">	
			<spring:message code="trip.register"/>
		</a>
	
	</security:authorize>
	 		<security:authorize access="hasRole('PETSHIPPER')">
	 		<a class="text-left-small" href="registration/petShipper/list.do?tripId=<jstl:out value="${row.id}"/>">	
			<spring:message code="trip.registrations"/></a></security:authorize>
	
		</div>		
	<br>
	
	

	 	</div>
	 	
 	</jstl:forEach>
 	</div>
<!--  	<div class="col-md-1"> -->
<!--  		</div> -->
<!--  </div> -->

