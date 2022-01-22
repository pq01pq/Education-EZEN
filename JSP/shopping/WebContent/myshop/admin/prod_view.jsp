<%@page import="my.shop.CategoryDTO"%>
<%@page import="my.shop.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="productDAO" class="my.shop.ProductBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="productDAO" value="<%= pool %>"/>
<jsp:useBean id="categoryDAO" class="my.shop.CategoryBean"/>
<jsp:setProperty property="pool" name="categoryDAO" value="<%= pool %>"/>
<%
	int pnum = Integer.parseInt(request.getParameter("pnum"));
	ProductDTO product = productDAO.select(pnum).get(0);
	String ccode = product.getPcategory_fk().substring(0, 4);
	String pcode = product.getPcategory_fk().substring(4, product.getPcategory_fk().length());
	CategoryDTO category = categoryDAO.select("code", ccode).get(0);
%>
<meta charset="UTF-8">
<title>111번가_상세보기_<%= product.getPname() %></title>
</head>
<body>
<div align="center">
  <table border="1">
    <tr align="center">
      <th>카테고리</th><td><%= category.getCname() %> [<%= ccode %>]</td>
      <th>번호</th><td><%= product.getPnum() %></td>
    </tr>
    <tr align="center">
      <th>상품명</th><td><%= product.getPname()  %> [<%= pcode %>]</td>
      <th>제조회사</th><td><%= product.getPcompany() %></td>
    </tr>
    <tr align="center">
      <th>상품이미지</th>
        <td colspan="3">
          <img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>">
        </td>
    </tr>
    <tr align="center">
      <th>수량</th><td><%= product.getPqty() %></td>
      <th>가격</th><td><%= product.getPrice() %></td>
    </tr>
    <tr align="center">
      <th>스펙</th><td><%= product.getPspec() %></td>
      <th>포인트</th><td><%= product.getPoint() %></td>
    </tr>
    <tr align="center">
      <th>상품설명</th>
      <td colspan="3">
        <textarea rows="5" cols="30" style="resize: none"><%= product.getPcontents() %></textarea>
      </td>
    </tr>
  </table>
</div>
</body>
</html>