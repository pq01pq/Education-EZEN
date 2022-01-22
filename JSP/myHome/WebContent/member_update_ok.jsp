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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(memdao.update(memdto) < 1){%>
<script type="text/javascript">
alert("수정 실패")
location.href="<%=request.getContextPath()%>/member/memberAll.jsp"
</script>
<% } else { %>
<script type="text/javascript">
alert("수정 완료")
location.href="<%=request.getContextPath()%>/member/memberAll.jsp"
</script>
<% } %>
</body>
</html>