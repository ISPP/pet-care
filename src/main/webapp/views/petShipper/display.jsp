<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
		
<div class="col-md-4">
<jstl:if test="${empty shipper.photos}">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petShipper-index.jpg">
	</jstl:if>
	<jstl:if test="${not empty shipper.photos}">
	
	<jstl:forEach var="ph" items="${shipper.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 		</jstl:forEach>
	<jstl:if test="${not empty phId}">
	<img class="register-todoAncho" alt="Your AVATAR" src="customer/viewPhoto.do?photoId=${phId}">
	</jstl:if>
	<jstl:if test="${empty phId}">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petShipper-index.jpg">
	</jstl:if>
	</jstl:if>
	
	<br/>
	${shipper.name} ${shipper.surname} 
	<br/>
	${shipper.address}
	<br/>
	${shipper.email}
	<br/>
	<a href="${shipper.homePage}" target="_blank">${shipper.homePage}</a>
	<br/>
	<jstl:if test="${principal}">
	<acme:cancel code="owner.edit" url="/petShipper/petShipper/edit.do?petShipperId=${shipper.id}" />
	<br />
	<br />
		<acme:cancel code="customer.addPhoto"
			url="/customer/addPhoto.do" />
</jstl:if>
</div>

<div class="col-md-8">
<div class="col-md-12 ">
	
	<form:form  modelAttribute="shipper">
			<form:textarea readonly="true" class="area-autoAlto panel panel-default" path="description" />
	</form:form>
</div>	



<jstl:if test="${not empty shipper.photos }">
<div class="col-md-12">

<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
  <jstl:forEach var="i" begin="0" end="${shipper.photos.size() - 1}">
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
  <jstl:forEach var="eachPhoto" items="${shipper.photos}">
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
</jstl:if>





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