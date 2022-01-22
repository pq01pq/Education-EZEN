package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardContextController implements Controller {
	
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			String name = (String)req.getSession().getAttribute("name");
			int num = Integer.parseInt(req.getParameter("num"));
			BoardDTO article = boardDAO.select(num).get(0);
			if(!article.getWriter().equals(name)) {
				article.setReadCount(article.getReadCount() + 1);
				boardDAO.update(article);
			}
			mv.addObject("article", article);
			mv.setViewName("board/context");
			
			return mv;
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			mv.addObject("msg", "글 조회 실패 : 잘못된 파라미터");
			mv.addObject("url", "list.board");
		} catch(DataAccessException e) {
			e.printStackTrace();
			mv.addObject("msg", "글 조회 실패 : DB 오류");
			mv.addObject("url", "list.board");
		}
		mv.setViewName("message");
		return mv;
	}

}
