package shop.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CategoryDAO;
import shop.CategoryDTO;
import shop.CommandIf;
import shop.ProductDAO;
import shop.ProductDTO;

public class ProductViewCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CategoryDAO cdao = new CategoryDAO();
		ProductDAO pdao = new ProductDAO();
		try {
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			ProductDTO product = pdao.select(pnum).get(0);
			String ccode = product.getPcategory_fk().substring(0, 4);
			CategoryDTO category = cdao.select("code", ccode).get(0);
			req.setAttribute("category", category);
			req.setAttribute("product", product);
			req.setAttribute("root", req.getContextPath());
			
			return "WEB-INF/jsp/myshop/admin/prod_view.jsp";
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("msg", "상품보기 실패 : 잘못된 파라미터");
			req.setAttribute("url", "prod_list.admin");
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "상품보기 실패 : DB 오류");
			req.setAttribute("url", "prod_list.admin");
		}
		return "message.jsp";
	}

}
