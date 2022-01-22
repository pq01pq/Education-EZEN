<%@page import="my.board.BoardDTO2"%>
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
	
	function setImg(event) {
		for (var file of event.target.files) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var filesystemName = file.name.split(".");
				if(filesystemName.length > 1) {
					var extension = filesystemName[filesystemName.length - 1];
					document.querySelector("div#image_container").innerHTML = "";  // 앞서 선택한 이미지 삭제
					if(extension == "jpg") {
						var img = document.createElement("img");
						img.setAttribute("src", event.target.result);
						document.querySelector("div#image_container").appendChild(img);  //새로 선택한 이미지 div에 출력
					}
				}
			}
			console.log(file);
			reader.readAsDataURL(file);
		}
	}
</script>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="boardDAO2" class="my.board.BoardBean2"/>
<jsp:setProperty property="pool" name="boardDAO2" value="<%= pool %>"/>
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDTO2 article = boardDAO2.select(num).get(0);
%>
</head>
<body>
<form name="f" action="updatePro.jsp" method="post" enctype="multipart/form-data" onsubmit="return checkBlank()">
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
	<tr>
		<th>첨부파일</th>
		<td>
			<div id="image_container">
		<%	if(article.getFileName() != null && !article.getFileName().trim().equals("")) {
				String[] filesystemName = article.getFileName().split("\\.");
				if(filesystemName.length > 1){
					String extension = filesystemName[filesystemName.length - 1];
					if(extension.equals("jpg")){	%>
				<img src="<%= request.getContextPath() %>/board2/file/<%= article.getFileName() %>">
		<%			}
				}
			}	%>
			</div>
			<input type="file" name="fileName" onchange="setImg(event)">
			<input type="hidden" name="preFileName" value="<%= article.getFileName() %>">
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