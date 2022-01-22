package book2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements CommandIf {

	@Override
	public Object command(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, SQLException, IOException {
		String bookName = req.getParameter("bookName");
		BookDAO dao = new BookDAO();
		String msg, url;
		if(dao.delete(bookName) > 0) {
			msg = "삭제 완료";
			url = "index.jsp";
		} else {
			msg = "삭제 실패";
			url = "delete.jsp";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
