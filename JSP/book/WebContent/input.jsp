<%@ include file="top.jsp" %>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
	<form action="insert.jsp" method="post">
		도서명 <input type="text" name="bookName"><br>
		지은이 <input type="text" name="writer"><br>
		출판사 <input type="text" name="publisher"><br>
		판매가 <input type="text" name="price"><br>
		<input type="submit" value="입력">
		<input type="reset" value="취소">
	</form>
</div>
</body>
</html>

<%@ include file="bottom.jsp" %>