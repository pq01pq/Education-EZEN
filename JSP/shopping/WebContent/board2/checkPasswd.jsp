<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="top.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkPasswd(passwd){
		if(f.in_passwd.value == passwd){
			return true;
		}
		alert("비밀번호가 틀립니다.")
		return false;
	}
</script>
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String passwd = request.getParameter("passwd");
	
	String type = request.getParameter("type");
	String url = null;
	switch(type){
	case "update":
		url = "updateForm.jsp";
		break;
	case "delete":
		url = "deletePro.jsp";
		break;
	default :
	}
%>
</head>
<body>
<form action="<%= url %>" name="f" method="post" onsubmit="return checkPasswd('<%= passwd %>')">
	<input type="hidden" name="num" value="<%= num %>">
	<label>비밀번호</label> <input type="password" name="in_passwd"><br>
	<input type="submit" value="확인">
	<input type="button" onclick="history.back()" value="취소">
</form>

</body>
</html>
<%@ include file="bottom.jsp" %>