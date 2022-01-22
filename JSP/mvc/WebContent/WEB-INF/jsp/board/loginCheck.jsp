<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${sessionScope.id == null || sessionScope.name == null}">
	<script type="text/javascript">
		alert("로그인 먼저 해주세요")
		location.href="login.mem?source=board"
	</script>
</c:if>
</head>
<body>

</body>
</html>