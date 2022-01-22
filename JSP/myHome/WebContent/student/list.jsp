<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection connection = DriverManager.getConnection(
		"jdbc:oracle:thin:@localhost:1521:xe", "bigdata3", "bigdata3");
%>
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
String sql = "select * from student";
PreparedStatement ps = connection.prepareStatement(sql);
ResultSet rs = ps.executeQuery();

while(rs.next()){
	String id = rs.getString("id");
	String name = rs.getString("name");
	String cname = rs.getString("cname"); %>
	<tr><td><%= id %></td><td><%= name %></td><td><%= cname %></td></tr>
<%
}
rs.close();
ps.close();
connection.close();
%>
    </table>
  </div>
</body>
</html>