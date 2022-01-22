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
<table border="1" style="width: 600px">
	<tr height="80"><th colspan="3">결재 내역서</th></tr>
	<tr height="30"><th>상품명</th><th>수량</th><th>금액</th></tr>
<c:set var="totalPrice" value="0"/>
<c:forEach var="product" items="${products}">
	<tr height="50" align="right">
		<td align="center">${product.pname}</td>
		<td>${product.pqty}개</td>
		<td><fmt:formatNumber value="${product.price * product.pqty}" type="currency"/></td>
	<tr>
	<c:set var="totalPrice" value="${totalPrice + product.price * product.pqty}"/>
</c:forEach>
	<tr height="80"><td colspan="3"><b>결재하실 총액은</b> : <fmt:formatNumber value="${totalPrice}" type="currency"/></td></tr>
</table>
</body>
</html>
<%@ include file="bottom.jsp" %>