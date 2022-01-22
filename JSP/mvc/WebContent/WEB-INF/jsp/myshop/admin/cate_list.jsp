<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 목록</title>
</head>
<body>
<div align="center">
  <table border="1">
    <tr><th>번호</th><th>코드</th><th>카테고리명</th><th></th><tr>
<c:forEach var="category" items="${categories}">
	<tr>
      <td>${category.cnum}</td>
      <td>${category.code}</td>
      <td>${category.cname}</td>
      <td>
        <a href="cate_delete.admin?cnum=${category.cnum}">삭제</a>
      </td>
    </tr>
</c:forEach>
  </table>
</div>
</body>
</html>
<%@ include file="bottom.jsp" %>