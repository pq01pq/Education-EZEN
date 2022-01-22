<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookie = request.getCookies();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 테스트 2</title>
</head>
<body>
	<h2>클라이언트에서 넘어온 쿠키</h2>
<%
if(cookie != null && cookie.length != 0){
	for(int i = 0; i < cookie.length; i++){
		out.println("<h3>name = " + cookie[i].getName() +
				", value = " + cookie[i].getValue() + "</h3><br>");
	}
}
%>
</body>
</html>