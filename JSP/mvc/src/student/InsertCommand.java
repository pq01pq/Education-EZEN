package student;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = null, url = null;
		StudentDTO student = new StudentDTO();
		student.setId(req.getParameter("id"));
		student.setName(req.getParameter("name"));
		student.setCname(req.getParameter("cname"));
		StudentDAO dao = new StudentDAO();
		try {
			int res = dao.insertStudent(student);
			if(res > 0) {
				msg = "학생등록 성공. 학생목록 페이지로 이동합니다.";
				url = "list.st";
			} else {
				msg = "학생등록 실패. 홈페이지로 이동합니다.";
				url = "start.st";
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		String nextPage = "message.jsp";
		return nextPage;
	}

}
