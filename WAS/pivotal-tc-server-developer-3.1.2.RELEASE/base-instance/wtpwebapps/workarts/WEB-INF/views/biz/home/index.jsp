<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@
	include file="/WEB-INF/layouts/biz/include.jsp" %>

프론트 메인화면 <a href="${contextPath}/admin/index.do">관리자메인</a>
<hr/>

serverTime: <c:out value="${serverTime}"></c:out><br/>
serverTime: ${serverTime}<br/>

<ul>
	<li>me: ${me}</li>
	<sec:authorize access="isAuthenticated()">
	<li>me.userId: ${me.userId}</li>
	<li>me.userRealName: ${me.userRealName}</li>
	</sec:authorize>
</ul>
<ul>
	<li>principal: <sec:authentication property="principal"/></li>
</ul>

<sec:authorize access="isAuthenticated()">
	<a href="${contextPath}/logout">로그아웃</a><br/>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
	<a href="${contextPath}/login">로그인</a><br/>
</sec:authorize>