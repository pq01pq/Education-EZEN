<%@page import="java.text.DecimalFormat"%>
<%@page import="my.shop.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="productList" class="my.shop.mall.ProductList" scope="session"/>
<jsp:setProperty property="pool" name="productList" value="<%= pool %>"/>
<%
	String ccode = request.getParameter("ccode");
	List<ProductDTO> products = productList.search("pcategory_fk", ccode);
	CategoryDTO category = categoryDAO.select("code", ccode).get(0);
	DecimalFormat df = new DecimalFormat("###,###");
%>
</head>
<body>
<h2>111번가에 오신 것을 환영합니다</h2>
<hr>
	<h2 style="height: 20px"><%= category.getCname() %></h2>
<hr>
<%	if(products == null || products.size() == 0){	%>
	<b>상품이 없습니다.</b>
<%	} else {	%> 
	<table>
<%		int i = 0;
		for(ProductDTO product : products){
			if(i % 3 == 0){ %>
		<tr>
<%			} %>
			<td>
				<a href="mall_prodView.jsp?list_key=<%= ccode %>&pnum=<%= product.getPnum() %>">
					<img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>"><br>
					<%= product.getPname() %>
				</a><br>
				<%= df.format(product.getPrice()) %>원<br>
				[<%= product.getPoint() %>]포인트
			<td>
<%			i++;
			if(i % 3 == 0 || i >= products.size()) {	%>	
		</tr>	
<%			}
		}	%>
	</table>
<%	}	%>
</body>
</html>
<%@ include file="bottom.jsp" %>