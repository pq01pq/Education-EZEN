package shop.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CommandIf;
import shop.ProductDAO;
import shop.ProductDTO;

public class ProductUpdateInputCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ProductDAO dao = new ProductDAO();
		String msg = null, url = null;
		try {
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			ProductDTO product = dao.select(pnum).get(0);
			req.setAttribute("product", product);
			String[] specs = new String[] {"normal", "hit", "best", "new"};
			req.setAttribute("specs", specs);
			req.setAttribute("root", req.getContextPath());
			
			return "WEB-INF/jsp/myshop/admin/prod_update.jsp";
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "상품 수정 실패 : 잘못된 파라미터";
			url = "prod_list.admin";
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "상품 수정 실패 : DB 오류";
			url = "prod_list.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
