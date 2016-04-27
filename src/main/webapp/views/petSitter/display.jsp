<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
	
	

<div class="col-md-4">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petSitter-index.jpg">
	<br/>
	${sitter.name} ${sitter.surname} 
	<br/>
	${sitter.address}
	<br/>
	<acme:out code="petSitter.priceHour" path="${sitter.priceHour}"/>
	<br />
	<acme:out code="petSitter.priceNight" path="${sitter.priceNight}"/>
	<br/>
	${sitter.email}
	<br/>
	<a href="${sitter.homePage}" target="_blank">${sitter.homePage}</a>
	<br/>
	<jstl:if test="${principal}">
	<acme:cancel code="owner.edit" url="/petSitter/petSitter/edit.do?petSitterId=${sitter.id}" />
	<br />
</jstl:if>
</div>
<!-- 
<acme:out code="petSitter.name" path="${sitter.name}"/>
<br />
<acme:out code="petSitter.description" path="${sitter.description}"/>
<br />
<acme:out code="petSitter.address" path="${sitter.address}"/>
<br />
<acme:out code="petSitter.email" path="${sitter.email}"/>
<br />
<acme:out code="petSitter.priceHour" path="${sitter.priceHour}"/>
<br />
<acme:out code="petSitter.priceNight" path="${sitter.priceNight}"/>
<br />
<acme:out code="petSitter.rating" path="${sitter.rating}"/>
<br />
<acme:out code="petSitter.daysBeforeCancel" path="${sitter.daysBeforeCancel}"/>
<br />
<jstl:if test="${sitter.homePage != null}">
	<acme:out code="petSitter.homePage" path="${sitter.homePage}"/>
	<br />
</jstl:if>

<jstl:if test="${principal}">
	<acme:cancel code="petSitter.edit" url="/petSitter/petSitter/edit.do?petSitterId=${sitter.id}" />
	<br />
</jstl:if>
-->

<div class="col-md-8">
	
	<form:form  modelAttribute="sitter">
			<form:textarea readonly="true" class="area-autoAlto" path="description" />
	</form:form>

<!-- Reviews list with sortable date and rating -->
<h4><spring:message code="owner.reviews"/></h4>

<jstl:forEach var="rev" items="${reviews}"> 
<div class="col-md-8-2-noColor panel panel-default">
	 		<div class="wrap-3">
			<!-- <p><jstl:out value="${complaint.description}"/></p>  -->
			<p ><b class="register-left"><jstl:out value="${rev.reviewer.name}" /> <jstl:out value="${rev.reviewer.surname}" /></b>
			
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

<!-- Reviews list with sortable date and rating 
<h4><spring:message code="petSitter.reviews"/></h4>
<display:table name="reviews" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	<display:column property="creationMoment" titleKey="sitter.review.creationMoment" sortable="True">
		<fmt:formatDate value="${row.creationMoment}" pattern="dd/MM/yyyy HH:mm" />
	</display:column>  	
	
	<display:column property="description" titleKey="petSitter.description" />
	
	<display:column property="rating" titleKey="sitter.review.rating"  sortable="True"/> 
	
</display:table>
-->