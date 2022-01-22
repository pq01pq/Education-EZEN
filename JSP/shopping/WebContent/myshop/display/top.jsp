<%@page import="java.util.List"%>
<%@page import="my.shop.CategoryDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="categoryDAO" class="my.shop.CategoryBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="categoryDAO" value="<%= pool %>"/>
<%
	List<CategoryDTO> categories = categoryDAO.searchAll();
	if(categories == null || categories.size() == 0){ %>
	<script type="text/javascript">
		alert("쇼핑몰 준비중")
	</script>
<%	}
%>
<meta charset="UTF-8">
</head>
<body>
<table border="1" style="width: 1500px; height: 700px">
	<tr align="center" height="50">
		<td colspan="2">
			<a>HOME</a> |&nbsp;
			<a href="<%= request.getContextPath() %>/myshop/admin/main.jsp">관리자홈</a> |&nbsp;
			<a href="mall.jsp">쇼핑몰홈</a> |&nbsp;
			<a href="mall_cartList.jsp">장바구니</a> |&nbsp;
			<a href="company.jsp">회사소개</a>
		</td>
	</tr>
	<tr align="center">
		<td width="200" valign="top">		
			<h3>카테고리</h3>
			<table>
		<%	for(CategoryDTO category : categories){ %>
				<tr>
					<td>
						<a href="mall_cgProdList.jsp?ccode=<%= category.getCode() %>"><%= category.getCname() %> [<%= category.getCode() %>]</a>
					</td>
				</tr>
		<%	} %>
			</table>
		</td>
		<td style="padding: 50px">
</body>
</html>