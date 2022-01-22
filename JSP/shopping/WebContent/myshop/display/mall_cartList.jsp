<%@page import="my.shop.ProductDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="cart" class="my.shop.mall.CartBean" scope="session"/>
<%
	DecimalFormat df = new DecimalFormat("###,###");
	List<ProductDTO> products = cart.getCart();
%>
</head>
<body>
<table border="1">
	<tr><th colspan="6">장바구니 보기</th></tr>
	<tr><th>번호</th><th>상품명</th><th>수량</th><th>단가</th><th>금액</th><th>삭제</th></tr>
<%	int index = 1;
	int totalPrice = 0;
	int totalPoint = 0;
	if(products.size() > 0) {
		for(ProductDTO product : products) {	%>
		
		<tr>
			<td><%= index %></td>
			<td>
				<img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>"><br>
				<%= product.getPname() %>
			</td>
			<td>
				<form action="mall_cartEdit.jsp" method="get">
					<input type="hidden" name="pnum" value="<%= product.getPnum() %>">
					<input type="number" name="qty" min="1" max="20" value="<%= product.getPqty() %>">개<br>
					<input type="submit" value="수정">
				</form>
			</td>
			<td>
				<%= df.format(product.getPrice()) %>원<br>
				[<%= product.getPoint() %>] point
			</td>
			<td>
				<%= df.format(product.getPrice()) %>원<br>
				[<%= product.getPoint() %>] point
			</td>
			<td>
				<form action="mall_cartDel.jsp" method="get">
					<input type="hidden" name="pnum" value="<%= product.getPnum() %>">
					<input type="submit" value="삭제">
				</form>
			</td>
		</tr>
<%			totalPrice += product.getPrice();
			totalPoint += product.getPoint();
			index++;
		}	
	} else {	%>
	<tr><th colspan="6">장바구니가 비었습니다.</th></tr>
<%	}	%>
	
	<tr>
		<td colspan="4">
			<b>장바구니 총액 : </b><%= totalPrice %><br>
			총 적립 포인트 : [<%= totalPoint %>] point
		</td>
		<td colspan="2">
<%		if(products.size() > 0){	%>
			<a href="mall_order.jsp?type=cart">[주문하기]</a>
<%		}	%>
			<a href="javascript:history.go(-2)">[계속 쇼핑]</a>
		</td>
	</tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>