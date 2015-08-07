
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<spring url value="/connect/facebook" var="urlConnect" />
<div id="facebookConnect">
	<h3>Connect to Facebook</h3>

	<form action="${urlConnect}" method="POST">
	   <input type="hidden" name="scope" value="publish_actions" />
		<div class="formInfo">
			<p>Konektujte se na Facebook.</p>
		</div>
		<p>
			<button type="submit">Facebook</button>
		</p>
	</form>
</div>
