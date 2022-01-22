<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- memberSsn.jsp -->
<%
String option = request.getParameter("option");
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../style.css">

<script type="text/javascript">
function check(option){
	if(f.name.value == ""){
		alert("이름 입력")
		f.name.focus()
		return false
	}
	if(f.ssn1.value == ""){
		alert("주민번호 앞자리 입력")
		f.ssn1.focus()
		return false
	}
	if(f.ssn2.value == ""){
		alert("주민번호 뒷자리 입력")
		f.ssn2.focus()
		return false
	}
	if(option == "findPw" && f.id.value == ""){
		alert("아이디 입력")
		f.id.focus()
		return false
	}
	return true
}
</script>

<%
if(option != null){
	switch(option){
	case "findId":%>
	<title>아이디 찾기</title>
<%
		break;
		
	case "findPw":%>
	<title>비밀번호 찾기</title>
<%
		break;
		
	default :%>
	<title>회원가입유무</title>
<%
	}
} else {%>
	<title>회원가입유무</title>
<% } %>
</head>
<body>
	<div align="center">
<%
if(option != null){
	switch(option){
	case "findId":%>
	<h2>아 이 디 찾 기</h2>
<%
		break;
		
	case "findPw":%>
	<h2>비 밀 번 호 찾 기</h2>
<%
		break;
		
	default :%>
	<h2>회 원 가 입 유 무</h2>
<%
	}
} else {%>
	<h2>회 원 가 입 유 무</h2>
<% } %>
		<form name="f" action="checkMember.jsp" method="post" onsubmit="return check('<%= option %>')">
			<input type="hidden" name="option" value="<%= option %>">
			<table width="700" class="outline">
				<tr>
					<th width="20%">회원명</th>
					<td><input type="text" name="name" class="box" ></td>
				</tr>
				<tr>
					<th width="20%">주민번호</th>
					<td><input type="text" name="ssn1" class="box" maxlength="6">
					- <input type="password" name="ssn2" class="box" maxlength="7"></td>
				</tr>
<%			if(option != null && option.equals("findPw")){ %>
				<tr>
					<th width="20%">아이디</th>
					<td><input type="text" name="id" class="box" ></td>
				</tr>
<%			} %>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="조회">
						<input type="reset" value="취소">
					</td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>