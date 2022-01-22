<%@page import="book2.BookDTO"%>
<%@page import="java.util.List"%>
<%@ include file="top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	@SuppressWarnings("unchecked")
	List<BookDTO> books = (List<BookDTO>)request.getAttribute("books");
	if(books == null){
		response.sendRedirect("book");
		return;
	}
%>
<div align="center">
	<table border="1">
		<tr> <th>도서명</th> <th>지은이</th> <th>출판사</th> <th>판매가</th> <th>입고일</th> </tr>
<%
	for(BookDTO book : books){ %>
		<tr>
			<td><%= book.getBookName() %></td>
			<td><%= book.getWriter() %></td>
			<td><%= book.getPublisher() %></td>
			<td><%= book.getPrice() %></td>
			<td><%= book.getIndate() %></td>
		</tr>
<%	} %>
	</table>
	
	<form action="book?command=search" method="post">
		<select name="column">
			<option value="">선택</option>
			<option value="bookname">도서명</option>
			<option value="writer">지은이</option>
			<option value="publisher">출판사</option>
		</select>
		<input type="text" name="key">
		<input type="submit" value="검색">
	</form>
</div>

<%@ include file="bottom.jsp" %>