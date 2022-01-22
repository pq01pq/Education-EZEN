<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	String saveDirectory = config.getServletContext().getRealPath("/board2/file");
	MultipartRequest mr = new MultipartRequest(request, saveDirectory, 10*1024*1024, "utf-8");
	boardDAO2.insert(mr);
	response.sendRedirect("list.jsp");
%>
</head>
<body>

</body>
</html>