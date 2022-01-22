package member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		String msg = null, url = null;
		
		String option = req.getParameter("option");
		String name = req.getParameter("name");
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		String id = req.getParameter("id");
		MemberDAO dao = new MemberDAO();
		try {
			if(option != null && !option.trim().equals("")){
				switch(option){
				case "findId": {
					List<MemberDTO> members1 = dao.select("ssn1", ssn1);
					List<MemberDTO> members2 = dao.select("ssn2", ssn2);
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
						url = "start.mem";
					}
					else {
						msg = "일치하는 회원정보가 없습니다.";
						url = "start.mem";
					}
					break;
				}
				case "findPw": {
					List<MemberDTO> members1 = dao.select("ssn1", ssn1);
					List<MemberDTO> members2 = dao.select("ssn2", ssn2);
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
						url = "start.mem";
					} else {
						msg = "일치하는 회원정보가 없습니다.";
						url = "start.mem";
					}
					break;
				}
				default :
					System.out.println("option : " + option);
					msg = "왜일로와?";
					url = "ssn.mem";
				}
			} else {
				if(dao.checkSsn(ssn1, ssn2)){
					msg = "저희 회원이십니다. 로그인해주세요.";
					url = "start.mem";
				} else {
					msg = "회원가입 페이지로 이동합니다.";
					url = "input.mem";
					HttpSession session = req.getSession();
					session.setAttribute("name", name);
					session.setAttribute("ssn1", ssn1);
					session.setAttribute("ssn2", ssn2);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "DB 오류";
			url = "start.mem";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
