package board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		int numPerPage = 5;
		try {
			BoardDAO dao = new BoardDAO();
			
			int totalArticlesNum = dao.count();
			
			int totalPagesNum = (totalArticlesNum + numPerPage - 1) / numPerPage;
			if(totalPagesNum < 1) {
				totalPagesNum = 1;
			}
			
			int pageNum = 1;
			String pageNumStr = req.getParameter("pageNum");
			if(pageNumStr != null && !pageNumStr.trim().equals("")){
				try {
					pageNum = Integer.parseInt(pageNumStr);
				} catch(NumberFormatException e) {
					e.printStackTrace();
					pageNum = 1;
				}
			}
			
			int startPageNum = pageNum - 5;
			int endPageNum = pageNum + 5;
			if(startPageNum < 1) {
				startPageNum = 1;
			}
			if(endPageNum > totalPagesNum) {
				endPageNum = totalPagesNum;
			}
			
			List<BoardDTO> articles = dao.selectPage(pageNum, numPerPage);
			
			req.setAttribute("totalArticlesNum", totalArticlesNum);
			req.setAttribute("numPerPage", numPerPage);
			req.setAttribute("totalPagesNum", totalPagesNum);
			session.setAttribute("pageNum", pageNum);
			req.setAttribute("startPageNum", startPageNum);
			req.setAttribute("endPageNum", endPageNum);
			req.setAttribute("articles", articles);
			
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("totalArticlesNum", 0);
			req.setAttribute("numPerPage", numPerPage);
			req.setAttribute("totalPagesNum", 1);
			session.setAttribute("pageNum", 1);
			req.setAttribute("startPageNum", 1);
			req.setAttribute("endPageNum", 1);
			req.setAttribute("articles", 1);
		}
		
		return "WEB-INF/jsp/board/list.jsp";
	}

}
