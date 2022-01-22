<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="productDAO" class="my.shop.ProductBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="productDAO" value="<%= pool %>"/>
<%
	int pnum = Integer.parseInt(request.getParameter("pnum"));
	if(productDAO.delete(pnum) < 1){ %>
	<script type="text/javascript">
		alert("상품삭제 실패 : DB 오류")
		location.href="prod_list.jsp"
	</script>
<%	} else { %>
	<script type="text/javascript">
		alert("상품삭제 완료")
		location.href="prod_list.jsp"
	</script>	
<%	} %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>