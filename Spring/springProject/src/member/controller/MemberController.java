package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping("member_search.do")
	public String search(HttpServletRequest req, @RequestParam(required = false) String mode) {
		if(mode == null || mode.trim().equals("")) {
			mode = "all";
		}
		String column = req.getParameter("column");
		String key = req.getParameter("key");
		List<MemberDTO> members = null;
		switch(mode) {
		case "all":{
			members = memberDAO.searchAll();
			break;
		}
		case "find":{
			members = memberDAO.search(column, key);
			break;
		}
		default :{
			mode = "all";
			members = memberDAO.searchAll();
		}
		}
		req.setAttribute("mode", mode);
		req.setAttribute("members", members);
		return "member/memberAll";
	}
	
	@RequestMapping("member_ssn.do")
	public String ssn() {
		return "member/memberSsn";
	}
	
	@RequestMapping("member_check.do")
	public String check(HttpServletRequest req,
			@RequestParam(required = false) String option,
			@RequestParam String name,
			@RequestParam String ssn1,
			@RequestParam String ssn2,
			@RequestParam(required = false) String id) {
		String msg, url;
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
					msg = "???????????? ???????????? " + id + "?????????.";
					url = "member_all.do";
				}
				else {
					msg = "???????????? ??????????????? ????????????.";
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
					msg = "???????????? ???????????????" + passwd + "?????????.";
					url = "member_all.do";
				} else {
					msg = "???????????? ??????????????? ????????????.";
					url = "member_all.do";
				}
				break;
			}
			default :
				System.out.println("option : " + option);
				msg = "?????????????";
				url = "member_ssn.do";
			}
		} else {
			if(memberDAO.checkSsn(ssn1, ssn2)){
				msg = "?????? ??????????????????. ?????????????????????.";
				url = "member_all.do";
			} else {
				msg = "???????????? ???????????? ???????????????.";
				url = "member_input.do";
				HttpSession session = req.getSession();
				session.setAttribute("name", name);
				session.setAttribute("ssn1", ssn1);
				session.setAttribute("ssn2", ssn2);
			}
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("member_input.do")
	public String input() {
		return "member/member_input";
	}
	
	@RequestMapping("member_insert.do")
	public String insert(HttpServletRequest req,
			@ModelAttribute MemberDTO member, BindingResult result) {
		String msg, url;
		if(memberDAO.insert(member) > 0) {
			msg = "???????????? ??????";
			url = "member_search.do";
		} else {
			msg = "???????????? ?????? : DB ??????";
			url = "member_search.do";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("member_update_input.do")
	public String updateInput(HttpServletRequest req, @RequestParam("no") String strNo) {
		String page;
		try {
			int no = Integer.parseInt(strNo);
			MemberDTO member = memberDAO.selectNo(no);
			page = "member/member_update";
			req.setAttribute("member", member);
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			page = "message";
			req.setAttribute("msg", "????????? ????????????");
			req.setAttribute("url", "member_search.do");
		}
		return page;
	}
	
	@RequestMapping("member_update.do")
	public String update(HttpServletRequest req, @ModelAttribute MemberDTO member) {
		String msg, url;
		if(memberDAO.update(member) > 0) {
			msg = "?????? ??????";
			url = "member_search.do";
		} else {
			msg = "?????? ?????? : ?????? ??????";
			url = "member_search.do";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("member_delete.do")
	public String delete(HttpServletRequest req, @RequestParam("no") String strNo) {
		String msg, url;
		try {
			int no = Integer.parseInt(strNo);
			if(memberDAO.delete(no) > 0){
				msg = "?????? ??????";
				url = "member_search.do";
			} else {
				msg = "?????? ?????? : ?????? ?????? ??????";
				url = "member_search.do";
			}
		} catch(NullPointerException | NumberFormatException e) {
			msg = "?????? ?????? : ????????? ????????????";
			url = "member_search.do";
		}
		ModelAndView mv = new ModelAndView("message");
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
}
