<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학생 입력</h2>
	<form action="student_insert.do" method="post">
		학번 : <input type="text" name="id"><br>
		이름 : <input type="text" name="name"><br>
		학급 : <input type="text" name="cname"><br>
		<input type="submit" value="확인">
		<input type="reset" value="취소">
	</form>
</body>
</html>
<%@ include file="bottom.jsp" %>