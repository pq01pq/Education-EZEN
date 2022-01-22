package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SsnCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String source = req.getParameter("source");
		req.getSession().setAttribute("source", source);
		
		String option = req.getParameter("option");
		if(option == null || option.trim().equals("")) {
			return "WEB-INF/jsp/member/memberSsn.jsp";
		} else {
			return "WEB-INF/jsp/member/memberSsn.jsp?option=" + option;
		}
	}

}
