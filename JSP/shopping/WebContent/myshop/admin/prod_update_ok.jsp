<%@page import="java.io.File"%>
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
	
	String preImageName = mr.getParameter("preImage");
	File preImage = null;
	if(preImageName != null){
		preImage = new File(saveDirectory, preImageName);
	}
%>
<jsp:useBean id="productDAO" class="my.shop.ProductBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="productDAO" value="<%= pool %>"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	if(productDAO.update(mr) < 1){ %>
	<script type="text/javascript">
		alert("수정 실패 : DB 오류")
		history.back()
	</script>
<%	} else { 
		if(mr.getFilesystemName("pimage") != null && preImage != null){
			preImage.delete();
		} %>
	<script type="text/javascript">
		alert("수정 완료")
		location.href="prod_list.jsp"
	</script>
<%	} %>
</body>
</html>