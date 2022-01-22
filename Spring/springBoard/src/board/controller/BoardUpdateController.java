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

public class BoardUpdateController extends AbstractCommandController {
	
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
			if(boardDAO.update(article) > 0) {
				msg = "글 수정 완료";
				url = "context.board?num=" + article.getNum();
			} else {
				msg = "글 수정 실패 : DB SQL 실행결과 없음";
				url = "context.board?num=" + article.getNum();
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "글 수정 실패 : 잘못된 파라미터";
			url = "list.board?pageNum=" + req.getSession().getAttribute("pageNum");
		} catch(DataAccessException e) {
			e.printStackTrace();
			msg = "글 수정 실패 : DB 오류";
			url = "context.board?num=" + req.getParameter("num");
		}
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		return mv;
	}

}
