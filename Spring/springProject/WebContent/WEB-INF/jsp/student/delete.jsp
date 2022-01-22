<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>삭제</title>
</head>
<body>
	<form action="student_delete.do" method="post">
		학번 : <input type="text" name="id"><br>
		<input type="submit" value="확인">
		<input type="reset" value="취소">
	</form>
</body>
</html>
<%@ include file="bottom.jsp" %>