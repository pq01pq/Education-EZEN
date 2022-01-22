package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			BoardDAO dao = new BoardDAO();
			String name = (String)req.getSession().getAttribute("name");
			int num = Integer.parseInt(req.getParameter("num"));
			BoardDTO article = dao.select(num).get(0);
			if(!article.getWriter().equals(name)) {
				article.setReadCount(article.getReadCount() + 1);
				dao.update(article);
			}
			req.setAttribute("article", article);
			
			return "WEB-INF/jsp/board/context.jsp";
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 조회 실패 : 잘못된 파라미터");
			req.setAttribute("url", "list.board");
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 조회 실패 : DB 오류");
			req.setAttribute("url", "list.board");
		}
		return "message.jsp";
	}

}
