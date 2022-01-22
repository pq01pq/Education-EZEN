<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
<div align="center">
  <form name="f" action="fileUpload_ok2.jsp" method="post" enctype="multipart/form-data">
    <table border="1">
      <caption>파일 업로드 테스트</caption>
      <tr> <th>올린이</th> <td><input type="text" name="name"></td> </tr>
      <tr> <th>파일명</th> <td><input type="file" name="filename"></td> <tr>
      <tr> <td colspan="2"><input type="submit" value="업로드">
                           <input type="reset" value="취소"></td> <tr>
    </table>
  </form>
</div>
</body>
</html>