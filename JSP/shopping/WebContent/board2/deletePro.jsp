<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="boardDAO2" class="my.board.BoardBean2"/>
<jsp:setProperty property="pool" name="boardDAO2" value="<%= pool %>"/>
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String saveDirectory = config.getServletContext().getRealPath("/board2/file");
	String preFileName = boardDAO2.select(num).get(0).getFileName();
	boardDAO2.delete(num);
	File preFile = new File(saveDirectory, preFileName);
	preFile.delete();
	response.sendRedirect("list.jsp");
%>
</head>
<body>

</body>
</html>