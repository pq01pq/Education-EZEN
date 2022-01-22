<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="categoryDAO" class="my.shop.CategoryBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="categoryDAO" value="<%= pool %>"/>
<%
	int cnum = Integer.parseInt(request.getParameter("cnum"));
	if(categoryDAO.delete(cnum) < 1){ %>
	<script type="text/javascript">
		alert("DB 오류")
		location.href="cate_list.jsp"
	</script>
<%	} else { %>
	<script type="text/javascript">
		alert("삭제 완료")
		location.href="cate_list.jsp"
	</script>
<%	}
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>