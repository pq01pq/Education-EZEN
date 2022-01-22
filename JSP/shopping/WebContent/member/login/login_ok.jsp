<%@page import="my.login.LoginCheck"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="loginCheck" class="my.login.LoginCheck"/>
<jsp:setProperty property="*" name="loginCheck"/>
<jsp:useBean id="loginOk" class="my.login.LoginOkBean" scope="session"/>
<%
	if(loginCheck.getId() == null || loginCheck.getId().trim().equals("")){
		response.sendRedirect("login.jsp");
		return;
	}

	String saveId = request.getParameter("saveId");
	System.out.println(saveId == null ? "null" : saveId); // 콘솔에서 checkbox값 확인
	
	int res = loginCheck.loginCheck();
	String msg = null, url = null;
	switch(res){
	case LoginCheck.OK:
		loginOk.setId(loginCheck.getId());
		boolean isLogin = loginOk.isSetting();
		session.setAttribute("id", loginOk.getId());
		session.setAttribute("name", loginOk.getName());
		
		Cookie cookie = new Cookie("saveId", loginOk.getId());
		if(saveId == null){
			cookie.setMaxAge(0);
		} else {
			cookie.setMaxAge(24*60*60);
		}
		response.addCookie(cookie);
		
		msg = "로그인되었습니다.";
		url = request.getContextPath() + "/index.jsp";
		break;
	case LoginCheck.NOT_ID:
		msg = "아이디가 없습니다. 다시 확인하고 입력해주세요.";
		url = "login.jsp";
		break;
	case LoginCheck.NOT_PW:
		msg = "비밀번호가 틀렸습니다. 다시 확인하고 입력해주세요.";
		url = "login.jsp";
		break;
	case LoginCheck.ERROR:
		msg = "DB서버 오류 발생. 관리자 문의";
		url = request.getContextPath() + "/index.jsp";
		break;
	default :
		msg = "??";
		url = request.getContextPath() + "/index.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	alert("<%= msg %>")
	location.href="<%= url %>"
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>