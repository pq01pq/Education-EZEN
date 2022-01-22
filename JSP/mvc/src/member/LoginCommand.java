package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String source = req.getParameter("source");
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
		return "WEB-INF/jsp/member/login/login.jsp";
	}

}
