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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:set value="${requestURI}" var="action" />

	<div class="container text-center">
	
			<jstl:forEach var="company" items="${companies}">
			
			<jstl:if test="${i%3==0 and i>2}">
	<div class="row"></div>
	
	</jstl:if>
	<jstl:set var="i" value="${i+1}" />
			 <div style="cursor: pointer;" onclick="location.href='booking/petOwner/createForCompany.do?companyId=${company.id}&startMoment=${searchSuppliersForm.startDate}&endMoment=${searchSuppliersForm.endDate}';"  class="col-md-4 container-fluid panel panel-default">
			 	<div class="wrap">
			 	<jstl:if test="${toBook==true}">
			 	
			 		<jstl:if test="${not empty company.photos}">
		 	<jstl:forEach var="ph" items="${company.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 		</jstl:forEach>
	<jstl:if test="${not empty phId}">
	<img class="userLittle img-center" alt="Your AVATAR" src="customer/viewPhoto.do?photoId=${phId}">
	</jstl:if>
	<jstl:if test="${empty phId}">
	<img class="max-h-little img-center" alt="Your COMPANY" src="images/company-index.jpg"/>
	</jstl:if>
		</jstl:if>
			<jstl:if test="${empty company.photos}">
						 	<img class="max-h-little img-center" alt="Your COMPANY" src="images/company-index.jpg">
			</jstl:if>
			 	
			 	
			 	
			 	
				
				
				
				
				
				
				
				
				<span  class="hM3 carousel-caption desc"><jstl:out value=" ${company.pricePerDay}"/>&#8364;</span>
			 	</jstl:if>
				
				<jstl:if test="${toBook == false}">
					<jstl:if test="${not empty company.photos}">
		 	<jstl:forEach var="ph" items="${company.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 		</jstl:forEach>
	<jstl:if test="${not empty phId}">
	<img class="userLittle img-center" alt="Your AVATAR" src="customer/viewPhoto.do?photoId=${phId}">
	</jstl:if>
	<jstl:if test="${empty phId}">
	<img class="max-h-little img-center" alt="Your COMPANY" src="images/company-index.jpg"/>
	</jstl:if>
		</jstl:if>
			<jstl:if test="${empty company.photos}">
						 	<img class="max-h-little img-center" alt="Your COMPANY" src="images/company-index.jpg">
			</jstl:if>
				
				
					<span  class="hM3 carousel-caption desc"><jstl:out value=" ${company.pricePerDay}"/>&#8364;</span>
				</jstl:if>
				</div>
				
				<div>
				<strong><jstl:out value="${company.name}"/></strong><hr class="linea-pegada"/>
				<jstl:out value=" ${company.address}"/>
				<br/>
				</div>
			
			
			<jstl:if test="${company.rating<1.0}">
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>

        	<jstl:if test="${company.rating>=1.0 and company.rating<2.0}">
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=2.0 and company.rating<3.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=3.0 and company.rating<4.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating>=4.0 and company.rating<5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star-empty.png"/>
        	</jstl:if>
        	
        	<jstl:if test="${company.rating==5.0}">
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
            <img src="images/star.png"/>
        	</jstl:if>
        	<security:authorize access="hasRole('PETOWNER')">
        	<div class="col-md-12">
        	<a href="company/company/display.do?companyId=${company.id}"><spring:message code="petSitter.display.profile"/></a>
        	</div>
        	</security:authorize>
			
			</div>
			</jstl:forEach>
			<div class="col-md-12">
			<jstl:if test="${companies.size()==0}">
				<h2><spring:message code="search.noResults"/></h2>
			</jstl:if>
			<jstl:if test="${toBook==false}">
			<spring:message code="search.mustRegister"/><br/>
			</jstl:if>
			<spring:message code="search.priceShowsNight"/>
			</div>
			</div>
