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
	
	<form:form  modelAttribute="petOwner">
			<form:textarea readonly="true" class="area-autoAlto" path="description" />
	</form:form>
	
<!-- 		
<input class="area-autoAlto" readonly="readonly" value="${petOwner.description}"/>


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

<jstl:forEach var="rev" items="${reviews}"> 
<div class="col-md-8-2-noColor panel panel-default">
	 		<div class="wrap-3">
			<!-- <p><jstl:out value="${complaint.description}"/></p>  -->
			<p ><b class="register-left"><jstl:out value="${rev.reviewed.name}" /> <jstl:out value="${rev.reviewed.surname}" /></b>
			
			<span class="register-right">
			<jstl:if test="${rev.rating<1.0}">
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>

        	<jstl:if test="${rev.rating>=1.0 and rev.rating<2.0}">
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${rev.rating>=2.0 and rev.rating<3.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${rev.rating>=3.0 and rev.rating<4.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${rev.rating>=4.0 and rev.rating<5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${rev.rating==5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
        	</jstl:if>
        	</span>
        	</p>
        	<br/><br/>
			<p class="register-left"><jstl:out value="${rev.description}"/></p>
			<p class="text-rigth-small"><fmt:formatDate  value="${rev.creationMoment}" pattern="dd/MM/yyyy HH:mm" /></p>
			</div>
			
	 	</div>
</jstl:forEach>	
</div>
<!-- 
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

 -->