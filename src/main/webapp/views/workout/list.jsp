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

<label><spring:message code="workout.search" /> </label>
<input type="text" id="textSearch" /> 
<button type="button" class="btn btn-primary" >  <spring:message code="activity.search" /></button>


<security:authorize access="isAnonymous()">

<acme:list list="${workouts}" requestURI="workout/list.do" hidden_fields="id,version"
entityUrl="{steps: step/listByWorkout.do,annotations: annotation/listByWorkout.do}" />

</security:authorize>


<security:authorize access="permitAll() && !hasRole('MANAGER') && !isAnonymous() and !hasRole('TRAINER')">

<acme:list list="${workouts}" requestURI="workout/list.do" hidden_fields="id,version"
entityUrl="{steps: step/listByWorkout.do,annotations: annotation/listByWorkout.do}" extraColumns="{annotationsc: annotation/create.do}" />

</security:authorize>


<security:authorize access="hasRole('MANAGER')">

<jstl:if test="${a==1}">

<acme:list list="${workouts}" requestURI="workout/list.do" hidden_fields="id,version"
entityUrl="{steps: step/listByWorkout.do,annotations: annotation/listByWorkout.do}" 
extraColumns="{addStep: step/create.do, annotationsc: annotation/create.do}"/>

</jstl:if>

<jstl:if test="${a==2}">

<acme:list list="${workouts}" requestURI="workout/list.do" hidden_fields="id,version"
entityUrl="{steps: step/listByWorkout.do,annotations: annotation/listByWorkout.do}" editUrl="workout/edit.do" deleteUrl="workout/delete.do"
extraColumns="{annotationsc: annotation/create.do}"/>

</jstl:if>


</security:authorize>

<security:authorize access="hasRole('TRAINER')">

<acme:list list="${workouts}" requestURI="workout/list.do" hidden_fields="id,version"
entityUrl="{steps: step/listByWorkout.do, annotations: annotation/listByWorkout.do}" extraColumns="{annotationsc: annotation/create.do}" />

</security:authorize>




<script>
$(document).ready(function(){
    $("button").click(function(){
        $.ajax({success: function(result){
        	var input, filter, table, tr, tdTitle,tdDescription,i;
        	input = document.getElementById("textSearch");
        	filter1 = input.value.toUpperCase();
        	table = document.getElementById("row");
        	tr = table.getElementsByTagName("tr");
        	for (i = 0; i < tr.length; i++) {
        		tdTitle = tr[i].getElementsByTagName("td")[0];
        		tdDescription = tr[i].getElementsByTagName("td")[1];
        		if (tdTitle || tdDescription ) {
        			if (tdTitle.innerHTML.toUpperCase().indexOf(filter1) > -1 || 
        				tdDescription.innerHTML.toUpperCase().indexOf(filter1) > -1) {
	          	        tr[i].style.display = "";
	          	      } else {
	          	        tr[i].style.display = "none";
	          	      }
        			
        			}
          	      
        	}
        }});
    });
});
</script>