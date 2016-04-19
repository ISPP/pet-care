<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="col-md-4">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petOwner-index.jpg">
	<br/>
	${petOwner.name} ${petOwner.surname} 
	<br/>
	${petOwner.address}
	<br/>
	${petOwner.email}
	<br/>
	<a href="${petOwner.homePage}" target="_blank">${petOwner.homePage}</a>
	<br/>
	<jstl:if test="${principal}">
	<acme:cancel code="owner.edit" url="/petOwner/petOwner/edit.do?petOwnerId=${petOwner.id}" />
	<br />
</jstl:if>
</div>

<div class="col-md-8">

	<input class="area-autoAlto" readonly="readonly" value="${petOwner.description}"/>
<!-- 		
<acme:out code="owner.fullName" path="${petOwner.name}"/>
<br />
<acme:out code="owner.description" path="${petOwner.description}"/>
<br />
<acme:out code="owner.address" path="${petOwner.address}"/>
<br />
<acme:out code="owner.email" path="${petOwner.email}"/>
<br />
<jstl:if test="${petOwner.homePage != null}">
	<acme:out code="owner.homePage" path="${petOwner.homePage}"/>
	<br />
</jstl:if>
 -->


<!-- Reviews list with sortable date and rating -->
<h4><spring:message code="owner.reviews"/></h4>
<display:table name="reviews" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	<display:column property="creationMoment" titleKey="owner.review.creationMoment" sortable="True">
		<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm" />
	</display:column>  	
	
	<display:column property="description" titleKey="owner.description" />
	
	<display:column property="rating" titleKey="owner.review.rating"  sortable="True"/> 
	
	<display:column titleKey="owner.review.reviewed">
		<a href="search/display.do?petSitterId=${row.reviewed.id}"> <jstl:out value="${row.reviewed.name}" /> <jstl:out value="${row.reviewed.surname}" /></a>
	</display:column>
	
</display:table>
</div>