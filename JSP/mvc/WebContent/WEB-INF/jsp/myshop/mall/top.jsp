<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function loginCheck(id, nextUrl){
	if(id == null){
		alert("로그인해주세요.")
		location.href = "login.mem?source=mall"
		return false
	}
	
	if(nextUrl != null){
		location.href = nextUrl
		return false
	}
	
	return true
}
</script>
<c:if test="${categories == null || categories.size() == 0}">
<script type="text/javascript">
	alert("쇼핑몰 준비중")
</script>
</c:if>
<meta charset="UTF-8">
</head>
<body>
<table border="1" style="width: 1500px; height: 700px">
	<tr align="center" height="50">
		<td colspan="2">
			<a href="index.jsp">HOME</a> |&nbsp;
			<a href="start.admin">관리자홈</a> |&nbsp;
			<a href="start.mall">쇼핑몰홈</a> |&nbsp;
	<c:choose>
		<c:when test="${sessionScope.id == null || sessionScope.id.trim() == ''}">
			<a href="login.mem?source=mall">로그인</a> |&nbsp;
		</c:when>
		<c:otherwise>
			<a href="logout.mem?source=mall">로그아웃</a> |&nbsp;
		</c:otherwise>
	</c:choose>		
			<a href="javascript:loginCheck(${sessionScope.id}, 'cartList.mall')">장바구니</a> |&nbsp;
			<a href="company.jsp">회사소개</a>
		</td>
	</tr>
	<tr align="center">
		<td width="200" valign="top">		
			<h3>카테고리</h3>
			<table>
			<c:forEach var="category" items="${categories}">
				<tr>
					<td>
						<a href="cgProdList.mall?ccode=${category.code}">${category.cname} [${category.code}]</a>
					</td>
				</tr>
			</c:forEach>
			</table>
		</td>
		<td style="padding: 50px">
</body>
</html>