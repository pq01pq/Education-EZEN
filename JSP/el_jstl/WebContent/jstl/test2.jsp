<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- test2.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL 예제 2</title>
</head>
<body>

<c:set var="country" value="${'korea'}" />
<c:if test="${country != null }">
국가명 : <c:out value="${country}" />

<br>

<c:choose>
	<c:when test="${country=='korea'}">
		<c:out value="${country}"/>의 겨울은 춥다.
	</c:when>
	<c:when test="${country=='canada'}">
		<c:out value="${country}"/>의 겨울은 매우 춥다.
	</c:when>
	<c:otherwise>
		그외의 나라는 알수가 없습니다.
	</c:otherwise>
</c:choose>

<br>

<c:set var="sum" value="0" />
<c:forEach var="k" begin="1" end="100">
<c:if test="${k%2==0}">
<c:set var="sum" value="${sum+k}" />
</c:if>
</c:forEach>
1~100까지의 짝수의 합 : ${sum}


</c:if>
</body>
</html>
