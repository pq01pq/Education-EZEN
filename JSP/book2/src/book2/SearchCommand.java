package book2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchCommand implements CommandIf {

	@Override
	public Object command(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, SQLException, IOException {
		BookDAO dao = new BookDAO();
		String column = req.getParameter("column");
		String key = req.getParameter("key");
		List<BookDTO> books = dao.search(column, key);
		req.setAttribute("books", books);
		return "index.jsp";
	}

}
