<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="col-md-4">
<jstl:if test="${empty petOwner.photos}">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petOwner-index.jpg">
	</jstl:if>
	<jstl:if test="${not empty petOwner.photos}">
	
	<jstl:forEach var="ph" items="${petOwner.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 		</jstl:forEach>
	<jstl:if test="${not empty phId}">
	<img class="register-todoAncho" alt="Your AVATAR" src="customer/viewPhoto.do?photoId=${phId}">
	</jstl:if>
	<jstl:if test="${empty phId}">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petOwner-index.jpg">
	</jstl:if>
	</jstl:if>
	
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
<div class="col-md-6 ">
	
	<form:form  modelAttribute="petOwner">
			<form:textarea readonly="true" class="area-autoAlto panel panel-default" path="description" />
	</form:form>
</div>	

<div class="col-md-6">


<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
  <jstl:forEach var="i" begin="0" end="${petOwner.photos.size() - 1}">
  <jstl:if test="${i == 0}">
    <li data-target="#myCarousel" data-slide-to="i" class="active"></li>
    </jstl:if>
    <jstl:if test="${i != 0}">
    <li data-target="#myCarousel" data-slide-to="i" ></li>
    </jstl:if>
   </jstl:forEach>
  </ol>

  <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
 <jstl:set var="positionImage" value="0" />
  <jstl:forEach var="eachPhoto" items="${petOwner.photos}">
  <jstl:if test="${positionImage == 0}">
 
    <div class="item active">
      <img class="center-block petPhoto" src="customer/viewPhoto.do?photoId=${eachPhoto.id}">
    <jstl:if test="${principal}">
     <jstl:if test="${not eachPhoto.avatar}">
    <acme:button classAux="btnCancel"
									href="customer/selectAvatar.do?photoId=${eachPhoto.id}"
									code="pet.selectAvatar" />
									</jstl:if>
<jstl:if test="${eachPhoto.avatar}">

<acme:button classAux="btnCancel"
									href="#"
									code="customer.selected" />
</jstl:if>
    </jstl:if>
    </div>
   
  
  </jstl:if>
  <jstl:if test="${positionImage != 0}">

    <div class="item">
      <img class="center-block petPhoto" src="customer/viewPhoto.do?photoId=${eachPhoto.id}">
    <jstl:if test="${principal}">
    <jstl:if test="${not eachPhoto.avatar}">
    <acme:button classAux="btnCancel"
									href="customer/selectAvatar.do?photoId=${eachPhoto.id}"
									code="pet.selectAvatar" />
									</jstl:if>
<jstl:if test="${eachPhoto.avatar}">

<acme:button classAux="btnCancel"
									href="#"
									code="customer.selected" />
</jstl:if>
   </jstl:if>
    </div>
    
    
  
  </jstl:if>
   <jstl:set var="positionImage" value="1" />
   
 </jstl:forEach>
 </div>


  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>










</div>
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
<div class="col-md-12">

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