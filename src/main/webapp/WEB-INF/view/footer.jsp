<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/movies" var="urlHome" />
<spring:message code="app.home" var="labelHome"/>

<spring:url value="/movies/catalog" var="urlCatalog" />
<spring:message code="movies.catalog" var="labelCatalog"/>

<div id="footer">
	<a href="${urlHome}">${labelHome}</a>&nbsp;
	<a href="${urlCatalog}">${labelCatalog}</a>&nbsp;
</div>