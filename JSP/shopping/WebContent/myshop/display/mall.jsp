<%@page import="java.text.DecimalFormat"%>
<%@page import="my.shop.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑은 111번가에서</title>
<jsp:useBean id="productList" class="my.shop.mall.ProductList" scope="session"/>
<jsp:setProperty property="pool" name="productList" value="<%= pool %>"/>
<%
	List<ProductDTO> hitProducts = productList.select("pspec", "hit");
	List<ProductDTO> newProducts = productList.select("pspec", "new");
	List<ProductDTO> bestProducts = productList.select("pspec", "best");
	DecimalFormat df = new DecimalFormat("###,###");
%>
</head>
<body>
<h2>111번가에 오신 것을 환영합니다</h2>
<hr>
	<h2 style="height: 20px">HIT</h2>
<hr>
<%	if(hitProducts == null || hitProducts.size() == 0){	%>
	<b>HIT 상품이 없습니다.</b>
<%	} else {	%> 
	<table>
<%		int i = 0;
		for(ProductDTO product : hitProducts){
			if(i % 3 == 0){ %>
		<tr>
<%			} %>
			<td>
				<a href="mall_prodView.jsp?list_key=hit&pnum=<%= product.getPnum() %>">
					<img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>"><br>
					<%= product.getPname() %>
				</a><br>
				<%= df.format(product.getPrice()) %>원<br>
				[<%= product.getPoint() %>]포인트
			<td>
<%			i++;
			if(i % 3 == 0 || i >= hitProducts.size()) {	%>	
		</tr>	
<%			}
		}	%>
	</table>
<%	}	%>

<hr>
	<h2 style="height: 20px">NEW</h2>
<hr>
<%	if(newProducts == null || newProducts.size() == 0){	%>
		<b>NEW 상품이 없습니다.</b>
<%	} else {	%>
	<table>
<%		int i = 0;
		for(ProductDTO product : newProducts){
			if(i % 3 == 0){ %>
		<tr>
<%			}	%>
			<td>
				<a href="mall_prodView.jsp?list_key=new&pnum=<%= product.getPnum() %>"">
					<img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>"><br>
					<%= product.getPname() %>
				</a><br>
				<%= df.format(product.getPrice()) %>원<br>
				[<%= product.getPoint() %>]포인트
			<td>
<%			i++;
			if(i % 3 == 0 || i >= hitProducts.size()) { %>	
		</tr>	
<%			}
		}	%>
	</table>
<%	}	%>
</body>

<hr>
	<h2 style="height: 20px">BEST</h2>
<hr>
<%	if(bestProducts == null || bestProducts.size() == 0){	%>
		<b>BEST 상품이 없습니다.</b>
<%	} else {	%>
	<table>
<%		int i = 0;
		for(ProductDTO product : bestProducts){
			if(i % 3 == 0){ %>
		<tr>
<%			}	%>
			<td>
				<a href="mall_prodView.jsp?list_key=best&pnum=<%= product.getPnum() %>">
					<img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>"><br>
					<%= product.getPname() %>
				</a><br>
				<%= df.format(product.getPrice()) %>원<br>
				[<%= product.getPoint() %>]포인트
			<td>
<%			i++;
			if(i % 3 == 0 || i >= hitProducts.size()) { %>	
		</tr>	
<%			}
		}	%>
	</table>
<%	}	%>
</body>
</html>
<%@ include file="bottom.jsp" %>