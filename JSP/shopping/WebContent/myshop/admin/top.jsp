<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>111번가</title>
</head>
<body>
<div align="center">
  <h1>쇼핑몰(관리자용)</h1><br>
  <h3><a href="<%= request.getContextPath() %>/myshop/display/mall.jsp">쇼핑몰 홈</a> | <a href="main.jsp">메인 홈</a><br></h3>
    <table border="1" style="width: 90%; height: 50px;">
      <tr align="center">
        <td width="20%"><a href="cate_input.jsp">카테고리 등록</a></td>
        <td width="20%"><a href="cate_list.jsp">카테고리 목록</a></td>
        <td width="20%"><a href="prod_input.jsp">상품 등록</a></td>
        <td width="20%"><a href="prod_list.jsp">상품 목록</a></td>
      </tr>
    </table>
</div>
</body>
</html>