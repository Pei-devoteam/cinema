<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<spring:message code="app.title" var="appTitle" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/styles/main.css" rel="stylesheet" />
<script src="resources/scripts/jquery-1.11.3.min.js"></script>
<link href="resources/scripts/magnific-popup.css" rel="stylesheet" />
<script src="resources/scripts/jquery.magnific-popup.min.js"></script>
<script src="resources/scripts/cinema.js"></script>
<title>${appTitle}</title>
</head>
<body>
	<div id="pageWrapper">
		<div id="headerWrapper">
			<tiles:insertAttribute name="header" ignore="true" />
		</div>
		<div id="contentWrapper">
			<tiles:insertAttribute name="menu" ignore="true" />
			<div id="main">
				<tiles:insertAttribute name="body" />
				<tiles:insertAttribute name="footer" ignore="true" />
			</div>
		</div>
	</div>
</body>
</html>