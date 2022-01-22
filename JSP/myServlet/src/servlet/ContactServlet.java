package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		String id = req.getParameter("id");
		String tel = req.getParameter("tel");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<h2>" + id + "님의 전화번호는 " + tel + " 입니다</h2>");
	}
	
	
}
