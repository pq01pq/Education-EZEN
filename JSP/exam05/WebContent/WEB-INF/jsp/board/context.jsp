<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="width: 800px">
	<tr>
		<th>글번호</th><td>${article.num}</td>
		<th>조회수</th><td>${article.readCount}</td>
	</tr>
	<tr>
		<th>작성자</th><td>${article.writer}</td>
		<th>작성일</th><td>${article.regDate}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td colspan="3">${article.subject}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3">${article.content}</td>
	</tr>
</table>
<table style="width: 800px">
	<tr>
		<td align="right">
			<form name="f" method="post">
				<input type="hidden" name="num" value="${article.num}">
				<input type="hidden" name="passwd" value="${article.passwd}">
				<input type="submit"
					formaction="write.board?reGroup=${article.reGroup}&reStep=${article.reStep + 1}&reLevel=${article.reLevel + 1}"
					value="답글">
				<input type="submit" formaction="check.board?type=update" value="수정">
				<input type="submit" formaction="check.board?type=delete" value="삭제">
				<input type="button" value="목록" onclick="location.href='list.board?pageNum=${sessionScope.pageNum}'">
			</form>
		</td>
	</tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>