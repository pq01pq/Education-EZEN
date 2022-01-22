<%@page import="java.util.List"%>
<%@page import="my.shop.CategoryDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
/* 새창 띄우기 */
function checkMember(option){
	let url = "<%=request.getContextPath()%>/member/memberSsn.jsp"
	if(option != null || option != ""){
		url = url + "?option=" + option
	}
	window.open(url, "check", "width=640,height=400");
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
<%
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	boolean isLogin = false;
	if(id != null && name != null){
		isLogin = true;
	}
%>
</head>
<body>
<div align="center">
<table border="1" style="width: 1500px; height: 700px">
	<tr align="center" height="50">
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/board/list.jsp">홈</a> |&nbsp;
		<%	if(isLogin) { %>
			<a href="<%=request.getContextPath()%>/board/login/logout.jsp">로그아웃</a> |&nbsp;
		<%	} else { %>
			<a href="<%=request.getContextPath()%>/board/login/login.jsp">로그인</a> |&nbsp;
		<%	} %>
			<a href="javascript:checkMember()">회원가입</a> |&nbsp;
			<a href="<%=request.getContextPath()%>/member/memberAll.jsp">회원보기</a> |&nbsp;
			<a href="<%=request.getContextPath()%>/member/memberAll.jsp?mode=find">회원찾기</a> |&nbsp;
			<a href="<%=request.getContextPath()%>/myshop/admin/main.jsp">쇼핑몰(관리자)</a> |&nbsp;
			<a href="<%=request.getContextPath()%>/myshop/display/mall.jsp">쇼핑몰</a> |&nbsp;
			<a href="list.jsp">게시판</a> |&nbsp;
			<a href="list2.jsp">자료실</a> |&nbsp;
			<a href="company.jsp">회사소개</a></td> </tr>
		</td>
	</tr>
	<tr align="center">
		<td width="200" valign="top">		
<%		if(isLogin){	%>
			<b>[<%= name %>]님 로그인중</b>
<%		} else {	%>
			<b>로그인 해주세요</b>
<%		}	%>
			<br><br>
			<jsp:include page="/count/count.jsp"/>
		</td>
		<td style="padding: 50px">
</body>
</html>