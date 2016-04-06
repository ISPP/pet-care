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
						<li><a href="complaint/administrator/listWithoutAdmin.do"><spring:message code="master.page.complaint.listComplaintWithoutAdmin" /></a></li>	
						<li><a href="complaint/administrator/listToSolve.do"><spring:message code="master.page.complaint.listComplaintToSolve" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv" href="supplier/administrator/list.do"><spring:message code="master.page.supplier.list"/></a>
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
			
			
			
				<li><a class="fNiv"><spring:message	code="master.page.booking" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						
						<li><a href="booking/petOwner/listBookingCanCancelPetOwner.do"><spring:message code="master.page.booking.cancel" /></a></li>
						<li><a href="booking/petOwner/list.do"><spring:message code="master.page.booking.list" /></a></li>
						<li><a href="petSitter/listToBook.do"><spring:message code="master.page.booking.bookpetSitter" /></a></li>
					
				</ul>
			</li>	
			
			
			
			
			<li><a class="fNiv" href="petOwner/petOwner/displayOwn.do"><spring:message code="master.page.myProfile" /></a></li>				
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
		
					
					
			<li><a class="fNiv"><spring:message	code="master.page.booking" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						<li><a href="booking/supplier/listBookingAccepted.do"><spring:message code="master.page.booking.listBookingAccepted" /></a></li>
					<li><a href="booking/supplier/listPendingSupplier.do"><spring:message code="master.page.booking.listPendingSupplier" /></a></li>
						
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
			<li><a class="fNiv"><spring:message	code="master.page.booking" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						
						
						
						
						<li><a href="booking/supplier/listBookingAccepted.do"><spring:message code="master.page.booking.listBookingAccepted" /></a></li>
						<li><a href="booking/supplier/listPendingSupplier.do"><spring:message code="master.page.booking.listPendingSupplier" /></a></li>
						
				</ul>
			</li>
			
			
								
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.petSitter.invite"  /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						<li><a href="petSitter/invite.do"><spring:message code="master.page.petSitter.inviteText" /></a></li>
				</ul>
			</li>		
			<li><a class="fNiv"><spring:message code="master.page.petSitter.review"/></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="review/petSitter/list.do"><spring:message code="master.page.petsitter.review.list"/></a></li>
				</ul>
			</li>
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
			<li><a class="fNiv"><spring:message	code="master.page.booking" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						<li><a href="booking/supplier/listBookingAccepted.do"><spring:message code="master.page.booking.listBookingAccepted" /></a></li>
					<li><a href="booking/supplier/listPendingSupplier.do"><spring:message code="master.page.booking.listPendingSupplier" /></a></li>
						
				</ul>
			</li>
			
							
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="search/searchSitters.do"><spring:message code="master.page.searchSitters" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="petOwner/create.do"><spring:message code="master.page.register.petOwner" /></a></li>
				
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
										
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

