<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	request.setCharacterEncoding("utf-8");
	String saveDirectory = config.getServletContext().getRealPath("/myshop/file");
	MultipartRequest mr = new MultipartRequest(
			request, saveDirectory, 10*1024*1024, "utf-8");
%>
<jsp:useBean id="productDAO" class="my.shop.ProductBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="productDAO" value="<%= pool %>"/>
<%	if(productDAO.insert(mr) < 1){ %>
	<script type="text/javascript">
		alert("상품등록 실패 : DB 오류")
		location.href="prod_input.jsp"
	</script>
<%	} else { %>
	<script type="text/javascript">
		alert("상품등록 완료")
		location.href="prod_list.jsp"
	</script>
<%	} %>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>