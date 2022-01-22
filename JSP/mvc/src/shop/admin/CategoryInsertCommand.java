package shop.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CategoryDAO;
import shop.CategoryDTO;
import shop.CommandIf;

public class CategoryInsertCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CategoryDTO category = new CategoryDTO();
		category.setCode(req.getParameter("code"));
		category.setCname(req.getParameter("cname"));
		CategoryDAO dao = new CategoryDAO();
		String msg = null, url = null;
		try {
			if(dao.insert(category) > 0) {
				msg = "카테고리 등록 완료";
				url = "cate_list.admin";
			} else {
				msg = "카테고리 등록 실패 : DB SQL 수행결과 없음";
				url = "cate_list.admin";
			}
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "카테고리 등록 실패 : DB 오류";
			url = "WEB-INF/jsp/myshop/admin/cate_list.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
