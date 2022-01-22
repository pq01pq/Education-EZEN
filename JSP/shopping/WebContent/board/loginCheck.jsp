<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	if((String)session.getAttribute("id") == null || (String)session.getAttribute("name") == null){	%>
	<script type="text/javascript">
		alert("로그인 먼저 해주세요")
		location.href="login/login.jsp"
	</script>
<%	}	%>
</head>
<body>

</body>
</html>