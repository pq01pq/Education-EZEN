<%@page import="java.io.File"%>
<%@page import="my.shop.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function checkBlank(){
	if(f.pname.value == ""){
		alert("상품명 필수")
		f.pname.focus()
		return false
	}
	return true;
}

function setImg(event) {
    for (var image of event.target.files) {
        var reader = new FileReader();
        reader.onload = function(event) {
            var img = document.createElement("img");
            img.setAttribute("src", event.target.result);
            document.querySelector("div#image_container").innerHTML = '';  // 앞서 선택한 이미지 삭제
            document.querySelector("div#image_container").appendChild(img);  //새로 선택한 이미지 div에 출력
         };
         console.log(image);
         reader.readAsDataURL(image);
    }
}
</script>
<jsp:useBean id="productDAO" class="my.shop.ProductBean"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="productDAO" value="<%= pool %>"/>
<%
	int pnum = Integer.parseInt(request.getParameter("pnum"));
	ProductDTO product = productDAO.select(pnum).get(0);
	String[] spec = new String[] {"normal", "hit", "best", "new"};
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<form name="f" action="prod_update_ok.jsp" method="post" enctype="multipart/form-data" onsubmit="return checkBlank()">
		<table border="1">
			<tr>
				<th>카테고리</th>
				<td>
					<input type="text" placeholder="<%= product.getPcategory_fk() %>" readonly>
					<input type="hidden" name="pcategory_fk" value="<%= product.getPcategory_fk() %>">
				</td>
			</tr>
			<tr>
				<th>상품번호</th>
				<td><%= product.getPnum() %><input type="hidden" name="pnum" value="<%= product.getPnum() %>"></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="pname" value="<%= product.getPname() %>"></td>
			</tr>
			<tr>
				<th>제조회사</th>
				<td><input type="text" name="pcompany" value="<%= product.getPcompany() %>"></td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td>
					<div id="image_container">
						<img src="<%= request.getContextPath() %>/myshop/file/<%= product.getPimage() %>">
					</div>
					<input type="file" name="pimage" onchange="setImg(event)">
					<input type="hidden" name="preImage" value="<%= product.getPimage() %>">
				</td>
			</tr>
			<tr>
				<th>수량</th>
				<td><input type="text" name="pqty" value="<%= product.getPqty() %>"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" value="<%= product.getPrice() %>"></td>
			</tr>
			<tr>
				<th>스펙</th>
				<td>
					<select name="pspec" >
			<%	for(int i = 0; i < spec.length; i++){
					if(spec[i].toUpperCase().equals(product.getPspec().toUpperCase())){ %>
						<option value="<%= spec[i].toLowerCase() %>" selected><%= spec[i].toUpperCase() %></option>
			<%		} else { %>
						<option value="<%= spec[i].toLowerCase() %>"><%= spec[i].toUpperCase() %></option>
			<%		}
				} %>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품 설명</th>
				<td>
					<textarea name="pcontents" rows="5" cols="30" style="resize: none;"><%= product.getPcontents() %></textarea>
				</td>
			</tr>
			<tr>
				<th>포인트</th>
				<td><input type="text" name="point" value="<%= product.getPoint() %>"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
<%@ include file="bottom.jsp" %>