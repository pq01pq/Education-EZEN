<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="obj" class="my.scope.Counter" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>result.jsp페이지</h2>
<b>request : obj의 getCount()값 : </b>
<jsp:getProperty property="count" name="obj"/>
<br>
<a href="test.html">처음으로</a>
</body>
</html>