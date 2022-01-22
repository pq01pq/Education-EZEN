package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			BoardDTO article = new BoardDTO();
			BoardDAO dao = new BoardDAO();
			article.setWriter(req.getParameter("writer"));
			article.setSubject(req.getParameter("subject"));
			article.setEmail(req.getParameter("email"));
			article.setContent(req.getParameter("content"));
			article.setPasswd(req.getParameter("passwd"));
			article.setIp(req.getParameter("ip"));
			int parentNum, reGroup, reStep, reLevel;
			try {
				// 답글
				parentNum = Integer.parseInt(req.getParameter("parent"));
				reGroup = Integer.parseInt(req.getParameter("parentReGroup"));
				reStep = Integer.parseInt(req.getParameter("parentReStep")) + 1;
				reLevel = Integer.parseInt(req.getParameter("parentReLevel")) + 1;
			} catch(NullPointerException | NumberFormatException e) {
				// 새글
				parentNum = -1;
				reGroup = dao.maxGroup() + 1;
				reStep = 0;
				reLevel = 0;
			}
			article.setParentNum(parentNum);
			article.setReGroup(reGroup);
			article.setReStep(reStep);
			article.setReLevel(reLevel);
			
			if(dao.insert(article) > 0) {
				msg = "글 등록 완료";
				url = "list.board";
			} else {
				msg = "글 등록 실패 : DB SQL 실행결과 없음";
				url = "list.board";
			}
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "글 등록 실패 : DB 오류";
			url = "list.board";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
