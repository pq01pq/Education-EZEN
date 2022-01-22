<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일내용 보기</title>
</head>
<body>
<div align="center">
  <h2>파일 업로드 테스트</h2>
  <h3>전송되는 데이터의 형태를 화면에 그대로 출력하기</h3>
  <hr>
<%
	String type = request.getContentType();
	int len = request.getContentLength();
%>
  ContentType = <%= type %>, len = <%= len %><br>
  <hr>
<xmp>
<%
	ServletInputStream in = request.getInputStream();
	byte[] data = new byte[1024];
	int n = 0;
	while((n = in.read(data)) != -1){
		String str = new String(data, 0, n);
		out.print(str);
	}
	in.close();
%>
</xmp>
</div>
</body>
</html>