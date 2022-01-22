<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- memberAll.jsp -->
<%@ include file="top.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="style.css">
<body>
<div align="center">
	<hr color="green" width="300">
<c:choose>
	<c:when test="${mode == 'all'}">
		<h2>회 원 목 록 보 기</h2>
	</c:when>
	<c:when test="${mode == 'find'}">
		<h2>회 원 찾 아 보 기</h2>
		<form name="f" action="search.mem?mode=find" method="post">
			<select name="column" >
				<option value="name">회원명</option>
				<option value="id" >아이디</option>
			</select>
			<input type="text" name="key">
			<input type="submit" value="찾기">
		</form>
	</c:when>
</c:choose>
	<hr color="green" width="300">
	<table width="100%" class="outline">
		<tr>
			<th class="m3">번호</th>
			<th class="m3">이름</th>
			<th class="m3">아이디</th>
			<th class="m3">이메일</th>
			<th class="m3">전화번호</th>
			<th class="m3">가입일</th>
			<th class="m3">수정 | 삭제</th>
		</tr>
<c:choose>
	<c:when test="${members == null || members.size() == 0}">
		<tr>
			<td>회원 없음</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach var="member" items="${members}">
			<tr>
				<td>${member.no}</td>
				<td>${member.name}</td>
				<td>${member.id}</td>
				<td>${member.email}</td>
				<td>${member.allHp}</td>
				<td>${member.joindate}</td>
				<td>
					<a href="update_input.mem?no=${member.no}">수정</a> |&nbsp;
					<a href="delete.mem?no=${member.no}">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
	</table>
</div>

</body>
<%@ include file="bottom.jsp" %>