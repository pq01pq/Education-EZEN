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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<form name="f" action="prod_update.admin" method="post" enctype="multipart/form-data" onsubmit="return checkBlank()">
		<table border="1">
			<tr>
				<th>카테고리</th>
				<td>
					<input type="text" placeholder="${product.pcategory_fk}" readonly>
					<input type="hidden" name="pcategory_fk" value="${product.pcategory_fk}">
				</td>
			</tr>
			<tr>
				<th>상품번호</th>
				<td>${product.pnum}<input type="hidden" name="pnum" value="${product.pnum}"></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="pname" value="${product.pname}>"></td>
			</tr>
			<tr>
				<th>제조회사</th>
				<td><input type="text" name="pcompany" value="${product.pcompany}"></td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td>
					<div id="image_container">
						<img src="${root}/myshop/file/${product.pimage}">
					</div>
					<input type="file" name="pimage" onchange="setImg(event)">
					<input type="hidden" name="preImage" value="${product.pimage}">
				</td>
			</tr>
			<tr>
				<th>수량</th>
				<td><input type="text" name="pqty" value="${product.pqty}"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" value="${product.price}"></td>
			</tr>
			<tr>
				<th>스펙</th>
				<td>
					<select name="pspec" >
				<c:forEach var="spec" items="${specs}">
					<c:choose>
						<c:when test="${spec.toUpperCase() == product.pspec.toUpperCase()}">
							<option value="${spec.toLowerCase()}" selected>${spec.toUpperCase()}</option>
						</c:when>
						<c:otherwise>
							<option value="${spec.toLowerCase()}">${spec.toUpperCase()}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품 설명</th>
				<td>
					<textarea name="pcontents" rows="5" cols="30" style="resize: none;">${product.pcontents}</textarea>
				</td>
			</tr>
			<tr>
				<th>포인트</th>
				<td><input type="text" name="point" value="${product.point}"></td>
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