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
<a href="https://www.facebook.com/petcaresepp" target="_blank"><img alt="<spring:message code="master.social.facebook"/>" height="32" src="http://2.bp.blogspot.com/-q_Tm1PpPfHo/UiXnJo5l-VI/AAAAAAAABzU/MKdrVYZjF0c/s1600/face.png" title="<spring:message code="master.social.facebook"/>" width="32" /></a>
<a href="https://twitter.com/PetPetcaresepp" target="_blank"><img alt="<spring:message code="master.social.twitter"/>" height="32" src="http://3.bp.blogspot.com/-wlwaJJG-eOY/UiXnHS2jLsI/AAAAAAAAByQ/I2tLyZDLNL4/s1600/Twitter+NEW.png" title="<spring:message code="master.social.twitter"/>" width="32" /></a>
<a href="https://www.instagram.com/petcaresepp/" target="_blank"><img alt="<spring:message code="master.social.instagram"/>" height="32" src="http://2.bp.blogspot.com/-kQop92g4NsM/UidPJ06ER1I/AAAAAAAACAA/0mj0jK5hhXM/s1600/instagram2.png" title="<spring:message code="master.social.instagram"/>" width="32" /></a>
<a href="https://www.youtube.com/channel/UCoUeCo0hHJzC5_pdFpT1fNQ" target="_blank"><img alt="<spring:message code="master.social.youtube"/>" height="32" src="http://1.bp.blogspot.com/-VXR1bXUiF6c/UidLJJRexgI/AAAAAAAAB_I/qymCft9JexE/s1600/youtube2.png" title="<spring:message code="master.social.youtube"/>" width="32" /></a>
<br>

<font color="#FFA500"><spring:message code="master.contact"/>: petcaresepp@gmail.com</font>
</footer>
