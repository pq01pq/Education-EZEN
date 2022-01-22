<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>111번가_상세보기_${product.pname}</title>
</head>
<body>
<div align="center">
  <table border="1">
    <tr align="center">
      <th>카테고리</th><td>${category.cname} [${category.code}]</td>
      <th>번호</th><td>${product.pnum}</td>
    </tr>
    <tr align="center">
      <th>상품명</th><td>${product.pname} [${product.pcategory_fk}]</td>
      <th>제조회사</th><td>${product.pcompany}</td>
    </tr>
    <tr align="center">
      <th>상품이미지</th>
        <td colspan="3">
          <img src="${root}/myshop/file/${product.pimage}">
        </td>
    </tr>
    <tr align="center">
      <th>수량</th><td>${product.pqty}</td>
      <th>가격</th><td>${product.price}</td>
    </tr>
    <tr align="center">
      <th>스펙</th><td>${product.pspec}</td>
      <th>포인트</th><td>${product.point}</td>
    </tr>
    <tr align="center">
      <th>상품설명</th>
      <td colspan="3">
        <textarea rows="5" cols="30" style="resize: none">${product.pcontents}</textarea>
      </td>
    </tr>
  </table>
</div>
</body>
</html>