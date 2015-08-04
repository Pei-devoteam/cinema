<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="header">
	<spring:url value="/j_spring_security_check" var="loginUrl" />
	<spring:url value="/j_spring_security_logout" var="logoutUrl" />
	<spring:url value="/users/register" var="urlRegister" />
	<spring:message var="labelUsername" code="user.username" />
	<spring:message var="labelPassword" code="user.password" />
	<spring:message var="labelLogin" code="user.login" />
	<spring:message var="labelLogout" code="user.logout" />
	<spring:message var="labelRegister" code="user.register" />
	<spring:message var="messageWelcome" code="user.welcome" />

	<spring:url value="/resources/images/bosnia32.png" var="imageBosnia" />
	<spring:url value="/resources/images/italia32.png" var="imageItalia" />
	<spring:url value="?lang=bs" var="urlBos" />
	<spring:url value="?lang=it" var="urlIta" />

	<security:authorize access="isAnonymous()">
		<div id="login">
			<form name="loginForm" action="${loginUrl}" method="post">
				<table>
					<tr>
						<td>${labelUsername}:</td>
						<td><input type="text" name="j_username" /></td>
						<td>${labelPassword}</td>
						<td><input type="password" name="j_password" /></td>
						<td><input name="submit" type="submit" value="${labelLogin}" /></td>
						<td><a href="${urlRegister}">${labelRegister}</a></td>
						<td class="languages"><a href="${urlBos}"> <img alt="bos"
								src="${imageBosnia}" />
						</a> <a href="${urlIta}"> <img alt="bos" src="${imageItalia}" />
						</a></td>
					</tr>
				</table>
			</form>
		</div>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<table>
			<tr>
				<td>${messageWelcome}&nbsp;<security:authentication
						property="principal.username" /></td>
				<td><a href="${logoutUrl}">${labelLogout}</a></td>
				<td class="languages"><a href="${urlBos}"> <img alt="bos"
						src="${imageBosnia}" />
				</a> <a href="${urlIta}"> <img alt="bos" src="${imageItalia}" />
				</a></td>
			</tr>
		</table>


	</security:authorize>

</div>