package board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateInputCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageNum = (Integer)req.getSession().getAttribute("pageNum");
		try {
			BoardDAO dao = new BoardDAO();
			int num = Integer.parseInt(req.getParameter("num"));
			List<BoardDTO> articles = dao.select(num);
			BoardDTO article = null;
			if(articles.size() > 0) {
				article = articles.get(0);
			}
			req.setAttribute("article", article);
			
			return "WEB-INF/jsp/board/updateForm.jsp";
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 수정 실패 : 잘못된 파라미터");
			req.setAttribute("url", "list.board?pageNum=" + pageNum);
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 수정 실패 : DB 오류");
			req.setAttribute("url", "context.board?num=" + req.getParameter("num"));
		}
		
		return "message.jsp";
	}

}
