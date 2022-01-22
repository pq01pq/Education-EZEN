package student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<StudentDTO> students = null;
		StudentDAO dao = new StudentDAO();
		try {
			students = dao.listStudent();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("students", students);
		String nextPage = "/WEB-INF/jsp/student/list.jsp";
		return nextPage;
	}

}
