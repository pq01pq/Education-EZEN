package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping("list.board")
	public String list(HttpServletRequest req) {
		HttpSession session = req.getSession();
		int numPerPage = 5;
		try {
			int totalArticlesNum = boardDAO.count();
			
			int totalPagesNum = (totalArticlesNum + numPerPage - 1) / numPerPage;
			if(totalPagesNum < 1) {
				totalPagesNum = 1;
			}
			
			int pageNum;
			try {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			} catch(NullPointerException | NumberFormatException e) {
				e.printStackTrace();
				pageNum = 1;
			}
			
			int startPageNum = pageNum - 5;
			int endPageNum = pageNum + 5;
			if(startPageNum < 1) {
				startPageNum = 1;
			}
			if(endPageNum > totalPagesNum) {
				endPageNum = totalPagesNum;
			}
			
			List<BoardDTO> articles = boardDAO.selectPage(pageNum, numPerPage);
			
			req.setAttribute("totalArticlesNum", totalArticlesNum);
			req.setAttribute("numPerPage", numPerPage);
			req.setAttribute("totalPagesNum", totalPagesNum);
			session.setAttribute("pageNum", pageNum);
			req.setAttribute("startPageNum", startPageNum);
			req.setAttribute("endPageNum", endPageNum);
			req.setAttribute("articles", articles);
			
		} catch(DataAccessException e) {
			e.printStackTrace();
			req.setAttribute("totalArticlesNum", 0);
			req.setAttribute("numPerPage", numPerPage);
			req.setAttribute("totalPagesNum", 1);
			session.setAttribute("pageNum", 1);
			req.setAttribute("startPageNum", 1);
			req.setAttribute("endPageNum", 1);
			req.setAttribute("articles", 1);
		}
		
		return "board/list";
	}
	
	@RequestMapping("context.board")
	public String context(HttpServletRequest req) {
		try {
			String name = (String)req.getSession().getAttribute("name");
			int num = Integer.parseInt(req.getParameter("num"));
			BoardDTO article = boardDAO.select(num).get(0);
			if(!article.getWriter().equals(name)) {
				article.setReadCount(article.getReadCount() + 1);
				boardDAO.update(article);
			}
			req.setAttribute("article", article);
			
			return "board/context";
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 조회 실패 : 잘못된 파라미터");
			req.setAttribute("url", "list.board");
		} catch(DataAccessException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 조회 실패 : DB 오류");
			req.setAttribute("url", "list.board");
		}
		return "message";
	}
	
	@RequestMapping("write.board")
	public String write(HttpServletRequest req) {
		req.setAttribute("ip", req.getRemoteAddr());
		return "board/writeForm";
	}
	
	
}
