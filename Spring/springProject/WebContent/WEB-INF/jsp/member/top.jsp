<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- top.jsp -->
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript">
/* 새창 띄우기 */
function checkMember(option){
	let url = "member_ssn.do"
	if(option != null && option != ""){
		url = url + "?option=" + option
	}
	//window.open(url, "check", "width=640,height=400");
	location.href=url
}

function loginCheck(){
	if(f.id.value==""){
		alert("아이디 입력")
		f.id.focus()
		return
	}
	if(f.passwd.value==""){
		alert("비밀번호 입력")
		f.passwd.focus()
		return
	}
	document.f.submit()
}
</script>
</head>
<body>

<div align="center">
 <table border="1" width="800" height="600">
  <tr height="10%"> <td align="center" colspan="2">
   <a href="index.jsp">홈</a> |&nbsp;
<c:choose>
	<c:when test="${sessionScope.id != null && sessionScope.name != null}">
		<a href="logout.mem?source=mem">로그아웃</a> |&nbsp;
	</c:when>
	<c:otherwise>
		 <a href="login.mem?source=mem">로그인</a> |&nbsp;
		 <a href="javascript:checkMember()">회원가입</a> |&nbsp;
	</c:otherwise>
</c:choose>
   <a href="member_search.do?mode=all">회원보기</a> |&nbsp;
   <a href="member_search.do?mode=find">회원찾기</a> |&nbsp;
   <a href="start.admin">쇼핑몰(관리자)</a> |&nbsp;
   <a href="start.mall">쇼핑몰</a> |&nbsp;
   <a href="<%=request.getContextPath()%>/board/list.jsp">게시판</a> |&nbsp;
   <a href="<%=request.getContextPath()%>/company.jsp">회사소개</a></td> </tr>
  <tr hegith="80%"> <td width="20%" valign="top">tree/view</td> <td>