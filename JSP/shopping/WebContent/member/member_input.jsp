<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="memdao" class="my.member.MemberDAO"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="memdao" value="<%= pool %>"/>
<jsp:useBean id="memdto" class="my.member.MemberDTO"/>
<jsp:setProperty property="*" name="memdto"/>
<%
if(memdto.getName() == null || memdto.getName().trim().equals("")){
	response.sendRedirect("/member/memberSsn.jsp");
	return;
}

if(memdao.insert(memdto) < 1){ %>
<script type="text/javascript">
alert("회원가입 실패")
self.close()
</script>
<% } else { %>
<script type="text/javascript">
alert("회원가입 성공")
location.href="/member/memberSsn.jsp"
</script>
<% } %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title></title>
</head>
<body>
</body>
</html>