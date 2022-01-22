<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="loginCheck.jsp" %>
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
		if(f.fileName.value == ""){
			alert("파일 첨부 확인")
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
</head>
<body>
<form name="f" action="writePro.jsp" method="post" enctype="multipart/form-data" onsubmit="return checkBlank()">
<input type="hidden" name="ip" value="<%= request.getRemoteAddr() %>">
<table border="1">
	<tr><th>작성자</th><td><input type="text" name="writer" value="<%= name %>" readonly></td></tr>
	<tr><th>제목</th><td><input type="text" name="subject"></td></tr>
	<tr><th>이메일</th><td><input type="text" name="email"></td></tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="content" rows="10" cols="30" style="resize: none;" placeholder="최소 10자이상 입력"></textarea>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<div id="image_container"></div>
			<input type="file" name="fileName" onchange="setImg(event)">
		</td>
	</tr>
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