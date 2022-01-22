package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BoardCheckPasswdController implements Controller {

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

}
