package shop.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.CommandIf;
import shop.ProductDAO;
import shop.ProductDTO;

public class ProductListCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ProductDAO dao = new ProductDAO();
		try {
			List<ProductDTO> products = dao.searchAll();
			req.setAttribute("products", products);
			req.setAttribute("root", req.getContextPath());
			
			return "WEB-INF/jsp/myshop/admin/prod_list.jsp";
			
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "상품 검색 실패 : DB 오류");
			req.setAttribute("url", "start.admin");
		}
		
		return "message.jsp";
	}

}
