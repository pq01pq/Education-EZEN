<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function view(pnum){
	window.open("prod_view.admin?pnum=" + pnum, "상세보기", "width=800,height=600")
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
  <table border="1" style="width: 60%">
    <tr align="center"><th>번호</th><th>상품코드</th><th>상품명</th><th>이미지</th><th>가격</th><th>제조사</th><th>수량</th><th></th></tr>
<c:forEach var="product" items="${products}">
	<tr align="center">
	  <td>${product.pnum}</td><td>${product.pcategory_fk}</td>
	  <td><a href="javascript:view(${product.pnum})">${product.pname}</a></td>
	  <td>
	    <a href="javascript:view(${product.pnum})">
	    
	      <img width="50px" height="50px" src="${root}/myshop/file/${product.pimage}">
	    </a>
	  </td>
	  <td>${product.price}</td><td>${product.pcompany}</td><td>${product.pqty}</td>
	  <td>
	    <a href="prod_update_input.admin?pnum=${product.pnum}">수정</a> |&nbsp;
	    <a href="prod_delete.admin?pnum=${product.pnum}">삭제</a>
	  </td>
	</tr>
</c:forEach>
  </table>
</div>
</body>
</html>
<%@ include file="bottom.jsp" %>