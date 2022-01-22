package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class MemberDeleteController implements Controller {
	
	MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String msg, url;
		try {
			int no = Integer.parseInt(req.getParameter("no"));
			if(memberDAO.delete(no) > 0){
				msg = "삭제 완료";
				url = "search.mem";
			} else {
				msg = "삭제 실패 : 해당 번호 없음";
				url = "search.mem";
			}
		} catch(NullPointerException | NumberFormatException e) {
			msg = "삭제 실패 : 잘못된 파라미터";
			url = "search.mem";
		}
		ModelAndView mv = new ModelAndView("message");
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		return mv;
	}

}
