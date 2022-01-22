<%@page import="my.member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String no = request.getParameter("no");
if(no == null || no.trim().equals("")){
	response.sendRedirect("/member/memberAll.jsp");
	return;
}
%>
<jsp:useBean id="memdao" class="my.member.MemberDAO" />
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="memdao" value="<%= pool %>"/>
<%
MemberDTO dto = memdao.selectNo(Integer.parseInt(no));
%>
<!DOCTYPE html>
<%@ include file="top.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function check(){
	if(f.passwd.value==""){
		alert("비번입력")
		f.passwd.focus()
		return
	}
	document.f.submit()
}
</script>
</head>
<body>
	<div align="center">
		<form name="f" method="POST" action="member_update_ok.jsp">
			<input type="hidden" name="no" value="<%= dto.getNo() %>">
			<table width="600" align="center" class="outline">
				<!-- class : css파일의 .outline부분을 적용하겠다 -->
				<tr>
					<td colspan="2" align=center class="m2">회원가입</td>
				</tr>
				<tr>
					<td width="150" class="m3">이름</td>
					<td class="m3"><input type="text" name="name" class="box"
						value="<%= dto.getName() %>" readOnly></td>
				</tr>
				<tr>
					<td width="150" class="m3">아이디</td>
					<td class="m3"><input type="text" name="id" class="box"
						value="<%= dto.getId() %>" readOnly></td>
				</tr>
				<tr>
					<td width="150" class="m3">비밀번호</td>
					<td class="m3"><input type="password" name="passwd"
						class="box"></td>
				</tr>
				<tr>
					<td width="150" class="m3">주민번호</td>
					<td class="m3"><input type="text" name="ssn1" class="box"
						value="<%= dto.getSsn1() %>" readOnly> - <input
						type="password" name="ssn2" class="box"
						value="<%= dto.getSsn2() %>" readOnly></td>
				</tr>
				<tr>
					<td width="150" class="m3">이메일</td>
					<td class="m3"><input type="text" name="email" class="box"
						value="<%= dto.getEmail() %>"></td>
				</tr>
				<tr>
					<td width="150" class="m3">연락처</td>
					<td class="m3"><input type="text" name="hp1" class="box"
						size="3" maxlength="3" value="<%= dto.getHp1() %>"> - <input
						type="text" name="hp2" class="box" size="4" maxlength="4"
						value="<%= dto.getHp2() %>"> - <input type="text"
						name="hp3" class="box" size="4" maxlength="4"
						value="<%= dto.getHp3() %>"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><a href="javascript:check()">[전송]</a>
						<a href="#">[취소]</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<%@ include file="bottom.jsp"%>