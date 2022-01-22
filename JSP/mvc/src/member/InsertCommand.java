package member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String source = (String)req.getSession().getAttribute("source");
		String msg = null, url = null;
		MemberDTO member = new MemberDTO();
		member.setName(req.getParameter("name"));
		member.setId(req.getParameter("id"));
		member.setPasswd(req.getParameter("passwd"));
		member.setSsn1(req.getParameter("ssn1"));
		member.setSsn2(req.getParameter("ssn2"));
		member.setEmail(req.getParameter("email"));
		member.setHp1(req.getParameter("hp1"));
		member.setHp2(req.getParameter("hp2"));
		member.setHp3(req.getParameter("hp3"));
		MemberDAO dao = new MemberDAO();
		try {
			if(dao.insert(member) > 0) {
				msg = "입력 완료";
			} else {
				msg = "입력 실패 : DB 오류";
			}
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "입력 실패 : DB 오류";
		}
		
		if(source != null) {
			url = "start." + source;
		} else {
			url = "search.mem?mode=all";
		}
		
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
