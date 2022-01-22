<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cookie = new Cookie("id", "admin");
	cookie.setMaxAge(24*60*60);
	response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 테스트 1</title>
</head>
<body>
	<h2>쿠키 전송 끝</h2>
</body>
</html>