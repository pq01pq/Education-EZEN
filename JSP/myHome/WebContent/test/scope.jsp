<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="obj" class="my.scope.Counter" scope="request"/>
<jsp:setProperty name="obj" property="*"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Page Scope</h2>
	<b>obj의 getCount() 호출 값 : </b>
	<jsp:getProperty property="count" name="obj"/>
	<br>
	<jsp:forward page="result.jsp"/>
</body>
</html>