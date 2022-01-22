<%@page import="my.shop.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function view(pnum){
	window.open("prod_view.jsp?pnum=" + pnum, "상세보기", "width=800,height=600")
}
</script>
<jsp:useBean id="productDAO" class="my.shop.ProductBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="productDAO" value="<%= pool %>"/>
<%
	List<ProductDTO> products = productDAO.searchAll();
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
  <table border="1" style="width: 60%">
    <tr align="center"><th>번호</th><th>상품코드</th><th>상품명</th><th>이미지</th><th>가격</th><th>제조사</th><th>수량</th><th></th></tr>
<%	for(ProductDTO product : products){ %>
	<tr align="center">
	  <td><%= product.getPnum() %></td><td><%= product.getPcategory_fk() %></td>
	  <td><a href="javascript:view(<%= product.getPnum() %>)"><%= product.getPname() %></a></td>
	  <td>
	    <a href="javascript:view(<%= product.getPnum() %>)">
	      <img width="50px" height="50px" src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>">
	    </a>
	  </td>
	  <td><%= product.getPrice() %></td><td><%= product.getPcompany() %></td><td><%= product.getPqty() %></td>
	  <td>
	    <a href="prod_update.jsp?pnum=<%= product.getPnum() %>">수정</a> |&nbsp;
	    <a href="prod_delete.jsp?pnum=<%= product.getPnum() %>">삭제</a>
	  </td>
	</tr>
<%	} %>
  </table>
</div>
</body>
</html>
<%@ include file="bottom.jsp" %>