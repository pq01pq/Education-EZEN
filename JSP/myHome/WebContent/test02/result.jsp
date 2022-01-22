<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");

String msg = null, url = null;
if(id != null && id.equals("admin")){
	if(pw != null && pw.equals("1234")){
		session.setAttribute("id", id);
		msg = "로그인 되었습니다. 회원전용페이지로 이동합니다.";
		url = "sessionTest02.jsp";
	} else {
		msg = "비밀번호가 틀렸습니다. 다시 로그인해주세요.";
		url = "sessionTest01.jsp";
	}
} else {
	msg = "아이디가 틀렸습니다. 다시 로그인해주세요.";
	url = "sessionTest01.jsp";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	alert("<%= msg %>")
	location.href="<%= url %>"
</script>
</body>
</html>