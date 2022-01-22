<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="loginCheck.jsp" %>
<%@ include file="top.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkBlank(){
		if(f.subject.value == ""){
			alert("제목을 입력해주세요.")
			f.subject.focus()
			return false
		}
		if(f.content.value == ""){
			alert("내용을 입력해주세요.")
			f.content.focus()
			return false
		}
		if(f.content.value.length < 10){
			alert("내용은 10자 이상 입력해주세요.")
			f.content.focus()
			return false
		}
		return true
	}
</script>
</head>
<body>
<form name="f" action="insert.board" method="post" enctype="multipart/form-data" onsubmit="return checkBlank()">
<!-- 파라미터가 있으면 답글의 필드값이 설정되고 없으면 null이 됨 -->
<c:if test="${!empty param.num}">
	<input type="hidden" name="parentNum" value="${param.num}">
	<input type="hidden" name="reGroup" value="${param.reGroup}">
	<input type="hidden" name="reStep" value="${param.reStep + 1}">
	<input type="hidden" name="reLevel" value="${param.reLevel + 1}">
</c:if>
<input type="hidden" name="ip" value="${ip}">
<table border="1">
	<tr><th>작성자</th><td><input type="text" name="writer" value="${sessionScope.name}" readonly></td></tr>
	<tr><th>제목</th><td><input type="text" name="subject"></td></tr>
	<tr><th>이메일</th><td><input type="text" name="email"></td></tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="content" rows="10" cols="30" style="resize: none;" placeholder="최소 10자이상 입력"></textarea>
		</td>
	</tr>
	<tr><th>파일</th><td><input type="file" name="fileName"></td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="passwd"></td></tr>
</table>
<table>
	<tr>
		<td>
			<input type="submit" value="글쓰기">
			<input type="reset" value="다시작성">
			<input type="button" onclick="history.back()" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%@ include file="bottom.jsp" %>