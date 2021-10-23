<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:if test="${!empty userinfo}">
	<div class="m-3 text-right">
		<strong>${userinfo.name}</strong> (${userinfo.id})님 안녕하세요.
	</div>
	</c:if>
	<c:if test="${empty userinfo}">
		<a href="${pageContext.request.contextPath}/member/login">로그인</a>
		<a href="${pageContext.request.contextPath}/member/join">회원가입</a>
	</c:if>
