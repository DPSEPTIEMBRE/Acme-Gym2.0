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




<spring:message code="step.videoTutorial" var="videoHeader" />
<spring:message code="step.description" var="descriptionHeader" />
<spring:message code="step.advices" var="adviceHeader" />
<spring:message code="step.advicesTitle" var="adviceTitle" />


<display:table name="steps" id="row" requestURI="step/list.do" pagesize="8" class="table table-over">
	<display:column property="description" title="${descriptionHeader}" sortable ="false" />
	<display:column title="${adviceHeader}" > 
	
	<a href="advice/listByStep.do?q=${row.id}"> <jstl:out value="${adviceTitle}" /></a>
	
	</display:column>
	<display:column title="${videoHeader}">
	
	<input type="text" id="video" value="${row.videoTutorial}"
	style="display: none">
<br>

<div id="player"></div>

<script>
	// 2. This code loads the IFrame Player API code asynchronously.
	var tag = document.createElement('script');

	tag.src = "https://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

	// 3. This function creates an <iframe> (and YouTube player)
	//    after the API code downloads.
	var player;

	var video = document.getElementById("video").value;
	var filter = video.replace("https://www.youtube.com/watch?v=", "");

	function onYouTubeIframeAPIReady() {
		player = new YT.Player('player', {
			height : '360',
			width : '640',
			videoId : filter,
			events : {
				'onReady' : onPlayerReady
			}
		});
	}

	// 4. The API will call this function when the video player is ready.
	function onPlayerReady(event) {
		event.target.playVideo();
	}

	// 5. The API calls this function when the player's state changes.
	//    The function indicates that when playing a video (state=1),
	//    the player should play for six seconds and then stop.
	var done = false;
	function onPlayerStateChange(event) {
		if (event.data == YT.PlayerState.PLAYING && !done) {
			setTimeout(stopVideo, 12000);
			done = true;
		}
	}
	function stopVideo() {
		player.stopVideo();
	}
</script>
	
	
	
	</display:column>
</display:table>



