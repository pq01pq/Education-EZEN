package mvc1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String depart = req.getParameter("depart");
		resp.setContentType("text/html; charset=utf-8");

		DepartExpert de = new DepartExpert();
		List<String> advice = de.getAdvice(depart);
		
		req.setAttribute("list", advice);
		
		RequestDispatcher view = req.getRequestDispatcher("/result.jsp");
		view.forward(req, resp);
		
//		PrintWriter pw = resp.getWriter();
//		pw.println("<h2>넘어온 데이터 : " + depart + "</h2>");
	}
	
	

}
