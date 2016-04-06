<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('PETOWNER')">

<jstl:if test="${avatar!=null}">
<b><spring:message code="pet.avatar"/></b>:
<br/>
		<img src="pet/displayPhoto.do?photoId=${avatar.id}" width="200px" height="200px"/>
		<br/>

</jstl:if>
<br/>

<br/>
<b><spring:message code="pet.name"/></b>: <jstl:out value="${pet.name}"/>
<br/>
<b><spring:message code="pet.description"/></b>: <jstl:out value="${pet.description}"></jstl:out>
<br/>
<b><spring:message code="pet.breed"/></b>: <jstl:out value="${pet.breed}"></jstl:out>
<br/>
<b><spring:message code="pet.kind"/></b>: <jstl:out value="${pet.kind}"></jstl:out>
<br/>
<br/>

<jstl:if test="${numberOfPhotos!=0}">
<b><spring:message code="pet.photos"/></b>:
<br/>

<div class="carousel slide" data-ride="carousel" id="carousel-example-generic">

	<!-- Indicators -->
    <ol class="carousel-indicators">
    	<jstl:forEach var="i" begin="0" end="${pet.photos.size() - 1}">
        	<jstl:if test="${i == 0}">
            	<li data-target="#carousel-example-generic" data-slide-to="${i}" class="active"></li>
            </jstl:if>
            <jstl:if test="${i > 0}">
            	<li data-target="#carousel-example-generic" data-slide-to="${i}"></li>
            </jstl:if>
         </jstl:forEach>
     </ol>

     <!-- Wrapper for slides -->
     <div class="carousel-inner">
     	<jstl:set var="positionImage" value="0"/>
        	<jstl:forEach var="eachPhoto" items="${pet.photos}">
            	<jstl:if test="${positionImage == 0}">
             		<div class="item active">
                    	<img src = "pet/displayPhoto.do?photoId=${eachPhoto.id}"/>
                    	<acme:button href="pet/petOwner/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}" code="pet.selectAvatar" />
                    </div>
              	</jstl:if>
              	<jstl:if test="${positionImage != 0}">
              		<div class="item">
                    	<img src = "pet/displayPhoto.do?photoId=${eachPhoto.id}"/>
                    	<acme:button href="pet/petOwner/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}" code="pet.selectAvatar" />
                    </div>
              	</jstl:if>
              	<jstl:set var="positionImage" value="1"/>
             </jstl:forEach>
      </div>

      <!-- Controls -->
      <a class="carousel-control left" href="#carousel-example-generic" data-slide="prev">
      	<span class="icon-prev"></span>
      </a>
      <a class="carousel-control right" href="#carousel-example-generic" data-slide="next">
      	<span class="icon-next"></span>
       </a>
</div>
</jstl:if>
<br/>
	
<acme:button href="pet/petOwner/addPhoto.do?petId=${pet.id}" code="pet.add" />
<br/>
<br/>
<input type="button" name="back" value="<spring:message code="pet.back" />"onClick="history.back(-1)" />
</security:authorize>

<security:authorize access="hasRole('PETSITTER')">

<jstl:if test="${avatar!=null}">
<b><spring:message code="pet.avatar"/></b>:
<br/>
		<img src="pet/displayPhoto.do?photoId=${avatar.id}" width="200px" height="200px"/>
		<br/>

</jstl:if>
<br/>

<br/>
<b><spring:message code="pet.name"/></b>: <jstl:out value="${pet.name}"/>
<br/>
<b><spring:message code="pet.description"/></b>: <jstl:out value="${pet.description}"></jstl:out>
<br/>
<b><spring:message code="pet.breed"/></b>: <jstl:out value="${pet.breed}"></jstl:out>
<br/>
<b><spring:message code="pet.kind"/></b>: <jstl:out value="${pet.kind}"></jstl:out>
<br/>
<br/>

<jstl:if test="${numberOfPhotos!=0}">
<b><spring:message code="pet.photos"/></b>:
<br/>

<div class="carousel slide" data-ride="carousel" id="carousel-example-generic">

	<!-- Indicators -->
    <ol class="carousel-indicators">
    	<jstl:forEach var="i" begin="0" end="${pet.photos.size() - 1}">
        	<jstl:if test="${i == 0}">
            	<li data-target="#carousel-example-generic" data-slide-to="${i}" class="active"></li>
            </jstl:if>
            <jstl:if test="${i > 0}">
            	<li data-target="#carousel-example-generic" data-slide-to="${i}"></li>
            </jstl:if>
         </jstl:forEach>
     </ol>

     <!-- Wrapper for slides -->
     <div class="carousel-inner">
     	<jstl:set var="positionImage" value="0"/>
        	<jstl:forEach var="eachPhoto" items="${pet.photos}">
            	<jstl:if test="${positionImage == 0}">
             		<div class="item active">
                    	<img src = "pet/displayPhoto.do?photoId=${eachPhoto.id}"/>
                    	<acme:button href="pet/petSitter/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}" code="pet.selectAvatar" />
                    </div>
              	</jstl:if>
              	<jstl:if test="${positionImage != 0}">
              		<div class="item">
                    	<img src = "pet/displayPhoto.do?photoId=${eachPhoto.id}"/>
                    	<acme:button href="pet/petSitter/selectAvatar.do?petId=${pet.id}&photoId=${eachPhoto.id}" code="pet.selectAvatar" />
                    </div>
              	</jstl:if>
              	<jstl:set var="positionImage" value="1"/>
             </jstl:forEach>
      </div>

      <!-- Controls -->
      <a class="carousel-control left" href="#carousel-example-generic" data-slide="prev">
      	<span class="icon-prev"></span>
      </a>
      <a class="carousel-control right" href="#carousel-example-generic" data-slide="next">
      	<span class="icon-next"></span>
       </a>
</div>
</jstl:if>
<br/>
	
<acme:button href="pet/petSitter/addPhoto.do?petId=${pet.id}" code="pet.add" />
<br/>

<br/>
<input type="button" name="back" value="<spring:message code="pet.back" />"onClick="history.back(-1)" />
</security:authorize>



