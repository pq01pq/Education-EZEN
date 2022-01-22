<%@page import="java.util.List"%>
<%@page import="my.board.BoardDTO"%>
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
	int parentNum, reGroup, reStep, reLevel;
	try {
		// 답글
		parentNum = Integer.parseInt(request.getParameter("parent"));
		reGroup = Integer.parseInt(request.getParameter("parentReGroup"));
		reStep = Integer.parseInt(request.getParameter("parentReStep")) + 1;
		reLevel = Integer.parseInt(request.getParameter("parentReLevel")) + 1;
	} catch(NullPointerException | NumberFormatException e) {
		// 새글
		parentNum = -1;
		reGroup = boardDAO.maxGroup() + 1;
		reStep = 0;
		reLevel = 0;
	}
	article.setParentNum(parentNum);
	article.setReGroup(reGroup);
	article.setReStep(reStep);
	article.setReLevel(reLevel);
	
	boardDAO.insert(article);
	
	response.sendRedirect("list.jsp");
%>
</head>
<body>

</body>
</html>