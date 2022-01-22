package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardUpdateInputController implements Controller {
	
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView();
		int pageNum = (Integer)req.getSession().getAttribute("pageNum");
		try {
			int num = Integer.parseInt(req.getParameter("num"));
			List<BoardDTO> articles = boardDAO.select(num);
			BoardDTO article = null;
			if(articles.size() > 0) {
				article = articles.get(0);
			}
			mv.addObject("article", article);
			mv.addObject("ip", req.getRemoteAddr());
			mv.setViewName("board/updateForm");
			return mv;
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			mv.addObject("msg", "글 수정 실패 : 잘못된 파라미터");
			mv.addObject("url", "list.board?pageNum=" + pageNum);
		} catch(DataAccessException e) {
			e.printStackTrace();
			mv.addObject("msg", "글 수정 실패 : DB 오류");
			mv.addObject("url", "context.board?num=" + req.getParameter("num"));
		}
		mv.setViewName("message");
		return mv;
	}

}
