<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../top.jsp"%>
<%
	Cookie[] cookie = request.getCookies();
	String value = null;
	if(cookie != null && cookie.length != 0){
		for(int i = 0; i < cookie.length; i++){
			String savedName = cookie[i].getName();
			if(savedName.equals("saveId")){
				value = cookie[i].getValue();
				break;
			}
		}
	}
%>
<!-- login.jsp-->
<link rel="stylesheet" type="text/css" href="style.css">
<br>
<img src="../img/bottom.gif" width=570 height="40" border="0" alt="">
<br>
<p>
<img src="../img/tm_login.gif" width=100 height="13" border="0" 
	align=center ALT="회원 로그인">
<form name="f" action="login_ok.jsp" method="post">
	<table width="60%" align="center" height="120">
		<tr>
			<td align="right" width="30%">
				<img src="../img/id01.gif" 
				width="28" height="11" border="0" alt="아이디">&nbsp;&nbsp;
			</td>
			<td width="40%">
<%				if(value == null){ %>
					<input type="text" name="id" tabindex="1">
<%				} else{ %>
					<input type="text" name="id" tabindex="1" value="<%= value %>">
<%				} %>
			</td>
			<td rowspan="2" width="30%" valign="middle">
				<a href="javascript:loginCheck()">
					<img src="../img/bt_login.gif" border="0" alt="로그인"  tabindex="3">&nbsp;&nbsp;<br>
				</a>
				<nobr>
<%				if(value == null){ %>
					<input type="checkbox" name="saveId">
<%				} else{ %>
					<input type="checkbox" name="saveId" checked>
<%				} %>
					<font face="굴림" size="2">아이디 기억하기</font>
				</nobr>
			</td>
		</tr>
		<tr>
			<td align="right">
				<img src="../img/pwd.gif" 
							width="37" height="11" alt="비밀번호">
			</td>
			<td>
				<input type="password" name="passwd"  tabindex="2">
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<a href="javascript:checkMember()">
					<img src="../img/bt_join.gif" width="60" height="22" alt="회원가입">
				</a>
				<a href="javascript:checkMember('findId')">
 					<img src="../img/bt_search_id.gif" width="60" height="22" alt="아이디 찾기">
 				</a>
 				<a href="javascript:checkMember('findPw')">
					<img src="../img/bt_search_pw.gif" width="60" height="22" alt="비밀번호 찾기">
				</a>
								
			</td>
		</tr>
	</table>
</form>
<%@ include file="../bottom.jsp"%>