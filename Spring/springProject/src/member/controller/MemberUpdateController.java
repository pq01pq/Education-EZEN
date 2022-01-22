package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberUpdateController extends AbstractCommandController {
	
	MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp,
			Object dto, BindException be) throws Exception {
		MemberDTO member = (MemberDTO)dto;
		String msg, url;
		if(memberDAO.update(member) > 0) {
			msg = "수정 완료";
			url = "member_search.do";
		} else {
			msg = "수정 실패 : 회원 없음";
			url = "member_search.do";
		}
		ModelAndView mv = new ModelAndView("message");
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		return mv;
	}

}
