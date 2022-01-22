<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	List<String> advice = (List)request.getAttribute("list");
	if(advice == null || advice.size() == 0){	%>
		<h2>제공되는 도움말이 없습니다.</h2>
<%	} else {	
		for(String msg : advice){%>
			<h2><%= msg %></h2>
<%		}
	}
%>
</head>
<body>

</body>
</html>