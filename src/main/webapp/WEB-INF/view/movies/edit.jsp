
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:message code="movies.edit.title" arguments="${movie.id}" var="titleEdit"/>
<spring:message code="movies.new.title" arguments="${movie.id}" var="titleNew"/>
<spring:eval expression="movie.id == 0 ? titleNew : titleEdit" var="title"/>

<spring:message code="movies.name" var="labelName" />
<spring:message code="movies.releaseDate" var="labelDate" />
<spring:message code="movies.description" var="labelDescription" />
<spring:message code="movies.trailer" var="labelTrailer" />
<spring:message code="movies.image" var="labelImage" />
<spring:message code="form.save" var="labelSave" />
<spring:message code="form.reset" var="labelReset" />
<spring:message code="form.ws" var="labelWS" />

<spring:url value="/movies/photo" var="urlPhoto" />
<spring:url value="/movies/getinfo" var="urlGetInfo" />

<script type="text/javascript">
	function downloadInformations(){
		var imdbId = prompt("Imdb ID:","tt1631867");
		var params = 'imdbId='+imdbId;
		$.ajax({
			url: "${urlGetInfo}",
			data:params,
			success: function(data){
				console.dir(data);
				if (data.Title != null){
					//alert(data.Title);
					$('#name').val(data.Title);
					$('#description').val(data.Plot);
					$('#poster').attr('src',data.Poster);
					$('#fileUrl').val(data.Poster);
					
					$('#trailerUrl').val('http://www.youtube.com');
					$('#releaseDate').val('01.01.1970');
					
					var date = new Date(data.Released);
					$('#releaseDate').val(date.getDate() + "." + date.getMonth() + "." + date.getFullYear());
					
				} 
			}
		});
	}
</script>

<div id="movieEdit">
	<h2>${title}</h2>
	<form:form modelAttribute="movie" id="editMovieForm" method="post" enctype="multipart/form-data">

		<c:if test="${not empty message}">
			<div id="message">${message}</div>
		</c:if>

		<table>
			<tr>
				<td><form:label path="name">${labelName}</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
				<td rowspan="5">
					<img id="poster" src="${urlPhoto}/${movie.id}"	height="300" width="200" />
				</td>
			</tr>

			<tr>
				<td><form:label path="releaseDate">${labelDate}</form:label></td>
				<td><form:input path="releaseDate" /></td>
				<td><form:errors path="releaseDate" cssClass="error" />
				<td>
			</tr>

			<tr>
				<td><form:label path="description">${labelDescription}</form:label></td>
				<td><form:textarea path="description" rows="10" cols="50"/></td>
				<td><form:errors path="description" cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:label path="trailerUrl">${labelTrailer}</form:label></td>
				<td><form:input path="trailerUrl" /></td>
				<td><form:errors path="trailerUrl" cssClass="error" /></td>
			</tr>
			 
			<tr>
				<td><form:label path="image" />${labelImage}</td>
				<td><input name="file" type="file"/>
					<input id="fileUrl" name="fileUrl" type="hidden" />
				</td>
				<td><form:errors path="image" cssClass="error" /></td>
			</tr>
			
			<form:hidden path="version" />
		</table>
		
		<button type="submit">${labelSave}</button>
		<button type="reset">${labelReset}</button>
		<button type="button" onclick="downloadInformations()">${labelWS}</button>
	</form:form>
</div>
