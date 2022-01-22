package member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView("member/login/login");
		String source = req.getParameter("source");
		req.getSession().setAttribute("source", source);
		Cookie[] cookie = req.getCookies();
		if(cookie != null && cookie.length != 0){
			for(int i = 0; i < cookie.length; i++){
				String savedName = cookie[i].getName();
				if(savedName.equals("saveId")){
					mv.addObject("saveId", cookie[i].getValue());
					break;
				}
			}
		}
		mv.addObject("root", req.getContextPath());
		return mv;
	}

}
