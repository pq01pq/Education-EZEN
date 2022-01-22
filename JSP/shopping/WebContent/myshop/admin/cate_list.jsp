<%@page import="my.shop.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="catDao" class="my.shop.CategoryBean"/>
<jsp:setProperty property="pool" name="catDao" value="<%= pool %>"/>
<%
	List<CategoryDTO> categories = catDao.searchAll();
%>
<meta charset="UTF-8">
<title>카테고리 목록</title>
</head>
<body>
<div align="center">
  <table border="1">
    <tr><th>번호</th><th>코드</th><th>카테고리명</th><th></th><tr>
<%
	for(CategoryDTO category : categories){ %>
    <tr>
      <td><%= category.getCnum() %></td>
      <td><%= category.getCode() %></td>
      <td><%= category.getCname() %></td>
      <td>
        <a href="cate_delete.jsp?cnum=<%= category.getCnum() %>">삭제</a>
      </td>
    </tr>
<%	}
%>
  </table>
</div>
</body>
</html>
<%@ include file="bottom.jsp" %>