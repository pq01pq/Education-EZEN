import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		String root = req.getContextPath();
		String cmd = uri.substring(root.length() + 1);
		String nextPage = null;
		switch(cmd) {
		case "start.do":{
			nextPage = "index.jsp";
			break;
		}
		case "student.do":{
			nextPage = "start.st";
			break;
		}
		case "member.do":{
			nextPage = "start.mem";
			break;
		}
		case "adminShop.do":{
			nextPage = "start.admin";
			break;
		}
		case "mallShop.do":{
			nextPage = "start.mall";
			break;
		}
		case "board.do":{
			nextPage = "start.board";
			break;
		}
		default :{
			
		}
		}
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}
	
	
}
