<%--
 * footer.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Footer -->
<footer class=" footer text-center">
<jstl:if test="${index==true}">
  <spring:message var="toTop" code="master.toTop"/>
  <a class="up-arrow" href="#toptop" title="${toTop}">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a><br><br>
</jstl:if>
</footer>
