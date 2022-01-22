package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemoDAO;
import dto.MemoDTO;

public class MemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		MemoDTO inMemo = new MemoDTO();
		inMemo.setId(req.getParameter("id"));
		inMemo.setEmail(req.getParameter("email"));
		inMemo.setContent(req.getParameter("content"));
		MemoDAO memodao = new MemoDAO();
		List<MemoDTO> list = null;
		try {
			memodao.insert(inMemo);
			list = memodao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = resp.getWriter(); 
		pw.println("<table border=\"1\">");
		if(list == null) {
			pw.println("<tr><td colspan=\"3\">파일 없음</td></tr>");
		} else {
			pw.println("<tr><th>아이디</th><th>이메일</th><th>내용</th></tr>");
			for(MemoDTO memo : list) {
				pw.println("<tr><td>" + memo.getId() + "</td><td>" + memo.getEmail() + "</td><td>" + memo.getContent() + "</td></tr>");
			}
		}
		pw.println("</table>");
		
		pw.close();
	}

}
