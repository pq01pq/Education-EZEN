<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function cartDelete(){
	return confirm("정말로 삭제하시겠습니까?")
}
</script>
</head>
<body>
<table border="1">
	<tr><th colspan="6">장바구니 보기</th></tr>
	<tr><th>번호</th><th>상품명</th><th>수량</th><th>단가</th><th>금액</th><th>삭제</th></tr>
<c:set var="totalPrice" value="0"/>
<c:set var="totalPoint" value="0"/>
<c:choose>
	<c:when test="${products.size() > 0}">
		<c:forEach var="product" items="${products}" varStatus="status">
		<tr>
			<td>${status.index}</td>
			<td>
				<img src="${root}/myshop/file/${product.pimage}"><br>
				${product.pname}
			</td>
			<td>
				<form action="cartEdit.mall" method="get" onsubmit="return loginCheck(${sessionScope.id})">
					<input type="hidden" name="pnum" value="${product.pnum}">
					<input type="number" name="qty" min="1" max="20" value="${product.pqty}">개<br>
					<input type="submit" value="수정">
				</form>
			</td>
			<td>
				<fmt:formatNumber value="${product.price}" type="currency"/><br>
				[${product.point}] point
			</td>
			<td>
				<fmt:formatNumber value="${product.price * product.pqty}" type="currency"/><br>
				[${product.point * product.pqty}] point
			</td>
			<td>
				<form action="cartDel.mall" method="get" onsubmit="return loginCheck(${sessionScope.id}) && cartDelete()">
					<input type="hidden" name="pnum" value="${product.pnum}">
					<input type="submit" value="삭제">
				</form>
			</td>
		</tr>
		<c:set var="totalPrice" value="${totalPrice + product.price * product.pqty}"/>
		<c:set var="totalPoint" value="${totalPoint + product.point * product.pqty}"/>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><th colspan="6">장바구니가 비었습니다.</th></tr>
	</c:otherwise>
</c:choose>
	<tr>
		<td colspan="4">
			<b>장바구니 총액 : </b>${totalPrice}<br>
			총 적립 포인트 : [${totalPoint}] point
		</td>
		<td colspan="2">
		<c:if test="${products.size() > 0}">
			<a href="order.mall?type=cart">[주문하기]</a>
		</c:if>
			<a href="javascript:history.go(-2)">[계속 쇼핑]</a>
		</td>
	</tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>