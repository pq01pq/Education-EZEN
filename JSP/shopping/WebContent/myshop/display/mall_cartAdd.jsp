<%@page import="my.shop.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="productList" class="my.shop.mall.ProductList" scope="session"/>
<jsp:useBean id="cart" class="my.shop.mall.CartBean" scope="session"/>
<%
	String listKey = request.getParameter("list_key");
	int qty = Integer.parseInt(request.getParameter("qty"));
	int pnum = Integer.parseInt(request.getParameter("pnum"));
	ProductDTO addProduct = productList.getProduct(listKey, pnum);
	addProduct.setPqty(qty);
	cart.add(addProduct);
	response.sendRedirect("mall_cartList.jsp");
%>
</head>
<body>

</body>
</html>