package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String source = req.getParameter("source");
		req.getSession().invalidate();
		req.setAttribute("msg", "로그아웃 되었습니다.");
		if(source == null || source.trim().equals("")) {
			req.setAttribute("url", "index.jsp");
		} else {
			req.setAttribute("url", "start." + source);
		}
		
		return "message.jsp";
	}

}
