<%@page import="my.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="boardDAO" class="my.board.BoardBean"/>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDTO article = boardDAO.select(num).get(0);
	if(!article.getWriter().equals(name)) {
		article.setReadCount(article.getReadCount() + 1);
		boardDAO.update(article);
	}
%>
</head>
<body>
<table border="1" style="width: 800px">
	<tr>
		<th>글번호</th><td><%= article.getNum() %></td>
		<th>조회수</th><td><%= article.getReadCount() %></td>
	</tr>
	<tr>
		<th>작성자</th><td><%= article.getWriter() %></td>
		<th>작성일</th><td><%= article.getRegDate() %></td>
	</tr>
	<tr>
		<th>제목</th>
		<td colspan="3"><%= article.getSubject() %></td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3"><%= article.getContent() %></td>
	</tr>
</table>
<table style="width: 800px">
	<tr>
		<td align="right">
			<form name="f" method="post">
				<input type="hidden" name="num" value="<%= article.getNum() %>">
				<input type="hidden" name="passwd" value="<%= article.getPasswd() %>">
				<input type="submit"
					formaction="writeForm.jsp?reGroup=<%= article.getReGroup() %>&reStep=<%= article.getReStep() + 1 %>&reLevel=<%= article.getReLevel() + 1 %>"
					value="답글">
				<input type="submit" formaction="checkPasswd.jsp?type=update" value="수정">
				<input type="submit" formaction="checkPasswd.jsp?type=delete" value="삭제">
				<input type="button" onclick="location.href='list.jsp'" value="목록">
			</form>
		</td>
	</tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>