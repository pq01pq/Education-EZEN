<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<%	if(id == null){ %>
	<script type="text/javascript">
		alert("로그인을 먼저 해주십시오")
		location.href="sessionTest01.jsp"
	</script>
<%	} %>
<meta charset="UTF-8">
<title>회원 전용 페이지</title>
</head>
<body>
	<h1>회원 전용 페이지</h1>
	<h2>회원만 볼 수 있도록 합니다. 로그인이 되지 않았을 때는 절대로 보여져서는 안됩니다.</h2>
	<a href="sessionTest03.jsp">로그아웃</a>
</body>
</html>