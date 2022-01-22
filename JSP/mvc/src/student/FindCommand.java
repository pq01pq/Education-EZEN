package student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<StudentDTO> students = null;
		StudentDAO dao = new StudentDAO();
		String name = req.getParameter("name");
		try {
			students = dao.findStudnet(name);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("students", students);
		String nextPage = "/WEB-INF/jsp/student/list.jsp";
		return nextPage;
	}

}
