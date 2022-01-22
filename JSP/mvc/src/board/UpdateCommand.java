package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			BoardDAO dao = new BoardDAO();
			BoardDTO article = new BoardDTO();
			int num = Integer.parseInt(req.getParameter("num"));
			article.setNum(num);
			article.setSubject(req.getParameter("subject"));
			article.setEmail(req.getParameter("email"));
			article.setPasswd(req.getParameter("passwd"));
			article.setReadCount(Integer.parseInt(req.getParameter("readCount")));
			article.setContent(req.getParameter("content"));
			article.setIp(req.getParameter("ip"));
			
			if(dao.update(article) > 0) {
				msg = "글 수정 완료";
				url = "context.board?num=" + num;
			} else {
				msg = "글 수정 실패 : DB SQL 실행결과 없음";
				url = "context.board?num=" + num;
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "글 수정 실패 : 잘못된 파라미터";
			url = "list.board?pageNum=" + req.getSession().getAttribute("pageNum");
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "글 수정 실패 : DB 오류";
			url = "context.board?num=" + req.getParameter("num");
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
