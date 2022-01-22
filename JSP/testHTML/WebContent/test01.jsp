<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- test01.jsp -->
<%!
int a = 10;
public void disp(){
	System.out.println(a);
}
%>
<html>
<head>
	<title>JSP 실습</title>
</head>
<body>
<%	for(int i = 0; i < 9; i++){ %>
		<h3>Hello, JSP <%= i + 1 %></h3>
<%	} %>
</body>
</html>
