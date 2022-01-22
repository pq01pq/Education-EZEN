<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어 예제 2</title>
</head>
<body>
<h2>표현언어 사용 예제 2 : 파라미터값 처리</h2>
<hr>
<form name="f" method="post">
	이름 : <input type="text" name="name">
	<input type="submit" value="확인">
</form>
<br>
<%
request.setCharacterEncoding("utf-8");
//String name = request.getParameter("name");
%>
<%-- 이름 : <%= name %><br> --%>
이름 : ${param.name}<br>
</body>
</html>