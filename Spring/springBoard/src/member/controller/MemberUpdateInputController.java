package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberUpdateInputController implements Controller {
	
	MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			int no = Integer.parseInt(req.getParameter("no"));
			MemberDTO member = memberDAO.selectNo(no);
			mv.setViewName("member/member_update");
			mv.addObject("member", member);
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			mv.setViewName("message");
			mv.addObject("msg", "잘못된 파라미터");
			mv.addObject("url", "search.mem");
		}
		return mv;
		
	}

}
