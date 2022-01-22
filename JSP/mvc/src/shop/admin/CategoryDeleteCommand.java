package shop.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CategoryDAO;
import shop.CommandIf;

public class CategoryDeleteCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			int cnum = Integer.parseInt(req.getParameter("cnum"));
			CategoryDAO dao = new CategoryDAO();
			if(dao.delete(cnum) > 0) {
				msg = "카테고리 삭제 완료";
				url = "cate_list.admin";
			} else {
				msg = "카테고리 삭제 실패 : 해당 카테고리 번호 없음";
				url = "cate_list.admin";
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "카테고리 삭제 실패 : 파라미터 없음";
			url = "cate_list.admin";
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "카테고리 삭제 실패 : DB 오류";
			url = "cate_list.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
