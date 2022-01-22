package member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		String msg = null, url = null;
		MemberDTO member = new MemberDTO();
		try {
			member.setNo(Integer.parseInt(req.getParameter("no")));
			member.setPasswd(req.getParameter("passwd"));
			member.setEmail(req.getParameter("email"));
			member.setHp1(req.getParameter("hp1"));
			member.setHp2(req.getParameter("hp2"));
			member.setHp3(req.getParameter("hp3"));
			MemberDAO dao = new MemberDAO();
			if(dao.update(member) > 0) {
				msg = "수정 완료";
				url = "start.mem";
			} else {
				msg = "수정 실패 : 회원 없음";
				url = "start.mem";
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "잘못된 파라미터";
			url = "start.mem";
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "DB 오류";
			url = "start.mem";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "message.jsp";
	}

}
