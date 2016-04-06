<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="" onclick="jumpTo('./');"><spring:message	code="master.page.petcare" /></a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
		<security:authorize access="hasRole('ADMIN')">
			<li><a href="hola"><spring:message code="master.page.users" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a href="" data-toggle="modal" data-target="#loginForm"><spring:message code="master.page.login" /></a></li>
        	<li><a href="#users"><spring:message code="master.page.users" /></a></li>
        	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.language" /><span class="caret"></span></a>
          	<ul class="dropdown-menu">
            	<li> <a href="" onclick="jumpTo('./?language=en');"><img width="35" height="23" src="images/United-Kingdom.png">EN</a></li>
            	<li><a href="" onclick="jumpTo('./?language=esp');"><img width="35" height="23" src="images/Spain.png">ESP</a></li>
          	</ul>
        	</li>
		</security:authorize>
	
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>

</div>
</div>
</nav>





<script>

function jumpTo(url){
	window.location.replace(url);
}

</script>
