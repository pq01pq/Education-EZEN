package book2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String command = req.getParameter("command");
		CommandIf cmdIf = CommandFactory.getInstance().createCommand(command);
		String nextPage = null;
		try {
			nextPage = (String)cmdIf.command(req, resp);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}

}
