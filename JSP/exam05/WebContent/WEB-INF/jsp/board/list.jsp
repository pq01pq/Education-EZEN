<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table style="width: 800px">
	<tr><td align="right"><form action="write.board" onsubmit="${sessionScope.id != null}"><input type="submit" value="글쓰기"></form></td></tr>
</table>
<table border="1" style="width: 800px">
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th><th>IP</th></tr>
<c:forEach items="${articles}" var="article" varStatus="status">
	<tr>
		<td>${status.count}</td>
		<td>
		<c:if test="${article.reLevel > 0}">
			<img src="img/level.gif" width="${article.reLevel * 10}"><img src="img/re.gif">
		</c:if>
			<a href="context.board?num=${article.num}">${article.subject}</a>
		<c:if test="${article.readCount > 10}">
			<img src="img/hot.gif">
		</c:if>
		</td>
		<td>${article.writer}</td>
		<td>${article.regDate}</td>
		<td>${article.readCount}</td>
		<td>${article.ip}</td>
	</tr>
</c:forEach>
</table>
<form method="get" >
	<select name="column">
		<option value="">제목+내용</option>
		<option value="subject">제목</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>
	</select>
	<input type="text" name="key">
	<input type="submit" value="검색">
</form>
<c:if test="${startPageNum > 1}">
		<a>[&lt;&lt;]</a>&nbsp;
</c:if>
<c:forEach var="i" begin="${startPageNum}" end="${endPageNum}">
	<c:choose>
		<c:when test="${i == sessionScope.pageNum}">
			<a>[${i}]</a>
		</c:when>
		<c:otherwise>
			<a href="list.board?pageNum=${i}">[${i}]</a>
		</c:otherwise>
	</c:choose>
	<c:if test="${i < endPageNum}">
		&nbsp;
	</c:if>
</c:forEach>
<c:if test="${endPageNum < totalPagesNum}">
	<a>[&gt;&gt;]</a>
</c:if>
</body>
</html>
<%@ include file="bottom.jsp" %>