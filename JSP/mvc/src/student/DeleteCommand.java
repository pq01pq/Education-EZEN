package student;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		String name = req.getParameter("name");
		StudentDAO dao = new StudentDAO();
		try {
			int res = dao.deleteStudent(name);
			if(res > 0) {
				msg = "학생삭제 성공. 학생목록 페이지로 이동합니다.";
				url = "list.st";
			} else {
				msg = "학생삭제 실패. 홈페이지로 이동합니다.";
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
