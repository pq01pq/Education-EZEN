
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		
		req.setAttribute("name", name);
		req.setAttribute("tel", tel);
		HttpSession session = req.getSession();
		session.setAttribute("name", "EZEN");
		
		RequestDispatcher view = req.getRequestDispatcher("result.jsp");
		view.forward(req, resp);
	}

}
