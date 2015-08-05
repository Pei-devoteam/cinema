
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<spring:url value="/movies/photo" var="urlPhoto" />
<spring:message code="movies.title" var="messageTitle" />
<spring:message code="movies.available" var="messageAvailable" />

<spring:url value="/movies/edit" var="urlEdit" />
<spring:message code="movies.edit" var="labelEdit" />
<spring:url value="/movies/new" var="urlNew" />
<spring:message code="movies.new" var="labelNew" />

<spring:url value="/movies/page" var="urlPage" />
<spring:url value="/movies/rating" var="urlRating" />

<script type="text/javascript">
	function rateMovie(movieId,userId,rating){
		var params = 'movieId='+movieId+'&userId='+userId+'&rating='+rating;
		$.ajax({
			url: '${urlRating}',
			data:params,
			success: function(data){
				
			}
		});
	}
</script>

<div id="moviesList">

	<p>${message}</p>
	<h2>${messageAvailable}</h2>

	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="${urlNew}">${labelNew}</a>
	</security:authorize>

	<c:forEach var="movie" items="${movies}">
		<table>
			<tr>
				<td rowspan="4"><a class="popup-trailer"
					<security:authorize access="isAuthenticated()">
						href="${movie.trailerUrl}"
					</security:authorize>>
						<img src="${urlPhoto}/${movie.id}" height="150" width="100" />
				</a></td>
				<td class="title">${movie.id}.${movie.name}</td>
				<td align="right"><security:authorize
						access="isAuthenticated()">
						<security:authorize access="hasRole('ROLE_USER')">
							<input type="hidden" class="rating" data-size="xs" data-step="1"
								data-glyphicon="false" data-show-caption="false"
								value="${ratings[movie.id]}"
								onchange="rateMovie(${movie.id},'${username}',this.value)" />
						</security:authorize>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<a href="${urlEdit}/${movie.id}">${labelEdit}</a>
						</security:authorize>
					</security:authorize></td>
			</tr>
			<tr>
				<td colspan="2">${movie.description}</td>
			</tr>
			<tr>
				<td class="cast" colspan="2"><c:forEach var="actor"
						items="${movie.cast}">
							${actor.name}&nbsp;|&nbsp;
					</c:forEach></td>
			</tr>
		</table>
		<br />
	</c:forEach>
</div>
<div id="pagesList">
	<c:forEach var="page" items="${pages}">
		<a href="${urlPage}/${page}">${page}-${page+pageSize-1}</a>&nbsp;
		</c:forEach>
</div>
