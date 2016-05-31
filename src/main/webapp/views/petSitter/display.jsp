<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<div class="col-md-12">
<div class="col-md-4">
	
	<jstl:if test="${empty sitter.photos}">
	<img class="register-todoAncho" alt="Your AVATAR" src="images/petSitter-index.jpg">
	</jstl:if>
	<jstl:if test="${not empty sitter.photos}">
	
	<jstl:forEach var="ph" items="${sitter.photos}">
		 		<jstl:if test="${ph.avatar==true}">
					<jstl:set var="phId" value="${ph.id}"/>	 		
		 		</jstl:if>
		 		</jstl:forEach>
	<jstl:if test="${not empty phId}">
	<img class="userPhotoAvatar" alt="Your AVATAR" src="customer/viewPhoto.do?photoId=${phId}">
	</jstl:if>
	<jstl:if test="${empty phId}">
	<img class="userPhotoAvatar" alt="Your AVATAR" src="images/petSitter-index.jpg">
	</jstl:if>
	</jstl:if>
	<br>
	<br>
	
	 
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

<jstl:if test="${empty sitter.photos}">
			<h2><spring:message code="petSitter.noPhotos"/></h2>
			<img  class="center-block petPhoto"
									src="images/pet-nophoto.jpg">
			
</jstl:if>
<jstl:if test="${not empty sitter.photos }">
		

		<div class="col-md-12">


			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<jstl:forEach var="i" begin="0" end="${sitter.photos.size() - 1}">
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
					<jstl:forEach var="eachPhoto" items="${sitter.photos}">
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
			url="/petSitter/petSitter/edit.do?petSitterId=${sitter.id}" />
		
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
      <div class="panel-heading"> <strong style="font-size:200%;"> ${sitter.name} ${sitter.surname}</strong></div>
      <div class="panel-body">
      
      
      
    
	  ${sitter.address} <br />
	<acme:out code="petSitter.priceHour" path="${sitter.priceHour}" />
	<br />
	<acme:out code="petSitter.priceNight" path="${sitter.priceNight}" />
	<br /> ${sitter.email} <br /> <a href="${sitter.homePage}"
		target="_blank">${sitter.homePage}</a> <br />
      
      
      </div>
    </div>
    
   
	 
	 <div class="panel panel-default">
	 <div class="panel-heading"> <strong style="font-size:150%;"><spring:message code="sitter.description" /></strong></div>
      <div class="panel-body">
      
      
      
    
	  ${sitter.description} <br />
	
      
      </div>
    </div>
	 
	 
			</div>
	

		   
		   
		   <div class="col-md-8 ">
		   <h4>
	<spring:message code="owner.reviews" />
</h4>
<jstl:if test="${reviews.size()==0}">
			<h2><spring:message code="petSitter.noReviews"/></h2>
</jstl:if>
<jstl:forEach var="rev" items="${reviews}">
	<div class="col-md-8-2-noColor panel panel-default">
		<div class="wrap-3">
			<!-- <p><jstl:out value="${complaint.description}"/></p>  -->
			<p>
				<b class="register-left"><jstl:out value="${rev.reviewer.name}" />
					<jstl:out value="${rev.reviewer.surname}" /></b> <span
					class="register-right"> <jstl:if test="${rev.rating<1.0}">
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
					</jstl:if> <jstl:if test="${rev.rating>=1.0 and rev.rating<2.0}">
						<img src="images/star.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
					</jstl:if> <jstl:if test="${rev.rating>=2.0 and rev.rating<3.0}">
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
					</jstl:if> <jstl:if test="${rev.rating>=3.0 and rev.rating<4.0}">
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star-empty.png" />
						<img src="images/star-empty.png" />
					</jstl:if> <jstl:if test="${rev.rating>=4.0 and rev.rating<5.0}">
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star-empty.png" />
					</jstl:if> <jstl:if test="${rev.rating==5.0}">
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star.png" />
						<img src="images/star.png" />
					</jstl:if>
				</span>
			</p>
			<br />
			<br />
			<p class="register-left">
				<jstl:out value="${rev.description}" />
			</p>
			<p class="text-rigth-small">
				<fmt:formatDate value="${rev.creationMoment}"
					pattern="dd/MM/yyyy HH:mm" />
			</p>
		</div>

	</div>
</jstl:forEach>
	

</div>
</div>


	<br>
<div class="col-md-12">
<!-- Reviews list with sortable date and rating -->

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