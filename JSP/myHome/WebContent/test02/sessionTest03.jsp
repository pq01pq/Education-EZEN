<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session.removeAttribute("id");
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	alert("로그아웃 되었습니다. 로그인 페이지로 이동합니다.")
	location.href="sessionTest01.jsp"
</script>
</body>

</html>