package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardListController implements Controller {
	
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView("board/list");
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
			
			mv.addObject("totalArticlesNum", totalArticlesNum);
			mv.addObject("numPerPage", numPerPage);
			mv.addObject("totalPagesNum", totalPagesNum);
			session.setAttribute("pageNum", pageNum);
			mv.addObject("startPageNum", startPageNum);
			mv.addObject("endPageNum", endPageNum);
			mv.addObject("articles", articles);
			
		} catch(DataAccessException e) {
			e.printStackTrace();
			mv.addObject("totalArticlesNum", 0);
			mv.addObject("numPerPage", numPerPage);
			mv.addObject("totalPagesNum", 1);
			session.setAttribute("pageNum", 1);
			mv.addObject("startPageNum", 1);
			mv.addObject("endPageNum", 1);
			mv.addObject("articles", 1);
		}
		
		return mv;
	}

}
