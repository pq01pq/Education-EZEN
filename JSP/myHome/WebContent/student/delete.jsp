<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- delete.jsp -->
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");

if(id == null || id.trim().equals("")){
	response.sendRedirect("student.jsp");	// 서버단에서 페이지 이동을 시켜주는 명령어
	return;
}

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection connection = DriverManager.getConnection(
		"jdbc:oracle:thin:@localhost:1521:xe", "bigdata3", "bigdata3");
PreparedStatement ps;
%>
<html>
<head>
<meta charset="UTF-8">
<title>삭제</title>
</head>
<body>
<%
String sql = "delete from student where id=?";
ps = connection.prepareStatement(sql);
ps.setString(1, id);
String message;
String url;
if(ps.executeUpdate() > 0) {
	message = "학생삭제 성공";
	url = "list.jsp";
} else {
	message = "학생삭제 실패";
	url = "student.jsp";
}
ps.close();
connection.close();
%>
<script type="text/javascript">
  alert("<%= message %>")
  location.href("<%= url %>")
</script>
</body>
</html>