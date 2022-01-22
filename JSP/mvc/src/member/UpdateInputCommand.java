package member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateInputCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		try {
			int no = Integer.parseInt(req.getParameter("no"));
			MemberDAO dao = new MemberDAO();
			MemberDTO member = dao.selectNo(no);
			req.setAttribute("member", member);
			
			return "WEB-INF/jsp/member/member_update.jsp?no=" + no;
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("msg", "잘못된 파라미터");
			req.setAttribute("url", "start.mem");
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "DB 오류");
			req.setAttribute("url", "start.mem");
		}
		return "message.jsp";
	}

}
