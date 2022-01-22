package shop.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CategoryDAO;
import shop.CategoryDTO;
import shop.CommandIf;

public class ProductInputCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CategoryDAO dao = new CategoryDAO();
		try {
			List<CategoryDTO> categories = dao.searchAll();
			req.setAttribute("categories", categories);
			return "WEB-INF/jsp/myshop/admin/prod_input.jsp";
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "카테고리 검색 실패 : DB 오류");
			req.setAttribute("url", "prod_list.admin");
			return "message.jsp";
		}
	}

}
