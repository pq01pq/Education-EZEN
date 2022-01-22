<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr><th colspan="2"><h2>[${product.pname}] 상품정보</h2></th></tr>
	<tr>
		<td><img src="${root}/myshop/file/${product.pimage}"></td>
		<td>
			상품번호 : ${product.pnum}<br>
			상품이름 : ${product.pname}<br>
			상품가격 : ${product.price}<br>
			상품포인트 : ${product.point}<br>
			<form method="get" onsubmit="return loginCheck(${sessionScope.id})">
				상품갯수 <input type="number" name="qty" min="1" max="20" value="1">개<br>
				<input type="hidden" name="listKey" value="${param.listKey}">
				<input type="hidden" name="pnum" value="${product.pnum}">
				<input type="hidden" name="type" value="now">
				<input type="submit" formaction="cartAdd.mall" value="장바구니(add to cart)">
				<input type="submit" formaction="order.mall" value="즉시 구매(buy now)">
			</form>
		</td>
	</tr>
	<tr><td><b>상품 상세 설명</b><br>${product.pcontents}</td></tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>