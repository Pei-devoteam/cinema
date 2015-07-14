
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="movies/photo" var="urlPhoto" />
<spring:message code="movies.title" var="messageTitle" />
<spring:message code="movies.available" var="messageAvailable" />

<div id="moviesList">
	<h2>${messageAvailable}</h2>
	<c:forEach var="movie" items="${movies}">
		<table>
			<tr>
				<td rowspan="3"><a class="popup-trailer"
					href="${movie.trailerUrl}"><img src="${urlPhoto}/${movie.id}"
						height="150" width="100" /></a></td>
				<td class="title">${movie.name}</td>
			</tr>
			<tr>
				<td>${movie.description}</td>
			</tr>
			<tr>
				<td class="cast"><c:forEach var="actor" items="${movie.cast}">
							${actor.name}&nbsp;|&nbsp;
						</c:forEach></td>
			</tr>
		</table>
		<br />
	</c:forEach>
</div>
