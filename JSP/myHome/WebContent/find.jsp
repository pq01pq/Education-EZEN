<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, my.student.*" %>
<jsp:useBean id="stdao" class="my.student.StudentDAO"/>
<%
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");

if(name == null || name.trim().equals("")){
	response.sendRedirect("student.jsp");
	return;
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div align="center">
    <hr color="green" width="300">
    <h2>학 생 목 록 보 기</h2>
    <hr color="green" width="300">
    
    <table border="1" width="300">
      <tr bgcolor="yellow">
        <th>아이디</th>
        <th>학생명</th>
        <th>학급명</th>
      </tr>
<%
ArrayList<StudentDTO> list = stdao.findStudnet(name);
if(list.size() == 0){%>
<tr>
  <td colspan="3"><%= name %>학생은 없음</td>
<tr>
<%} else {
	for(StudentDTO dto : list){%>
		<tr><td><%= dto.getId() %></td><td><%= dto.getName() %></td><td><%= dto.getCname() %></td></tr>
<% 	}
}%>
	  <tr>
	    <td colspan="3">찾는 학생은 <%= list.size() %>명 있음</td>
	  <tr>
    </table>
  </div>
</body>
</html>