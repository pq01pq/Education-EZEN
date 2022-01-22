package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView("message");
		String source = req.getParameter("source");
		req.getSession().invalidate();
		mv.addObject("msg", "로그아웃 되었습니다.");
		if(source == null || source.trim().equals("")) {
			mv.addObject("url", "index.jsp");
		} else {
			mv.addObject("url", "start." + source);
		}
		
		return mv;
	}

}
