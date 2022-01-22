package shop.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CommandIf;
import shop.ProductDAO;

public class ProductDeleteCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			ProductDAO dao = new ProductDAO();
			if(dao.delete(pnum) > 0) {
				msg = "상품 삭제 완료";
				url = "prod_list.admin";
			} else {
				msg = "상품 삭제 실패 : DB SQL 수행결과 없음";
				url = "prod_list.admin";
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "상품 삭제 실패 : 잘못된 파라미터";
			url = "prod_list.admin";
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "상품 삭제 실패 : DB 오류";
			url = "prod_list.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
