<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp" %>
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
<c:if test="${empty students}">
	<tr>
		<td colspan="3">등록된 학생이 없습니다</td>
	</tr>
</c:if>
<c:forEach items="${students}" var="student">
	<tr bgcolor="yellow">
		<th>${student.id}</th>
		<th>${student.name}</th>
		<th>${student.cname}</th>
	</tr>
</c:forEach>
    </table>
  </div>
</body>
</html>
<%@ include file="bottom.jsp" %>