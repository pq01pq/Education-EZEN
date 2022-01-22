<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="boardDAO" class="my.board.BoardBean"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="article" class="my.board.BoardDTO"/>
<jsp:setProperty property="*" name="article"/>
<%
	boardDAO.update(article);
	response.sendRedirect("list.jsp");
%>
</head>
<body>

</body>
</html>