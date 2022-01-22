<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head><title>표현언어 예제</title></head>
<body>  
	<div align="center">
	<h3>연산자와 내장객체를 사용한 예 : </h3>
	<table border="1" width="600">
		<tr><th width="35%">표현식</th><th width="65%">값</th></tr>
		<tr><td>\${2+3}</td><td>${2+3}</td></tr>
		<tr><td>\${2/3}</td><td>${2/3}</td></tr>
		<tr><td>\${2 div 3}</td><td>${2 div 3}</td></tr>
		<tr><td>\${2 % 3}</td><td>${2 % 3}</td></tr>
		<tr><td>\${2 mod 3}</td><td>${2 mod 3}</td></tr>
		<tr><td>\${2 == 3}</td><td>${2 == 3}</td></tr>
		<tr><td>\${2 eq 3}</td><td>${2 eq 3}</td></tr>
		<tr><td>\${header.host}</td><td>${header.host}</td></tr>
		<tr><td>\${header["host"]}</td><td>${header["host"]}</td></tr>
		<tr><td>\${header["user-agent"]}</td><td>${header["user-agent"]}</td></tr>
	</table>
	</div>
</body>
</html>