package com.ezen.bigdata3;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.bigdata3.model.BoardDTO;
import com.ezen.bigdata3.service.BoardDAO;
import com.ezen.bigdata3.service.BoardMapper;

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
			} catch(Exception e) {
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
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			int num = Integer.parseInt(req.getParameter("num"));
			String passwd = req.getParameter("passwd");
			
			String type = req.getParameter("type");
			String url = null;
			switch(type){
			case "update":
				url = "update_input.board";
				break;
			case "delete":
				url = "delete.board";
				break;
			default :
			}
			mv.addObject("num", num);
			mv.addObject("passwd", passwd);
			mv.addObject("url", url);
			mv.setViewName("board/checkPasswd");
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			mv.addObject("msg", "글 상태 변경 실패 : 잘못된 파라미터");
			mv.addObject("url", "start.board");
			mv.setViewName("message");
		}
		return mv;
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
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView("board/writeForm");
		mv.addObject("ip", req.getRemoteAddr());
		return mv;
	}
}
