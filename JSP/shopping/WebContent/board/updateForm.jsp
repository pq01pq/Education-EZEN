<%@page import="my.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkBlank(){
		if(f.writer.value == ""){
			alert("빈칸 확인")
			f.writer.focus()
			return false
		}
		if(f.subject.value == ""){
			alert("빈칸 확인")
			f.subject.focus()
			return false
		}
		if(f.content.value == ""){
			alert("빈칸 확인")
			f.content.focus()
			return false
		}
		return true
	}
</script>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="boardDAO" class="my.board.BoardBean"/>
<jsp:setProperty property="pool" name="boardDAO" value="<%= pool %>"/>
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDTO article = boardDAO.select(num).get(0);
%>
</head>
<body>
<form name="f" action="updatePro.jsp" method="post" onsubmit="return checkBlank()">
<input type="hidden" name="num" value="<%= article.getNum() %>">
<input type="hidden" name="readCount" value="<%= article.getReadCount() %>">
<input type="hidden" name="ip" value="<%= request.getRemoteAddr() %>">
<table border="1">
	<tr><th>작성자</th><td><input type="text" name="writer" value="<%= name %>" readonly></td></tr>
	<tr><th>제목</th><td><input type="text" name="subject" value="<%= article.getSubject() %>"></td></tr>
	<tr><th>이메일</th><td><input type="text" name="email" value="<%= article.getEmail() %>"></td></tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="content" rows="10" cols="30" style="resize: none;"
			placeholder="최소 10자이상 입력"><%= article.getContent() %></textarea>
		</td>
	</tr>
	<tr><th>비밀번호</th><td><input type="password" name="passwd" value="<%= article.getPasswd() %>"></td></tr>
</table>
<table>
	<tr>
		<td>
			<input type="submit" value="수정">
			<input type="reset" value="다시작성">
			<input type="button" onclick="history.go(-2)" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%@ include file="bottom.jsp" %>