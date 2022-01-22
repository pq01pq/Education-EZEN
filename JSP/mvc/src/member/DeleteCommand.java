package member;

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
		String no = req.getParameter("no");
		if(no == null || no.trim().equals("")){
			msg = "삭제 실패 : 파라미터 없음";
			url = "search.mem?mode=all";
		} else {
			int intNo = Integer.parseInt(no);
			MemberDAO dao = new MemberDAO();
			try {
				if(dao.delete(intNo) > 0){
					msg = "삭제 완료";
					url = "search.mem?mode=all";
				} else {
					msg = "삭제 실패 : 해당 번호 없음";
					url = "search.mem?mode=all";
				}
			} catch(SQLException e) {
				e.printStackTrace();
				msg = "삭제 실패 : DB 오류";
				url = "search.mem?mode=all";
			}
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "/WEB-INF/jsp/member/message.jsp";
	}

}
