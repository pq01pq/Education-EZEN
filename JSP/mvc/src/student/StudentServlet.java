package student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
		String root = req.getContextPath();
		String cmd = uri.substring(root.length());
		
		String nextPage = null;
		CommandFactory factory = CommandFactory.getInstance();
		CommandIf cmdIf = factory.createCommand(cmd);
		nextPage = (String)cmdIf.processCommand(req, resp);
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}
	
	
}
