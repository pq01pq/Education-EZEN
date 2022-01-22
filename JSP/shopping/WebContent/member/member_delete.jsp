<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String no = request.getParameter("no");
if(no == null || no.trim().equals("")){
	response.sendRedirect("/member/memberAll.jsp");
	return;
}
%>
<jsp:useBean id="memdao" class="my.member.MemberDAO"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="memdao" value="<%= pool %>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
int intNo = Integer.parseInt(no);
if(memdao.delete(intNo) < 1){ %>
<script type="text/javascript">
alert("삭제 실패")
location.href="<%=request.getContextPath()%>/member/memberAll.jsp"
</script>
<% } else { %>
<script type="text/javascript">
alert("삭제 완료")
location.href="<%=request.getContextPath()%>/member/memberAll.jsp"
</script>
<% } %>

</body>
</html>