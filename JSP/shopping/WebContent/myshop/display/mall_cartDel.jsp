<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="cart" class="my.shop.mall.CartBean" scope="session"/>
<% 
	int pnum = Integer.parseInt(request.getParameter("pnum"));
	cart.remove(pnum);
	response.sendRedirect("mall_cartList.jsp");
%>
</head>
<body>

</body>
</html>