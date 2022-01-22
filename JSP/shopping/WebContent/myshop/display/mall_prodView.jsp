<%@page import="my.shop.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="productList" class="my.shop.mall.ProductList" scope="session"/>
<jsp:setProperty property="pool" name="productList" value="<%= pool %>"/>
<%
	String listKey = request.getParameter("list_key");
	int pnum = Integer.parseInt(request.getParameter("pnum"));
	ProductDTO product = productList.getProduct(listKey, pnum);
%>
</head>
<body>
<table>
	<tr><th colspan="2"><h2>[<%= product.getPname() %>] 상품정보</h2></th></tr>
	<tr>
		<td><img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>"></td>
		<td>
			상품번호 : <%= product.getPnum() %><br>
			상품이름 : <%= product.getPname() %><br>
			상품가격 : <%= product.getPrice() %><br>
			상품포인트 : <%= product.getPoint() %><br>
			<form method="get">
				상품갯수 <input type="number" name="qty" min="1" max="20" value="1">개<br>
				<input type="hidden" name="list_key" value="<%= listKey %>">
				<input type="hidden" name="pnum" value="<%= product.getPnum() %>">
				<input type="hidden" name="type" value="now">
				<input type="submit" formaction="mall_cartAdd.jsp" value="장바구니(add to cart)">
				<input type="submit" formaction="mall_order.jsp" value="즉시 구매(buy now)">
			</form>
		</td>
	</tr>
	<tr><td><b>상품 상세 설명</b><br><%= product.getPcontents() %></td></tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>