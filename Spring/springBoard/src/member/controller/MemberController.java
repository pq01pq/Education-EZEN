package member.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import member.mybatis.MemberMapper;
import member.dao.MemberDAO;
import member.dto.MemberDTO;
import member.login.LoginCheck;
import member.login.LoginOkBean;

@Controller
public class MemberController {

//	@Autowired
//	private MemberDAO memberDAO;
	
	@RequestMapping({"search.mem", "start.mem"})
	public String search(HttpServletRequest req, @RequestParam(required = false) String mode) {
		if(mode == null || mode.trim().equals("")) {
			mode = "all";
		}
		String column = req.getParameter("column");
		String key = req.getParameter("key");
		List<MemberDTO> members = null;
		switch(mode) {
		case "all":{
			members = MemberMapper.searchAll();
			break;
		}
		case "find":{
			members = MemberMapper.search(column, key);
			break;
		}
		default :{
			mode = "all";
			members = MemberMapper.searchAll();
		}
		}
		req.setAttribute("mode", mode);
		req.setAttribute("members", members);
		return "member/memberAll";
	}
	
	@RequestMapping("ssn.mem")
	public String ssn() {
		return "member/memberSsn";
	}
	
	@RequestMapping("check.mem")
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
				List<MemberDTO> members1 = MemberMapper.select("ssn1", ssn1);
				List<MemberDTO> members2 = MemberMapper.select("ssn2", ssn2);
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
					url = "search.mem";
				}
				else {
					msg = "???????????? ??????????????? ????????????.";
					url = "search.mem";
				}
				break;
			}
			case "findPw": {
				List<MemberDTO> members1 = MemberMapper.select("ssn1", ssn1);
				List<MemberDTO> members2 = MemberMapper.select("ssn2", ssn2);
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
					url = "search.mem";
				} else {
					msg = "???????????? ??????????????? ????????????.";
					url = "search.mem";
				}
				break;
			}
			default :
				System.out.println("option : " + option);
				msg = "?????????????";
				url = "ssn.mem";
			}
		} else {
			if(MemberMapper.checkSsn(ssn1, ssn2)){
				msg = "?????? ??????????????????. ?????????????????????.";
				url = "search.mem";
			} else {
				msg = "???????????? ???????????? ???????????????.";
				url = "input.mem";
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
	
	@RequestMapping("input.mem")
	public String input() {
		return "member/member_input";
	}
	
	@RequestMapping("insert.mem")
	public String insert(HttpServletRequest req,
			@ModelAttribute MemberDTO member, BindingResult result) {
		String msg, url;
		if(MemberMapper.insert(member) > 0) {
			msg = "???????????? ??????";
			url = "search.mem";
		} else {
			msg = "???????????? ?????? : DB ??????";
			url = "search.mem";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("update_input.mem")
	public String updateInput(HttpServletRequest req, @RequestParam("no") String strNo) {
		String page;
		try {
			int no = Integer.parseInt(strNo);
			MemberDTO member = MemberMapper.selectNo(no);
			page = "member/member_update";
			req.setAttribute("member", member);
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			page = "message";
			req.setAttribute("msg", "????????? ????????????");
			req.setAttribute("url", "search.mem");
		}
		return page;
	}
	
	@RequestMapping("update.mem")
	public String update(HttpServletRequest req, @ModelAttribute MemberDTO member) {
		String msg, url;
		if(MemberMapper.update(member) > 0) {
			msg = "?????? ??????";
			url = "search.mem";
		} else {
			msg = "?????? ?????? : ?????? ??????";
			url = "search.mem";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("delete.mem")
	public String delete(HttpServletRequest req, @RequestParam("no") String strNo) {
		String msg, url;
		try {
			int no = Integer.parseInt(strNo);
			if(MemberMapper.delete(no) > 0){
				msg = "?????? ??????";
				url = "search.mem";
			} else {
				msg = "?????? ?????? : ?????? ?????? ??????";
				url = "search.mem";
			}
		} catch(NullPointerException | NumberFormatException e) {
			msg = "?????? ?????? : ????????? ????????????";
			url = "search.mem";
		}
		ModelAndView mv = new ModelAndView("message");
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("login.mem")
	public String login(HttpServletRequest req, @RequestParam String source) {
		req.getSession().setAttribute("source", source);
		Cookie[] cookie = req.getCookies();
		if(cookie != null && cookie.length != 0){
			for(int i = 0; i < cookie.length; i++){
				String savedName = cookie[i].getName();
				if(savedName.equals("saveId")){
					req.setAttribute("saveId", cookie[i].getValue());
					break;
				}
			}
		}
		req.setAttribute("root", req.getContextPath());
		return "member/login/login";
	}
	
	@RequestMapping("loginOk.mem")
	public String loginOk(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String source = (String)session.getAttribute("source");
		session.removeAttribute("source");
		
		String msg = null, url = null;
		if(source != null) {
			String id = req.getParameter("id");
			String passwd = req.getParameter("passwd");
			String saveId = req.getParameter("saveId");
			
			if(id == null || id.trim().equals("")){
				return "login.mem?source=" + source;
			}
			
			LoginCheck loginCheck = new LoginCheck();
			LoginOkBean loginOk = new LoginOkBean();
			loginCheck.setId(id);
			loginCheck.setPasswd(passwd);
			
			int res = loginCheck.loginCheck();
			switch(res){
			case LoginCheck.OK:
				try {
					loginOk.setId(loginCheck.getId());
					loginOk.isSetting();
					session.setAttribute("id", loginOk.getId());
					session.setAttribute("name", loginOk.getName());
					
					Cookie cookie = new Cookie("saveId", loginOk.getId());
					if(saveId == null){
						cookie.setMaxAge(0);
					} else {
						cookie.setMaxAge(24*60*60);
					}
					resp.addCookie(cookie);
					
					msg = "????????????????????????.";
					url = "start." + source;
				} catch(Exception e) {
					e.printStackTrace();
					msg = "?????? ??????. ????????? ??????";
					url = "start." + source;
				}
				break;
			case LoginCheck.NOT_ID:
				msg = "???????????? ????????????. ?????? ???????????? ??????????????????.";
				url = "login.mem?source=" + source;
				break;
			case LoginCheck.NOT_PW:
				msg = "??????????????? ???????????????. ?????? ???????????? ??????????????????.";
				url = "login.mem?source=" + source;
				break;
			case LoginCheck.ERROR:
				msg = "DB?????? ?????? ??????. ????????? ??????";
				url = "start." + source;
				break;
			default :
				msg = "??";
				url = "start." + source;
			}
		} else {
			msg = "?????????????????? ?????????";
			url = "index.jsp";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("logout.mem")
	public String logout(HttpServletRequest req, @RequestParam String source) {
		req.getSession().invalidate();
		req.setAttribute("msg", "???????????? ???????????????.");
		if(source == null || source.trim().equals("")) {
			req.setAttribute("url", "index.jsp");
		} else {
			req.setAttribute("url", "start." + source);
		}
		
		return "message";
	}
	
}
