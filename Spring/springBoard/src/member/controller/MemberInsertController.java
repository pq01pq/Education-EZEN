package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberInsertController extends AbstractCommandController {
	
	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp,
			Object dto, BindException be) throws Exception {
		MemberDTO member = (MemberDTO)dto;
		String msg, url;
		if(memberDAO.insert(member) > 0) {
			msg = "회원가입 완료";
			url = "search.mem";
		} else {
			msg = "회원가입 실패 : DB 오류";
			url = "search.mem";
		}
		ModelAndView mv = new ModelAndView("message");
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		return mv;
	}

}
