
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<spring:url value="movies/photo" var="urlPhoto" />
<spring:message code="movies.title" var="messageTitle" />
<spring:message code="movies.available" var="messageAvailable" />

<script type="text/javascript">
	function rateMovie(movieId,userId,rating){
		var params = 'movieId='+movieId+'&userId='+userId+'&rating='+rating;
		$.ajax({
			url: 'movies/rating',
			data:params,
			success: function(data){
				
			}
		});
	}
</script>

<div id="moviesList">
	<p>${message}</p>
	<h2>${messageAvailable}</h2>
	<c:forEach var="movie" items="${movies}">
		<table>
			<tr>
				<td rowspan="4">
				<a class="popup-trailer" 
					<security:authorize access="isAuthenticated()">
						href="${movie.trailerUrl}"
					</security:authorize>
					>
					<img src="${urlPhoto}/${movie.id}"	height="150" width="100" />
				</a></td>
				<td class="title">${movie.name}</td>
				<td align="right">
					<security:authorize access="isAuthenticated()">
						<input type="hidden" class="rating" data-size="xs" data-step="1"
							data-glyphicon="false" data-show-caption="false" value="${ratings[movie.id]}"
							onchange="rateMovie(${movie.id},'${username}',this.value)"/>
					</security:authorize>
				</td>
			</tr>
			<tr>
				<td colspan="2">${movie.description}</td>
			</tr>
			<tr>
				<td class="cast" colspan="2">
					<c:forEach var="actor" items="${movie.cast}">
							${actor.name}&nbsp;|&nbsp;
					</c:forEach>
				</td>
			</tr>
		</table>
		<br />
	</c:forEach>
</div>
