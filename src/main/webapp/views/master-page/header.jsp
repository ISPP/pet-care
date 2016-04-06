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

<div>
	<img src="images/logo.png" alt="Sample Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.complaint" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						<li><a href="complaint/administrator/listComplaintAdminNotSolved.do"><spring:message code="master.page.complaint.listComplaintAdminNotSolved" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('PETOWNER')">
			<li><a class="fNiv"><spring:message	code="master.page.complaint" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						
						<li><a href="complaint/customer/create.do"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerId.do"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerIdNotSolved.do"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
				</ul>

			</li>	
			<li>
				<a class="fNiv">
					<spring:message	code="master.page.pets" />
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="pet/petOwner/list.do"><spring:message code="master.page.list" /></a></li>				
					<li><a href="pet/petOwner/create.do"><spring:message code="master.page.create" /></a></li>	
				</ul>
			</li>
			<li>				
				
		

			</li>					
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>

		</security:authorize>
		
		<security:authorize access="hasRole('PETSHIPPER')">
			<li><a class="fNiv"><spring:message	code="master.page.complaint" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						
						<li><a href="complaint/customer/create.do"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerId.do"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerIdNotSolved.do"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
				</ul>
			</li>					
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('PETSITTER')">
			<li><a class="fNiv"><spring:message	code="master.page.complaint" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						
						<li><a href="complaint/customer/create.do"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerId.do"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerIdNotSolved.do"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
				</ul>
			</li>	
			<li>
				<a class="fNiv">
					<spring:message	code="master.page.pets" />
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="pet/petSitter/list.do"><spring:message code="master.page.list" /></a></li>				
					<li><a href="pet/petSitter/create.do"><spring:message code="master.page.create" /></a></li>	
				</ul>
			</li>
			<li>				
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>
		</security:authorize>
		
		
		<security:authorize access="hasRole('COMPANY')">
			<li><a class="fNiv"><spring:message	code="master.page.complaint" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						
						<li><a href="complaint/customer/create.do"><spring:message code="master.page.complaint.create" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerId.do"><spring:message code="complaint.customer.listComplaintCustomerId.do" /></a></li>
						<li><a href="complaint/customer/listComplaintCustomerIdNotSolved.do"><spring:message code="complaint.customer.listComplaintCustomerIdNotSolved.do" /></a></li>
				</ul>
			</li>					
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
 
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="petOwner/create.do"><spring:message code="master.page.register.petOwner" /></a></li>
					<li><a href="petSitter/create.do"><spring:message code="master.page.register.petSitter" /></a></li>					
				</ul>
			</li>


			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>


		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
										
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>