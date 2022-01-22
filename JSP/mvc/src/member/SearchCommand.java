package member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String mode = req.getParameter("mode");
		String column = req.getParameter("column");
		String key = req.getParameter("key");
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> members = null;
		try {
			if(mode != null) {
				switch(mode) {
				case "all":{
					members = dao.searchAll();
					break;
				}
				case "find":{
					members = dao.search(column, key);
					break;
				}
				default :{
					req.setAttribute("mode", "all");
					members = dao.searchAll();
				}
				}
			} else {
				req.setAttribute("mode", "all");
				members = dao.searchAll();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("members", members);
		return "WEB-INF/jsp/member/memberAll.jsp";
	}

}
