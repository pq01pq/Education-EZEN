<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="categoryDAO" class="my.shop.CategoryBean"/>
<jsp:setProperty property="pool" name="categoryDAO" value="<%= pool %>"/>

<jsp:useBean id="category" class="my.shop.CategoryDTO"/>
<jsp:setProperty property="*" name="category"/>
<%
	if(category.getCode() == null || category.getCode().trim().equals("") ||
	category.getCname() == null || category.getCname().trim().equals("")){ %>
	<script type="text/javascript">
		alert("잘못된 카테고리 정보")
		location.href="cate_input.jsp"
	</script>
<%	}
	
	if(categoryDAO.insert(category) < 1){ %>
	<script type="text/javascript">
		alert("DB오류")
		location.href="cate_input.jsp"
	</script>
<%	} else { %>
	<script type="text/javascript">
		alert("카테고리 입력 완료")
		location.href="cate_list.jsp"
	</script>
<%	} %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>