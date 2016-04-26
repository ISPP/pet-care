<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	 <form:form action="${requestURI}" modelAttribute="booking">
<div class="widget-container boxed">
    <h3 class="widget-title"><spring:message code="payBooking" /></h3>
    <div class="inner" style= "min-height: 250px;">
   <script async="async" src="https://www.paypalobjects.com/js/external/paypal-button.min.js?merchant=${booking.supplier.email}"
	    data-button="buynow" 
	    data-name='<spring:message code = "paypal.description"></spring:message>' 
	    data-quantity="1" 
	    data-amount="${booking.price}" 
	    data-currency="EUR" 
	    data-callback="http://127.0.0.1/PetCare/" 
	    data-env="sandbox"
	></script>
	
	
	
	
	
	
	
	
	<br><br>
	<h4>

	</h4>
    </div>
</div>

	</form:form>

	
	
  
  
