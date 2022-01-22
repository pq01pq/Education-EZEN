<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String priceString = request.getParameter("price");
int price = 0;
try {
	price = Integer.parseInt(priceString);
} catch(NumberFormatException e){
	e.printStackTrace();
	response.sendRedirect("input.jsp");
	return;
} %>

<jsp:useBean id="bookDTO" class="book.BookDTO">
	<jsp:setProperty property="bookName" name="bookDTO"/>
	<jsp:setProperty property="writer" name="bookDTO"/>
	<jsp:setProperty property="publisher" name="bookDTO"/>
</jsp:useBean>

<jsp:useBean id="bookDAO" class="book.BookDAO"/>


<%
	if(bookDTO.getBookName() == null ||
	bookDTO.getWriter() == null ||
	bookDTO.getPublisher() == null ||
	bookDTO.getBookName().trim().equals("") ||
	bookDTO.getWriter().trim().equals("") ||
	bookDTO.getPublisher().trim().equals("")) {
		response.sendRedirect("input.jsp");
		return;
	}
	bookDTO.setPrice(price);
	bookDAO.insert(bookDTO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert</title>
</head>
<body>

<script type="text/javascript">
	alert("입력 성공")
	location.href="index.jsp"
</script>

</body>
</html>