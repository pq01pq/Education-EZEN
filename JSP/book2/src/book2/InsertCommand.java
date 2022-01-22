package book2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCommand implements CommandIf {

	@Override
	public Object command(HttpServletRequest req, HttpServletResponse resp) throws ServletException, SQLException, IOException {
		BookDTO book = new BookDTO();
		book.setBookName(req.getParameter("bookName"));
		book.setWriter(req.getParameter("writer"));
		book.setPublisher(req.getParameter("publisher"));
		
		if(book.getBookName() == null ||
				book.getWriter() == null ||
				book.getPublisher() == null ||
				book.getBookName().trim().equals("") ||
				book.getWriter().trim().equals("") ||
				book.getPublisher().trim().equals("")) {
			return "input.jsp";
		}
		
		try {
			book.setPrice(Integer.parseInt(req.getParameter("price")));
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			return "input.jsp";
		}
		
		BookDAO dao = new BookDAO();
		String msg, url;
		if(dao.insert(book) > 0) {
			msg = "입력 성공";
			url = "index.jsp";
		} else {
			msg = "입력 실패";
			url = "insert.jsp";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
