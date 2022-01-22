<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String key = request.getParameter("name");
if(key == null || key.trim().equals("")){
	response.sendRedirect("student.jsp");
	return;
}
//key = key.trim();

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection connection = DriverManager.getConnection(
		"jdbc:oracle:thin:@localhost:1521:xe", "bigdata3", "bigdata3");
PreparedStatement ps;
ResultSet rs;
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
String sql = "select count(*) from student where name=?";
ps = connection.prepareStatement(sql);
ps.setString(1, key);
rs = ps.executeQuery();
rs.next();
int count = rs.getInt(1);
if(count == 0){%>
<tr>
  <td colspan="3"><%= key %>학생은 없음</td>
<tr>
<%} else {
	sql = "select * from student where name=?";
	ps = connection.prepareStatement(sql);
	ps.setString(1, key);
}

while(rs.next()){
	String id = rs.getString("id");
	String name = rs.getString("name");
	String cname = rs.getString("cname"); %>
	<tr><td><%= id %></td><td><%= name %></td><td><%= cname %></td></tr>
<%
}%>
<tr>
  <td colspan="3">찾는 학생은 <%= count %>명 있음</td>
<tr>
<% 
rs.close();
ps.close();
connection.close();
%>
    </table>
  </div>
</body>
</html>