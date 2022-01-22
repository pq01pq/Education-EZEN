<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	MultipartRequest mr = null;
	String upPath = config.getServletContext().getRealPath("/test03"); // 루트 이후의 경로명
	out.println(upPath);
	try {
		mr = new MultipartRequest(request, upPath, 10*1024*1024, "utf-8"); // (request, 저장할 위치, 최대 크기, 인코딩)
		// request가 가지고있는 파라미터값만 가져감
		out.println("파일 업로드 성공");
	} catch(IOException e){
		e.printStackTrace();
	}
%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<li>올린이 : <%= mr.getParameter("name") %></li>
<li>파일명 : <%= mr.getFilesystemName("filename") %></li>
<li>컨텐트타입 : <%= mr.getContentType("filename") %></li>
<li>파일 크기 :&nbsp;
<%
	File file = mr.getFile("filename");
	long filesize = 0L;
	if(file != null){
		filesize = file.length();
	}
%>
<%= filesize %> bytes</li>
</body>
</html>
