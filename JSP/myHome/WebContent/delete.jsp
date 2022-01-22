<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, my.student.*" %>
<jsp:useBean id="stdao" class="my.student.StudentDAO"/>
<!-- delete.jsp -->
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");

if(id == null || id.trim().equals("")){
	response.sendRedirect("student.jsp");	// 서버단에서 페이지 이동을 시켜주는 명령어
	return;
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>삭제</title>
</head>
<body>
<%
String message, url;
if(stdao.deleteStudent(id) > 0) {
	message = "학생삭제 성공";
	url = "list.jsp";
} else {
	message = "학생삭제 실패";
	url = "student.jsp";
}
%>
<script type="text/javascript">
  alert("<%= message %>")
  location.href("<%= url %>")
</script>
</body>
</html>