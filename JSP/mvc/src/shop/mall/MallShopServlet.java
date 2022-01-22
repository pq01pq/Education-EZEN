package shop.mall;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CommandIf;

public class MallShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String root = req.getContextPath();
		String cmd = uri.substring(root.length() + 1);
		CommandIf cmdIf = CommandFactory.getInstance().createCommand(cmd);
		String path = (String)cmdIf.processCommand(req, resp);
		RequestDispatcher view = req.getRequestDispatcher(path);
		view.forward(req, resp);
	}

}
