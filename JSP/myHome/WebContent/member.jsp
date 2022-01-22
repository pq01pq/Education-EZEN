<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>  
	<title>회원가입</title>
	<link rel="stylesheet" type="text/css" href="style.css"> <!-- css파일을 적용하겠다 -->
	<script type="text/javascript">
		function check(){
			if(f.id.value == ""){
				alert("아이디 입력")
				f.id.focus()
				return
			}
			if(f.passwd.value == ""){
				alert("비밀번호 입력")
				f.passwd.focus()
				return
			}
			document.f.submit()
		}
	</script>
	</head>
	<body>
<%
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");
String ssn1 = request.getParameter("ssn1");
String ssn2 = request.getParameter("ssn2");

if(name == null || name.trim().equals("") ||
	ssn1 == null || ssn1.trim().equals("") ||
	ssn2 == null || ssn2.trim().equals("")){
	
	response.sendRedirect("/member/memberSsn.jsp");
	return;
}
%>
		<form name="f" method="POST" action="member_input.jsp">
			<table width="600" align="center" class="outline"> <!-- class : css파일의 .outline부분을 적용하겠다 -->
  				<tr>
					<td colspan="2" align=center class="m2">회원가입</td>
 				</tr>
				<tr>
					<td width="150" class="m3">이름</td>
					<td class="m3">
						<input type="text" name="name" class="box" value="<%= name %>" readOnly>
					</td>
				</tr>
				<tr>
					<td width="150" class="m3">아이디</td>
					<td class="m3">
						<input type="text" name="id" class="box">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">비밀번호</td>
					<td class="m3">
						<input type="password" name="passwd" class="box">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">주민번호</td>
					<td class="m3">
						<input type="text" name="ssn1" class="box" value="<%= ssn1 %>" readOnly> -
				<input type="password" name="ssn2" class="box" value="<%= ssn2 %>" readOnly>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">이메일</td>
					<td class="m3">
						<input type="text" name="email" class="box">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">연락처</td>
					<td class="m3">
						<input type="text" name="hp1" class="box" size="3" maxlength="3"> -
						<input type="text" name="hp2" class="box" size="4" maxlength="4"> -
						<input type="text" name="hp3" class="box" size="4" maxlength="4">
					</td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
						<a href="javascript:check()">[전송]</a>
						<a href="#">[취소]</a>
					</td>
  				</tr>
  			</table>
		</form>
	</body>
</html>  