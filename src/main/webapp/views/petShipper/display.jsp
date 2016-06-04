<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div class="col-md-12">
<div class="col-md-4">
	
	<jstl:if test="${empty petShipper.photos}">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petShipper-index.jpg">
	</jstl:if>
	<jstl:if test="${not empty petShipper.photos}">
	
	<jstl:forEach var="ph" items="${petShipper.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 		</jstl:forEach>
	<jstl:if test="${not empty phId}">
	<img class="userPhotoAvatar" alt="Your AVATAR" src="customer/viewPhoto.do?photoId=${phId}">
	</jstl:if>
	<jstl:if test="${empty phId}">
	<img class="userPhotoAvatar" alt="Your AVATAR" src="images/petShipper-index.jpg">
	</jstl:if>
	</jstl:if>
	<br>
	<br>
	
	 
</div>



<div class="col-md-8">

<jstl:if test="${empty petShipper.photos}">
			<h2><spring:message code="petSitter.noPhotos"/></h2>
			<img  class="center-block petPhoto"
									src="images/pet-nophoto.jpg">
			
</jstl:if>
<jstl:if test="${not empty petShipper.photos }">
		

		<div class="col-md-12">


			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<jstl:forEach var="i" begin="0" end="${petShipper.photos.size() - 1}">
						<jstl:if test="${i == 0}">
							<li data-target="#myCarousel" data-slide-to="i" class="active"></li>
						</jstl:if>
						<jstl:if test="${i != 0}">
							<li data-target="#myCarousel" data-slide-to="i"></li>
						</jstl:if>
					</jstl:forEach>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<jstl:set var="positionImage" value="0" />
					<jstl:forEach var="eachPhoto" items="${petShipper.photos}">
						<jstl:if test="${positionImage == 0}">

							<div class="item active">
								<img  class="center-block userPhoto"
									src="customer/viewPhoto.do?photoId=${eachPhoto.id}">
								<jstl:if test="${principal}">
									<jstl:if test="${not eachPhoto.avatar}">
										<acme:button classAux="btnCancel"
											href="customer/selectAvatar.do?photoId=${eachPhoto.id}"
											code="pet.selectAvatar" />
									</jstl:if>
									<jstl:if test="${eachPhoto.avatar}">


											
											<input type="button" class="btnAccept"
									value="<spring:message code="customer.selected" />" />
									</jstl:if>
								</jstl:if>
							</div>


						</jstl:if>
						<jstl:if test="${positionImage != 0}">

							<div class="item">
								<img class="center-block userPhoto"
									src="customer/viewPhoto.do?photoId=${eachPhoto.id}">
								<jstl:if test="${principal}">
									<jstl:if test="${not eachPhoto.avatar}">
										<acme:button classAux="btnCancel"
											href="customer/selectAvatar.do?photoId=${eachPhoto.id}"
											code="pet.selectAvatar" />
									</jstl:if>
									<jstl:if test="${eachPhoto.avatar}">

										<input type="button" class="btnAccept"
									value="<spring:message code="customer.selected" />" />
									</jstl:if>
								</jstl:if>
							</div>



						</jstl:if>
						<jstl:set var="positionImage" value="1" />

					</jstl:forEach>
				</div>


				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			</div>
	
	</jstl:if>
	
	 <jstl:if test="${principal}">
	 <div class="col-md-12 row">
<br />
		
		<acme:cancel code="owner.edit"
			url="/petShipper/petShipper/edit.do?petShipperId=${shipper.id}" />
		
		<acme:cancel code="customer.addPhoto"
			url="/customer/addPhoto.do" />
	
		<br />
			   </div>
	</jstl:if>
	 
</div>

		</div>
	
		   
		   
		   
		   <div class="col-md-12 ">
		   <div class="col-md-4 ">
		   <div class="panel panel-default">
      <div class="panel-heading"> <strong style="font-size:200%;"> ${petShipper.name} ${petShipper.surname}</strong></div>
      <div class="panel-body">
      
      
      
    
	  ${petShipper.address} <br />
	
	<br /> ${petShipper.email} <br /> <a href="${petShipper.homePage}"
		target="_blank">${petShipper.homePage}</a> <br />
      
      
      </div>
    </div>
    
   
	 
	
	

		 </div>   
		   <br><br>
		   <div class="col-md-8 ">
		   
		
 <div class="panel panel-default">
	 <div class="panel-heading"> <strong style="font-size:150%;"><spring:message code="sitter.description" /></strong></div>
      <div class="panel-body">
      
      
      
    
	  ${petShipper.description} <br />
	
      
      </div>
    </div>
	
	 
			</div>


	


</div>


	
<div class="col-md-12">
<!-- Reviews list with sortable date and rating -->

</div>


