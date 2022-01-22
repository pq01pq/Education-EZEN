<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, my.student.*" %>
<jsp:useBean id="stdao" class="my.student.StudentDAO"/>
<!-- list.jsp -->
<html>
<head>
<meta charset="UTF-8">
<title>학생목록</title>
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
ArrayList<StudentDTO> list = stdao.listStudent();
if(list == null || list.size() == 0){%>
	<tr>
	  <td>등록된 학생 없음</td>
	</tr>
<%} else {
	for(StudentDTO dto : list){%>
		<tr><td><%= dto.getId() %></td><td><%= dto.getName() %></td><td><%= dto.getCname() %></td></tr>
<%	}
} %>
    </table>
  </div>
</body>
</html>