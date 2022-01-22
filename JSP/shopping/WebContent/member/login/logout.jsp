<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function logout(){
		
	}
</script>
<script type="text/javascript">
	alert("로그아웃 되었습니다.")
	location.href="login.jsp"
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>