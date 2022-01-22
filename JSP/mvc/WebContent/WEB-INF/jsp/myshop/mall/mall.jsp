<%@page import="shop.mall.ProductList"%>
<%@page import="shop.ProductDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑은 111번가에서</title>
</head>
<body>
<h2>111번가에 오신 것을 환영합니다</h2>
<hr>
	<h2 style="height: 20px">HIT</h2>
<hr>
<c:choose>
	<c:when test="${hitProducts == null || hitProducts.size() == 0}">
		<b>HIT 상품이 없습니다.</b>
	</c:when>
	<c:otherwise>
		<table>
		<c:forEach var="product" items="${hitProducts}" varStatus="status">
			<c:if test="${status.index % 3 == 0}">
				<tr>
			</c:if>
					<td>
					<a href="prodView.mall?listKey=hit&pnum=${product.pnum}">
						<img src="${root}/myshop/file/${product.pimage}"><br>
						${product.pname}
					</a><br>
					<fmt:formatNumber value="${product.price}" type="currency"/><br>
					[${product.point}]포인트
					</td>
			<c:if test="${status.index % 3 == 2 || status.index >= hitProducts.size() - 1}">
				</tr>
			</c:if>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
<hr>
	<h2 style="height: 20px">NEW</h2>
<hr>
<c:choose>
	<c:when test="${newProducts == null || newProducts.size() == 0}">
		<b>NEW 상품이 없습니다.</b>
	</c:when>
	<c:otherwise>
		<table>
		<c:forEach var="product" items="${newProducts}" varStatus="status">
			<c:if test="${status.index % 3 == 0}">
				<tr>
			</c:if>
					<td>
					<a href="prodView.mall?listKey=new&pnum=${product.pnum}">
						<img src="${root}/myshop/file/${product.pimage}"><br>
						${product.pname}
					</a><br>
					<fmt:formatNumber value="${product.price}" type="currency"/><br>
					[${product.point}]포인트
					</td>
			<c:if test="${status.index % 3 == 2 || status.index >= newProducts.size() - 1}">
				</tr>
			</c:if>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
<hr>
	<h2 style="height: 20px">BEST</h2>
<hr>
<c:choose>
	<c:when test="${bestProducts == null || bestProducts.size() == 0}">
		<b>BEST 상품이 없습니다.</b>
	</c:when>
	<c:otherwise>
		<table>
		<c:forEach var="product" items="${bestProducts}" varStatus="status">
			<c:if test="${status.index % 3 == 0}">
				<tr>
			</c:if>
					<td>
					<a href="prodView.mall?listKey=best&pnum=${product.pnum}">
						<img src="${root}/myshop/file/${product.pimage}"><br>
						${product.pname}
					</a><br>
					<fmt:formatNumber value="${product.price}" type="currency"/><br>
					[${product.point}]포인트
					</td>
			<c:if test="${status.index % 3 == 2 || status.index >= newProducts.size() - 1}">
				</tr>
			</c:if>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>
<%@ include file="bottom.jsp" %>