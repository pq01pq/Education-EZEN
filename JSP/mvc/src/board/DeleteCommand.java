package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageNum = (Integer)req.getSession().getAttribute("pageNum");
		String msg = null, url = null;
		try {
			BoardDAO dao = new BoardDAO();
			int num = Integer.parseInt(req.getParameter("num"));
			if(dao.delete(num) > 0) {
				msg = "글 삭제 완료";
				url = "list.board?pageNum=" + pageNum;
			} else {
				msg = "글 삭제 실패 : DB SQL 수행결과 없음";
				url = "list.board?pageNum=" + pageNum;
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "글 삭제 실패 : 잘못된 파라미터";
			url = "list.board?pageNum=" + pageNum;
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "글 삭제 실패 : DB 오류";
			url = "list.board?pageNum=" + pageNum;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
