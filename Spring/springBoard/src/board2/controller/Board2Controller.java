package board2.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import board2.dto.Board2DTO;
import board2.mybatis.Board2Mapper;

@Controller
public class Board2Controller {
	
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@RequestMapping("list.board2")
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
		
		return "board2/list";
	}
	
	@RequestMapping("context.board2")
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
			req.setAttribute("url", "list.board2");
		} catch(DataAccessException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 조회 실패 : DB 오류");
			req.setAttribute("url", "list.board2");
		}
		return "message";
	}
	
	@RequestMapping("insert.board2")
	public int insert(HttpServletRequest req) throws IOException {
		// 파일 받기
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("fileName");
		String filename = mf.getOriginalFilename();
		System.out.println("전송된 파일명 : " + filename);
		if(filename == null || filename.trim().equals("")) {
			System.out.println("선택된 파일이 없음");
			return 0;
		}
		
		HttpSession session = req.getSession();
		String saveDirectory = session.getServletContext().getRealPath("file");
		
		File file = new File(saveDirectory, filename);
		mf.transferTo(file);
		
		Board2DTO article = new Board2DTO();
		article.setNum(Integer.parseInt(req.getParameter("num")));
		article.setWriter(req.getParameter("writer"));
		article.setSubject(req.getParameter("subject"));
		article.setPasswd(req.getParameter("passwd"));
		article.setContent(req.getParameter("content"));
		article.setIp(req.getParameter("ip"));
		article.setFileName(file.getName());
		article.setFileSize((int)file.length());
		
		return Board2Mapper.insert(article);
	}
	
	@RequestMapping("write.board2")
	public String write(HttpServletRequest req) {
		req.setAttribute("ip", req.getRemoteAddr());
		return "board2/writeForm";
	}
	
	@RequestMapping("update.board2")
	public int update(HttpServletRequest req) throws Exception {
		// 파일 받기
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("fileName");
		String newFileName = mf.getOriginalFilename();
		String preFileName = mr.getParameter("preFileName");
		System.out.println("전송된 파일명 : " + newFileName);
		
		HttpSession session = req.getSession();
		String saveDirectory = session.getServletContext().getRealPath("file");
		File file = null;
		if(newFileName == null || newFileName.trim().equals("")) {
			newFileName = preFileName;
		} else {
			file = new File(saveDirectory, newFileName);
			mf.transferTo(file);
		}
		long fileSize = 0L;
		if(file != null){
			fileSize = file.length();
		}
		
		Board2DTO article = new Board2DTO();
		article.setNum(Integer.parseInt(req.getParameter("num")));
		article.setWriter(req.getParameter("writer"));
		article.setSubject(req.getParameter("subject"));
		article.setPasswd(req.getParameter("passwd"));
		article.setContent(req.getParameter("content"));
		article.setIp(req.getParameter("ip"));
		article.setFileName(file.getName());
		article.setFileSize((int)fileSize);
		
		int res = Board2Mapper.update(article);
		
		if(newFileName != null && !newFileName.trim().equals("")
				&& !newFileName.equals(preFileName)){
			File preFile = new File(saveDirectory, preFileName);
			preFile.delete();
		}
		
		return res;
	}
}
