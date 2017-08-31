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


<form:form action="curricula/trainer/saveCreate.do" modelAttribute="curricula">


		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="socialIdentitys" />

	    <div class="form-group" style="width: 20%;"> 
	    
	    <label> <spring:message code="curricula.nameTrainer"/> </label><br />
		<input class="form-control" value="${curricula.nameTrainer}" type="text" name="nameTrainer"/>
		<form:errors cssClass="error" path="nameTrainer" />
		
		<label> <spring:message code="curricula.specialities"/> </label><br />
		<textarea class="form-control" name="specialities">${curricula.specialities}</textarea>
		<form:errors cssClass="error" path="specialities" /> <br />
		
		<label> <spring:message code="curricula.education"/> </label><br />
		<textarea class="form-control" name="education">${curricula.education}</textarea>
		<form:errors cssClass="error" path="education" /> <br />
		
		<label> <spring:message code="curricula.workExperience"/> </label><br />
		<textarea class="form-control" name="workExperience">${curricula.workExperience}</textarea>
		<form:errors cssClass="error" path="workExperience" /> <br />

		</div>
		
		<spring:message code="curricula.save" var="actorSaveHeader"/>
		<spring:message code="curricula.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.history.back()" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>
		
		
	</form:form>
