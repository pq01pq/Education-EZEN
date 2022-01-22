<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../top.jsp"%>
<%
	String savedId = (String)request.getAttribute("savedId");
%>
<!-- login.jsp-->
<link rel="stylesheet" type="text/css" href="style.css">
<br>
<img src="${root}/img/bottom.gif" width=570 height="40" border="0" alt="">
<br>
<p>
<img src="${root}/img/tm_login.gif" width=100 height="13" border="0" 
	align=center ALT="회원 로그인">
<form name="f" action="loginOk.mem" method="post">
	<table width="60%" align="center" height="120">
		<tr>
			<td align="right" width="30%">
				<img src="${root}/img/id01.gif" 
				width="28" height="11" border="0" alt="아이디">&nbsp;&nbsp;
			</td>
			<td width="40%">
			<c:choose>
				<c:when test="${savedId == null}">
					<input type="text" name="id" tabindex="1">
				</c:when>
				<c:otherwise>
					<input type="text" name="id" tabindex="1" value="<%= savedId %>">
				</c:otherwise>
			</c:choose>
			</td>
			<td rowspan="2" width="30%" valign="middle">
				<a href="javascript:loginCheck()">
					<img src="${root}/img/bt_login.gif" border="0" alt="로그인"  tabindex="3">&nbsp;&nbsp;<br>
				</a>
				<nobr>
				<c:choose>
				<c:when test="${savedId == null}">
					<input type="checkbox" name="saveId">
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="saveId" checked>
				</c:otherwise>
			</c:choose>
					<font face="굴림" size="2">아이디 기억하기</font>
				</nobr>
			</td>
		</tr>
		<tr>
			<td align="right">
				<img src="${root}/img/pwd.gif" 
							width="37" height="11" alt="비밀번호">
			</td>
			<td>
				<input type="password" name="passwd"  tabindex="2">
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<a href="javascript:checkMember()">
					<img src="${root}/img/bt_join.gif" width="60" height="22" alt="회원가입">
				</a>
				<a href="javascript:checkMember('findId')">
 					<img src="${root}/img/bt_search_id.gif" width="60" height="22" alt="아이디 찾기">
 				</a>
 				<a href="javascript:checkMember('findPw')">
					<img src="${root}/img/bt_search_pw.gif" width="60" height="22" alt="비밀번호 찾기">
				</a>
								
			</td>
		</tr>
	</table>
</form>
<%@ include file="../bottom.jsp"%>