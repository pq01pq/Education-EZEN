package member.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberCheckController implements Controller {
	
	MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String msg = null, url = null;
		
		String option = req.getParameter("option");
		String name = req.getParameter("name");
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		String id = req.getParameter("id");
		if(option != null && !option.trim().equals("")){
			switch(option){
			case "findId": {
				List<MemberDTO> members1 = memberDAO.select("ssn1", ssn1);
				List<MemberDTO> members2 = memberDAO.select("ssn2", ssn2);
				MemberDTO foundMember = null;
				for(MemberDTO member1 : members1){
					for(MemberDTO member2 : members2){
						if(member1.getAllSsn().equals(member2.getAllSsn())){
							foundMember = member1;
						}
					}
				}

				if(foundMember != null && foundMember.getName().equals(name)){
					id = foundMember.getId();
				}

				if(id != null){
					msg = "회원님의 아이디는 " + id + "입니다.";
					url = "member_all.do";
				}
				else {
					msg = "일치하는 회원정보가 없습니다.";
					url = "member_all.do";
				}
				break;
			}
			case "findPw": {
				List<MemberDTO> members1 = memberDAO.select("ssn1", ssn1);
				List<MemberDTO> members2 = memberDAO.select("ssn2", ssn2);
				MemberDTO foundMember = null;
				for(MemberDTO member1 : members1){
					for(MemberDTO member2 : members2){
						if(member1.getAllSsn().equals(member2.getAllSsn())){
							foundMember = member1;
						}
					}
				}

				String passwd = null;
				if(foundMember.getName().equals(name) && foundMember.getId().equals(id)){
					passwd = foundMember.getPasswd();
				}

				if(passwd != null){
					msg = "회원님의 비밀번호는" + passwd + "입니다.";
					url = "member_all.do";
				} else {
					msg = "일치하는 회원정보가 없습니다.";
					url = "member_all.do";
				}
				break;
			}
			default :
				System.out.println("option : " + option);
				msg = "왜일로와?";
				url = "member_ssn.do";
			}
		} else {
			if(memberDAO.checkSsn(ssn1, ssn2)){
				msg = "저희 회원이십니다. 로그인해주세요.";
				url = "member_all.do";
			} else {
				msg = "회원가입 페이지로 이동합니다.";
				url = "member_input.do";
				HttpSession session = req.getSession();
				session.setAttribute("name", name);
				session.setAttribute("ssn1", ssn1);
				session.setAttribute("ssn2", ssn2);
			}
		}
		ModelAndView mv = new ModelAndView("message");
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		return mv;
	}

}
