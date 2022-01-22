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
<h2>111번가에 오신 것을 환영합니다</h2>
<hr>
	<h2 style="height: 20px">${category.cname}</h2>
<hr>
<c:choose>
	<c:when test="${products == null || products.size() == 0}">
		<b>상품이 없습니다.</b>
	</c:when>
	<c:otherwise>
		<table>
		<c:forEach var="product" items="${products}" varStatus="status">
			<c:if test="${status.index % 3 == 0}">
				<tr>
			</c:if>
					<td>
					<a href="prodView.mall?listKey=${category.code}&pnum=${product.pnum}">
						<img src="${root}/myshop/file/${product.pimage}"><br>
						${product.pname}
					</a><br>
					<fmt:formatNumber value="${product.price}" type="currency"/><br>
					[${product.point}]포인트
					</td>
			<c:if test="${status.index % 3 == 2 || status.index >= products.size() - 1}">
				</tr>
			</c:if>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>
<%@ include file="bottom.jsp" %>