<%@page import="java.util.Formatter"%>
<%@page import="my.board.BoardDTO2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="boardDAO2" class="my.board.BoardBean2"/>
<jsp:setProperty property="pool" name="boardDAO2" value="<%= pool %>"/>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDTO2 article = boardDAO2.select(num).get(0);
	if(!article.getWriter().equals(name)) {
		article.setReadCount(article.getReadCount() + 1);
		boardDAO2.update(article);
	}
	Formatter formatter = new Formatter();
	
	String saveDirectory = config.getServletContext().getRealPath("/board2/file");
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
	<tr>
		<th>첨부파일</th>
		<td colspan="3"><a href="<%= saveDirectory %>/<%= article.getFileName() %>"><%= article.getFileName() %></a>&nbsp;
			(<%= formatter.format("%.3f", article.getFileSize() / 1024.0) %>MB)</td>
	</tr>
</table>
<table style="width: 800px">
	<tr>
		<td align="right">
			<form name="f" method="post">
				<input type="hidden" name="num" value="<%= article.getNum() %>">
				<input type="hidden" name="passwd" value="<%= article.getPasswd() %>">
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