<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
//1. 넘어온 데이터 받기
request.setCharacterEncoding("utf-8"); // post방식만
%>
<jsp:useBean id="stdao" class="my.student.StudentDAO"/>
<jsp:useBean id="stdto" class="my.student.StudentDTO"/>
<jsp:setProperty property="*" name="stdto"/>
<!-- == my.student.StudentDAO stdao = new my.student.StudentDAO(); -->
<!-- 매개변수가 없는 디폴트 생성자로만 객체 만들 수 있음 -->
<!-- insert.jsp -->
<%
if(stdto.getId() == null || stdto.getName() == null || stdto.getCname() == null ||
	stdto.getId().trim().equals("") || stdto.getName().trim().equals("") || stdto.getCname().trim().equals("")) { %>
<script type="text/javascript">
alert("아이디, 학생명, 학급명을 모두 입력하셈요")
history.back()
</script>
<%
	return;	// _jspService() 메서드 나가라
}

// 4. 실패 유무를 판단하여 페이지 이동시키기

if(stdao.insertStudent(stdto) > 0) { %>
<script type="text/javascript">
alert("학생등록 성공")
location.href="list.jsp"
</script>
<% } else { %>
<script type="text/javascript">
alert("학생등록 실패")
location.href="student.jsp"
</script>
<% }
// 5. DB 닫기
%>