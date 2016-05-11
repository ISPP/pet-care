<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('PETOWNER')">

	<div class="col-md-4">
		<jstl:if test="${avatar!=null}">
			<img class="register-todoAncho petPhoto" alt="Your AVATAR"
				src="pet/displayPhoto.do?photoId=${avatar.id}" />
			<br />
		</jstl:if>
		<jstl:if test="${avatar==null}">
			<img class="register-todoAncho petPhoto" alt="Your AVATAR"
				src="images/pet-register.jpg">
			<br />
		</jstl:if>

		<h3>
			<jstl:out value="${pet.name}" />
		</h3>
		<jstl:out value="${pet.kind}" />
		
		<jstl:out value="${pet.breed}" />
		<br />
		<jstl:out value="${pet.description}" />
	</div>

	<div class="col-md-6-2">
		<jstl:if test="${numberOfPhotos!=0}">
			<div class="carousel slide" data-ride="carousel"
				id="carousel-example-generic">

				<!-- Indicators -->
				<ol class="carousel-indicators">
					<jstl:forEach var="i" begin="0" end="${pet.photos.size() - 1}">
						<jstl:if test="${i == 0}">
							<li data-target="#carousel-example-generic" data-slide-to="${i}"
								class="active"></li>
						</jstl:if>
						<jstl:if test="${i > 0}">
							<li data-target="#carousel-example-generic" data-slide-to="${i}"></li>
						</jstl:if>
					</jstl:forEach>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<jstl:set var="positionImage" value="0" />
					<jstl:forEach var="eachPhoto" items="${pet.photos}">
						<jstl:if test="${positionImage == 0}">
							<div class="item active ">
								<img class="center-block petPhoto"
									src="pet/displayPhoto.do?photoId=${eachPhoto.id}" />
								<acme:button classAux="btnCancel"
									href="pet/petOwner/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}"
									code="pet.selectAvatar" />
							</div>
						</jstl:if>
						<jstl:if test="${positionImage != 0}">
							<div class="item">
								<img class="center-block petPhoto"
									src="pet/displayPhoto.do?photoId=${eachPhoto.id}" />
								<acme:button classAux="btnCancel"
									href="pet/petOwner/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}"
									code="pet.selectAvatar" />
							</div>
						</jstl:if>
						<jstl:set var="positionImage" value="1" />
					</jstl:forEach>
				</div>

				<!-- Controls -->
				<a class="carousel-control left" href="#carousel-example-generic"
					data-slide="prev"> <span class="icon-prev"></span>
				</a> <a class="carousel-control right" href="#carousel-example-generic"
					data-slide="next"> <span class="icon-next"></span>
				</a>
			</div>

		</jstl:if>
		<br />

		<acme:button classAux="btnAccept" href="pet/petOwner/addPhoto.do?petId=${pet.id}"
			code="pet.add" />
		<br /> <br />
		<jstl:if test="${isOwner==true}">
			<acme:button classAux="btnCancel"
				href="pet/petOwner/edit.do?petId=${pet.id}"
				code="pet.edit" />
		</jstl:if>
		<input type="button" name="back" class="btnCancel"
			value="<spring:message code="pet.back" />" onClick="history.back(-1)" />
	</div>
</security:authorize>

<security:authorize access="hasRole('PETSITTER')">


<div class="col-md-4">
		<jstl:if test="${avatar!=null}">
			<img class="register-todoAncho petPhoto" alt="Your AVATAR"
				src="pet/displayPhoto.do?photoId=${avatar.id}" />
			<br />
		</jstl:if>
		<jstl:if test="${avatar==null}">
			<img class="register-todoAncho petPhoto" alt="Your AVATAR"
				src="images/pet-register.jpg">
			<br />
		</jstl:if>

		<h3>
			<jstl:out value="${pet.name}" />
		</h3>
		<jstl:out value="${pet.kind}" />
		
		<jstl:out value="${pet.breed}" />
		<br />
		<jstl:out value="${pet.description}" />
	</div>

	<div class="col-md-6-2">
	<jstl:if test="${numberOfPhotos!=0}">
		<b><spring:message code="pet.photos" /></b>:
<br />

		<div class="carousel slide" data-ride="carousel"
			id="carousel-example-generic">

			<!-- Indicators -->
			<ol class="carousel-indicators">
				<jstl:forEach var="i" begin="0" end="${pet.photos.size() - 1}">
					<jstl:if test="${i == 0}">
						<li data-target="#carousel-example-generic" data-slide-to="${i}"
							class="active"></li>
					</jstl:if>
					<jstl:if test="${i > 0}">
						<li data-target="#carousel-example-generic" data-slide-to="${i}"></li>
					</jstl:if>
				</jstl:forEach>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<jstl:set var="positionImage" value="0" />
				<jstl:forEach var="eachPhoto" items="${pet.photos}">
					<jstl:if test="${positionImage == 0}">
						<div class="item active">
							<img src="pet/displayPhoto.do?photoId=${eachPhoto.id}" />
							<acme:button classAux="btnCancel"
								href="pet/petSitter/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}"
								code="pet.selectAvatar" />
						</div>
					</jstl:if>
					<jstl:if test="${positionImage != 0}">
						<div class="item">
							<img src="pet/displayPhoto.do?photoId=${eachPhoto.id}" />
							<acme:button classAux="btnCancelt"
								href="pet/petSitter/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}"
								code="pet.selectAvatar" />
						</div>
					</jstl:if>
					<jstl:set var="positionImage" value="1" />
				</jstl:forEach>
			</div>

			<!-- Controls -->
			<a class="carousel-control left" href="#carousel-example-generic"
				data-slide="prev"> <span class="icon-prev"></span>
			</a> <a class="carousel-control right" href="#carousel-example-generic"
				data-slide="next"> <span class="icon-next"></span>
			</a>
		</div>
	</jstl:if>
	<br />

	<acme:button classAux="btnAccept" href="pet/petSitter/addPhoto.do?petId=${pet.id}"
		code="pet.add" />
	<br />

	<br />
	<input type="button" name="back" class="btnCancel"
		value="<spring:message code="pet.back" />" onClick="history.back(-1)" />
		</div>
</security:authorize>



