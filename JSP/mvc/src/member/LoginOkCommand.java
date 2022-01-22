package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.login.LoginCheck;
import member.login.LoginOkBean;

public class LoginOkCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String source = (String)session.getAttribute("source");
		session.removeAttribute("source");
		
		String msg = null, url = null;
		if(source != null) {
			String id = req.getParameter("id");
			String passwd = req.getParameter("passwd");
			String saveId = req.getParameter("saveId");
			
			if(id == null || id.trim().equals("")){
				return "login.mem";
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
					
					msg = "로그인되었습니다.";
					url = "start." + source;
					req.getSession().removeAttribute("source");
				} catch(Exception e) {
					e.printStackTrace();
					msg = "오류 발생. 관리자 문의";
					url = "start." + source;
				}
				break;
			case LoginCheck.NOT_ID:
				msg = "아이디가 없습니다. 다시 확인하고 입력해주세요.";
				url = "login.mem";
				break;
			case LoginCheck.NOT_PW:
				msg = "비밀번호가 틀렸습니다. 다시 확인하고 입력해주세요.";
				url = "login.mem";
				break;
			case LoginCheck.ERROR:
				msg = "DB서버 오류 발생. 관리자 문의";
				url = "start." + source;
				break;
			default :
				msg = "??";
				url = "start." + source;
			}
		} else {
			msg = "이전페이지값 사라짐";
			url = "index.jsp";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
