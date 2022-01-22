<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>스프링으로 보는 홈페이지</h1>
	<ul>
		<li><a href="start.mem">회원관리프로그램</a></li>
		<li><a href="start.board">게시판</a></li>
		<li><a href="list.board2">게시판2</a></li>
	</ul>
</body>
</html>

<!--
테이블 생성문

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
 -->