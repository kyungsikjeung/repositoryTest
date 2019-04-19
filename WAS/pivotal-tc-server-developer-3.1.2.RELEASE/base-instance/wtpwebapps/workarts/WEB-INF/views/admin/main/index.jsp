<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@
	include file="/WEB-INF/layouts/biz/include.jsp" %>

관리자 메인화면 <a href="${contextPath}/index.do">프론트메인</a>
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

<a href="${contextPath}/admin/index2.do">index2.do (ROLE_XXXXXX권한 필요 => 403 오류 테스트)</a><br/>


<sec:authorize access="isAuthenticated()">
	<a href="${contextPath}/logout">로그아웃</a><br/>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
	<a href="${contextPath}/login">로그인</a><br/>
</sec:authorize>