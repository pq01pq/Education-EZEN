<%@page import="javax.swing.ListModel"%>
<%@page import="java.util.List"%>
<%@page import="my.member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- memberAll.jsp -->
<%@ include file="../top.jsp" %>
<link rel="stylesheet" type="text/css" href="style.css">
<%
request.setCharacterEncoding("utf-8");
String mode = request.getParameter("mode");
List<MemberDTO> members;
%>
<jsp:useBean id="memdao" class="my.member.MemberDAO"/>
<jsp:setProperty property="*" name="memdao"/>
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application"/>
<jsp:setProperty property="pool" name="memdao" value="<%= pool %>"/>

<body>

<div align="center">
	<hr color="green" width="300">
<%
if(mode == null){
	mode = "all";
}
switch(mode){
case "all":%>
<h2>회 원 목 록 보 기</h2>
<%
	members = memdao.search();
	break;
	
case "find":%>
	<h2>회 원 찾 아 보 기</h2>
	<form name="f" method="post">
		<select name="column" >
			<option value="name">회원명</option>
			<option value="id" >아이디</option>
		</select>
		<input type="text" name="key">
		<input type="submit" value="찾기">
	</form>
<%
	String column = request.getParameter("column");
	String key = request.getParameter("key");
	if(key != null && !key.trim().equals("")){
		members = memdao.search(column, key);
	} else {
		members = null;
	}
	break;
	
default :
	members = null;
}
 %>
	<hr color="green" width="300">
	<table width="100%" class="outline">
		<tr>
			<th class="m3">번호</th>
			<th class="m3">이름</th>
			<th class="m3">아이디</th>
			<th class="m3">이메일</th>
			<th class="m3">전화번호</th>
			<th class="m3">가입일</th>
			<th class="m3">수정 | 삭제</th>
		</tr>
<%
	if(members == null || members.size() == 0){%>
		<tr>
			<td>회원 없음</td>
		</tr>
<%	} else {
		for(MemberDTO member : members){ %>
		<tr>
			<td><%= member.getNo() %></td>
			<td><%= member.getName() %></td>
			<td><%= member.getId() %></td>
			<td><%= member.getEmail() %></td>
			<td><%= member.getAllHp() %></td>
			<td><%= member.getJoindate() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/member_update.jsp?no=<%= member.getNo() %>">수정</a> |&nbsp;
				<a href="<%=request.getContextPath()%>/member_delete.jsp?no=<%= member.getNo() %>">삭제</a>
			</td>
		</tr>
<%		} 
	} %>
	</table>
</div>

</body>
<%@ include file="../bottom.jsp" %>