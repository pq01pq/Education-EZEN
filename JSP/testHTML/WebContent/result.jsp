<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");	// post방식에서만 가능
String id = request.getParameter("id");
String pw =  request.getParameter("pw");

//out.println("<h2>" + id + "님이 로그인하셨습니다.<h2>");
%>
<html>
<body>
	
	<%= id %>님이 로그인하셨습니다.<br>
	전송 내용<br>
</body>
</html>