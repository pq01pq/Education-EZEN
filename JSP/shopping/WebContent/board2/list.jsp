<%@page import="my.board.BoardDTO2"%>
<%@page import="java.util.List"%>
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
	int totalArticlesNum = boardDAO2.count();
	int numPerPage = 20;
	int totalPagesNum = (totalArticlesNum + numPerPage - 1) / numPerPage;
	if(totalPagesNum < 1){
		totalPagesNum = 1;
	}
	
	int pageNum = 1;
	String pageNumStr = request.getParameter("page_num");
	if(pageNumStr != null && !pageNumStr.trim().equals("")){
		pageNum = Integer.parseInt(pageNumStr);
	}
	if(pageNum < 1){
		pageNum = 1;
	} else if(pageNum > totalPagesNum){
		pageNum = totalPagesNum;
	}
	
	int startPageNum = pageNum - 5;
	int endPageNum = pageNum + 5;
	if(startPageNum < 1) {
		startPageNum = 1;
	}
	if(endPageNum > totalPagesNum) {
		endPageNum = totalPagesNum;
	}
	
	List<BoardDTO2> articles = boardDAO2.selectPage(pageNum, totalArticlesNum, numPerPage);
%>
</head>
<body>
<table style="width: 800px">
	<tr><td align="right"><form action="writeForm.jsp" onsubmit="<%= isLogin %>"><input type="submit" value="글쓰기"></form></td></tr>
</table>
<table border="1" style="width: 800px">
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th><th>IP</th></tr>
<%	for(BoardDTO2 article : articles){	%>
	<tr>
		<td><%= article.getNum() %></td>
		<td><a href="context.jsp?num=<%= article.getNum() %>"><%= article.getSubject() %></a></td>
		<td><%= article.getWriter() %></td>
		<td><%= article.getRegDate() %></td>
		<td><%= article.getReadCount() %></td>
		<td><%= article.getIp() %></td>
	</tr>
<%	}	%>
</table>
<form method="get">
	<select name="column">
		<option value="">제목+내용</option>
		<option value="subject">제목</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>
	</select>
	<input type="text" name="key">
	<input type="submit" value="검색">
</form>
<%	if(startPageNum > 1) {	%>
		<a href="list.jsp?page_num=<%= pageNum - 10 %>">[&lt;&lt;]</a>&nbsp;
<%	}	%>

<%	for(int i = startPageNum; i <= endPageNum; i++) {
		if(i == pageNum){	%>
			<a>[<%= i %>]</a>
<%		} else {	%>
			<a href="list.jsp?page_num=<%= i %>">[<%= i %>]</a>
<%		}
		
		if(i < endPageNum){	%>
			&nbsp;
<%		}
	}	
	
	if(endPageNum < totalPagesNum) {	%>
		<a href="list.jsp?page_num=<%= pageNum + 10 %>">[&gt;&gt;]</a>
<%	}	%>
</body>
</html>
<%@ include file="bottom.jsp" %>