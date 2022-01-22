package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardInsertController extends AbstractCommandController {
	
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp,
			Object dto, BindException be) throws Exception {
		ModelAndView mv = new ModelAndView("message");
		BoardDTO article = (BoardDTO)dto;
		String msg, url;
		try {
			if(req.getParameter("parentNum") == null) {
				article.setParentNum(-1);
				article.setReGroup(boardDAO.maxGroup() + 1);
			}
			
			if(boardDAO.insert(article) > 0) {
				msg = "글 등록 완료";
				url = "list.board";
			} else {
				msg = "글 등록 실패 : DB SQL 실행결과 없음";
				url = "list.board";
			}
		} catch(DataAccessException e) {
			e.printStackTrace();
			msg = "글 등록 실패 : DB 오류";
			url = "list.board";
		}
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		return mv;
	}

}
