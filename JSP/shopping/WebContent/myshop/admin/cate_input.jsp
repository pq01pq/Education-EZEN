<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function checkBlank(){
	if(f.code.value == ""){
		alert("코드 입력")
		f.code.focus()
		return false
	}
	if(f.cname.value == ""){
		alert("카테고리명 입력")
		f.cname.focus()
		return false
	}
	return true
}
</script>
<title>상품 등록</title>
</head>
<body>
<div align="center" style="height: 400px">
  <form name="f" action="cate_input_ok.jsp" method="post" onsubmit="return checkBlank()">
    <table border="1">
      <tr align="center"> <td>코드</td> <td><input type="text" name="code"></td> </tr>
      <tr align="center"> <td>카테고리명</td> <td><input type="text" name="cname"></td> </tr>
      <tr align="center"> <td colspan="2">
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