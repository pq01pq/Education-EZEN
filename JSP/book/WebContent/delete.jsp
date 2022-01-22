<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bookDAO" class="book.BookDAO"/>
<%
request.setCharacterEncoding("utf-8");
String bookName = request.getParameter("bookName");
if(bookName == null || bookName.trim().equals("")) {
	response.sendRedirect("input.jsp");
	return;
}
bookDAO.delete(bookName);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<script type="text/javascript">
	alert("삭제 완료")
	location.href="index.jsp"
</script>
</body>
</html>