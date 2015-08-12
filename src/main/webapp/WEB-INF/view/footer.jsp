<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url value="/movies" var="urlHome" />
<spring:message code="app.home" var="labelHome"/>

<spring:url value="/movies/catalog" var="urlCatalog" />
<spring:message code="movies.catalog" var="labelCatalog"/>
<spring:url value="?site_preference=mobile" var="urlMobile" />
<spring:message code="page.mobile" var="labelPageMobile"/>

<div id="footer">
	<a href="${urlHome}">${labelHome}</a>&nbsp;
	<a href="${urlCatalog}">${labelCatalog}</a>&nbsp;
	<a href="${urlMobile}" data-ajax="false">${labelPageMobile}</a>&nbsp;
</div>