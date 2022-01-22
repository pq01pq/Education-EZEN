package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberSearchController implements Controller {
	
	MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String mode = req.getParameter("mode");
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
		ModelAndView mv = new ModelAndView("member/memberAll");
		mv.addObject("mode", mode);
		mv.addObject("members", members);
		return mv;
	}

}
