<%@page import="java.util.List"%>
<%@page import="my.member.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memdao" class="my.member.MemberDAO"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="memdao" value="<%= pool %>"/>
<%
request.setCharacterEncoding("utf-8");
String option = request.getParameter("option");
String name = request.getParameter("name");
String ssn1 = request.getParameter("ssn1");
String ssn2 = request.getParameter("ssn2");
String id = request.getParameter("id");

if(name == null || name.trim().equals("") ||
	ssn1 == null || ssn1.trim().equals("") ||
	ssn2 == null || ssn2.trim().equals("")){
	
	response.sendRedirect("memberSsn.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%

if(option != null){
	switch(option){
	case "findId": {
		// id == null;
		List<MemberDTO> members1 = memdao.select("ssn1", ssn1);
		List<MemberDTO> members2 = memdao.select("ssn2", ssn2);
		MemberDTO foundMember = null;
		for(MemberDTO member1 : members1){
			for(MemberDTO member2 : members2){
				if(member1.getAllSsn().equals(member2.getAllSsn())){
					foundMember = member1;
				}
			}
		}
		
		if(foundMember.getName().equals(name)){
			id = foundMember.getId();
		}
		
		if(id != null){
%>
	<script type="text/javascript">
		alert("회원님의 아이디는 <%= id %>입니다.")
		self.close()
	</script>
<%		
		}
		else { %>
		<script type="text/javascript">
			alert("일치하는 회원정보가 없습니다.")
			self.close()
		</script>
<%		}
		break;
	}
	case "findPw": {
		List<MemberDTO> members1 = memdao.select("ssn1", ssn1);
		List<MemberDTO> members2 = memdao.select("ssn2", ssn2);
		MemberDTO foundMember = null;
		for(MemberDTO member1 : members1){
			for(MemberDTO member2 : members2){
				if(member1.getAllSsn().equals(member2.getAllSsn())){
					foundMember = member1;
				}
			}
		}
		
		String passwd = null;
		if(foundMember.getName().equals(name) && foundMember.getId().equals(id)){
			passwd = foundMember.getPasswd();
		}
		
		if(passwd != null){
	%>
	<script type="text/javascript">
		alert("회원님의 비밀번호는 <%= passwd %>입니다.")
		self.close()
	</script>
<%		} else { %>
	<script type="text/javascript">
		alert("일치하는 회원정보가 없습니다.")
		self.close()
	</script>
<%		}
		break;
	}
	default :
		
	}
} else {
	if(memdao.checkSsn(ssn1, ssn2)){ %>
	<script type="text/javascript">
		alert("존재하는 주민번호. 로그인 ㄱㄱ")
		self.close()
	</script>
	<% } else { %>
	<form name="f" action="../member.jsp" method="post">
		<input type="hidden" name="name" value="<%= name %>"/>
		<input type="hidden" name="ssn1" value="<%= ssn1 %>"/>
		<input type="hidden" name="ssn2" value="<%= ssn2 %>"/>
	</form>
	<script type="text/javascript">
		alert("회원가입 페이지 이동")
		document.f.submit()
	</script>
<%	}
} %>
</body>
</html>