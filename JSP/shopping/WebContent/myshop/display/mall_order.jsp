<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="my.shop.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="productList" class="my.shop.mall.ProductList" scope="session"/><!-- use at buy now -->
<jsp:useBean id="cart" class="my.shop.mall.CartBean" scope="session"/><!-- use at cart -->
<%
	DecimalFormat df = new DecimalFormat("###,###");
	
	String type = request.getParameter("type");
	
	// use at buy now
	String qtyStr = request.getParameter("qty");
	int qty = 0;
	if(qtyStr != null && !qtyStr.trim().equals("")){
		qty = Integer.parseInt(qtyStr);
	}
	
	String listKey = request.getParameter("list_key");
	
	String pnumStr = request.getParameter("pnum");
	int pnum = 0;
	if(pnumStr != null && !pnumStr.trim().equals("")){
		pnum = Integer.parseInt(pnumStr);
	}
	//
%>
</head>
<body>
<%	List<ProductDTO> products = null;
	switch(type){
	case "now":
		products = new ArrayList<>();
		ProductDTO product = productList.getProduct(listKey, pnum);
		product.setPqty(qty);
		products.add(product);
		break;
	case "cart":
		products = cart.getCart();
		break;
	default :
	}	%>
<table border="1" style="width: 600px">
	<tr height="80"><th colspan="3">결재 내역서</th></tr>
	<tr height="30"><th>상품명</th><th>수량</th><th>금액</th></tr>
<%	int totalPrice = 0;
	for(ProductDTO product : products){	%>
	<tr height="50" align="right">
		<td align="center"><%= product.getPname() %></td>
		<td><%= product.getPqty() %>개</td>
		<td><%= df.format(product.getPrice() * product.getPqty()) %>원</td>
	<tr>
<%		totalPrice += product.getPrice() * product.getPqty();
	}	%>
	<tr height="80"><td colspan="3"><b>결재하실 총액은</b> : <%= df.format(totalPrice) %>원</td></tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>