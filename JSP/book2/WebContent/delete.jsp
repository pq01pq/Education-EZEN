<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<div align="center">
	<form action="book?command=delete" method="post">
		도서명 <input type="text" name="bookName"><br>
		<input type="submit" value="삭제">
		<input type="reset" value="취소">
	</form>
</div>
</body>
</html>

<%@ include file="bottom.jsp" %>