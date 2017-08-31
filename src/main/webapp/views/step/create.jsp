<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('MANAGER')">


<form:form action="${url}" modelAttribute="step">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="advices" />


	    <div class="form-group" style="width: 20%;"> 
	    
	    <label> <spring:message code="step.videoTutorial"/> </label><br />
		<input class="form-control" value="${step.videoTutorial}" type="text" name="videoTutorial"/>
		<form:errors cssClass="error" path="videoTutorial" /> <br />
		
		<label> <spring:message code="step.description"/> </label><br />
		<textarea class="form-control" name="description"> ${step.description}</textarea>
		<form:errors cssClass="error" path="description" /> <br />
	  
		</div>
		
		<spring:message code="step.save" var="gymSaveHeader"/>
		<spring:message code="step.cancel" var="gymCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${gymSaveHeader}" />
		<input onclick="window.history.back()" class="btn btn-warning" type="button" name="cancel" value="${gymCancelHeader}"/>
		
		
	</form:form>

</security:authorize>