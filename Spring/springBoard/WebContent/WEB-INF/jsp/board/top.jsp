<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
/* 새창 띄우기 */
function checkMember(option){
	let url = "ssn.mem?source=board"
	if(option != null || option != ""){
		url = url + "&option=" + option
	}
	//window.open(url, "check", "width=640,height=400");
	location.href = url
}

function loginCheck(){
	if(f.id.value==""){
		alert("아이디 입력")
		f.id.focus()
		return
	}
	if(f.passwd.value==""){
		alert("아이디 입력")
		f.passwd.focus()
		return
	}
	document.f.submit()
}
</script>
</head>
<body>
<div align="center">
<table border="1" style="width: 1500px; height: 700px">
	<tr align="center" height="50">
		<td colspan="2">
			<a href="index.jsp">홈</a> |&nbsp;
			<c:choose>
				<c:when test="${sessionScope.id != null}">
					<a href="logout.mem?source=board">로그아웃</a> |&nbsp;
				</c:when>
				<c:otherwise>
					<a href="login.mem?source=board">로그인</a> |&nbsp;
				</c:otherwise>
			</c:choose>
			<a href="javascript:checkMember()">회원가입</a> |&nbsp;
			<a href="search.mem?mode=all">회원보기</a> |&nbsp;
			<a href="search.mem?mode=find">회원찾기</a> |&nbsp;
			<a href="start.admin">쇼핑몰(관리자)</a> |&nbsp;
			<a href="start.mall">쇼핑몰</a> |&nbsp;
			<a href="start.board">게시판</a> |&nbsp;
			<a href="">자료실</a> |&nbsp;
			<a href="company.jsp">회사소개</a></td> </tr>
		</td>
	</tr>
	<tr align="center">
		<td width="200" valign="top">
		<c:choose>
			<c:when test="${sessionScope.name != null}">
				<b>[${sessionScope.name}]님 로그인중</b>
			</c:when>
			<c:otherwise>
				<b>로그인 해주세요</b>
			</c:otherwise>
		</c:choose>
			<br><br>
			<jsp:include page="/count/count.jsp"/>
		</td>
		<td style="padding: 50px">
</body>
</html>