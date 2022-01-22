package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;

public class BoardDeleteController implements Controller {

	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView("message");
		int pageNum = (Integer)req.getSession().getAttribute("pageNum");
		String msg, url;
		try {
			int num = Integer.parseInt(req.getParameter("num"));
			if(boardDAO.delete(num) > 0) {
				msg = "글 삭제 완료";
				url = "list.board?pageNum=" + pageNum;
			} else {
				msg = "글 삭제 실패 : DB SQL 수행결과 없음";
				url = "list.board?pageNum=" + pageNum;
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "글 삭제 실패 : 잘못된 파라미터";
			url = "list.board?pageNum=" + pageNum;
		} catch(DataAccessException e) {
			e.printStackTrace();
			msg = "글 삭제 실패 : DB 오류";
			url = "list.board?pageNum=" + pageNum;
		}
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		return mv;
	}

}
