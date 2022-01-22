<%@page import="my.shop.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function checkBlank(){
	if(f.pname.value == ""){
		alert("상품명 입력")
		f.pname.focus()
		return false
	}
	if(f.pcode.value == ""){
		alert("상품명 입력")
		f.pcode.focus()
		return false
	}
	return true
}
</script>
<jsp:useBean id="categoryDAO" class="my.shop.CategoryBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="categoryDAO" value="<%= pool %>"/>
<%
	List<CategoryDTO> categories = categoryDAO.searchAll();
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center" style="height: 400px">
  <form name="f" action="prod_input_ok.jsp" method="post" enctype="multipart/form-data" onsubmit="return checkBlank()">
    <table border="1">
      <tr align="center">
        <td>카테고리</td>
        <td>
          <select name="ccode">
<%		for(CategoryDTO category : categories){ %>
			<option value="<%= category.getCode() %>"><%= category.getCname() %> [<%= category.getCode() %>]</option>
<%		} %>
          </select>
        </td>
      </tr>
      <tr align="center"> <td>상품명</td> <td><input type="text" name="pname"></td> </tr>
      <tr align="center"> <td>상품코드</td> <td><input type="text" name="pcode"></td> </tr>
      <tr align="center"> <td>제조회사</td> <td><input type="text" name="pcompany"></td> </tr>
      <tr align="center"> <td>상품이미지</td> <td><input type="file" name="pimage"></td> </tr>
      <tr align="center"> <td>수량</td> <td><input type="text" name="pqty"></td> </tr>
      <tr align="center"> <td>가격</td> <td><input type="text" name="price"></td> </tr>
      <tr align="center">
        <td>스펙</td>
        <td>
          <select name="pspec">
            <option value="normal">NORMAL</option>
            <option value="hit">HIT</option>
            <option value="best">BEST</option>
            <option value="new">NEW</option>
          </select>
        </td>
      </tr>
      <tr align="center">
        <td>상품 소개</td>
        <td>
          <textarea name="pcontents" rows="5" cols="30" style="resize: none;"></textarea>
        </td>
      </tr>
      <tr align="center"> <td>포인트</td> <td><input type="text" name="point"></td> </tr>
      <tr align="center">
        <td colspan="2">
          <input type="submit" value="입력">
          <input type="reset" value="취소">
        </td>
      </tr>
    </table>   
  </form>
</div>
</body>
</html>
<%@ include file="bottom.jsp" %>