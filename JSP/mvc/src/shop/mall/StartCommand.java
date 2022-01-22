package shop.mall;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CategoryDAO;
import shop.CategoryDTO;
import shop.CommandIf;
import shop.ProductDTO;

public class StartCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			
			CategoryDAO cdao = new CategoryDAO();
			if(session.getAttribute("categories") == null) {
				List<CategoryDTO> categories = cdao.searchAll();
				session.setAttribute("categories", categories);
			}
			ProductList productList = ProductList.getInstance();
			
			List<ProductDTO> hitProducts = productList.select("pspec", "hit");
			List<ProductDTO> bestProducts = productList.select("pspec", "best");
			List<ProductDTO> newProducts = productList.select("pspec", "new");
			req.setAttribute("hitProducts", hitProducts);
			req.setAttribute("bestProducts", bestProducts);
			req.setAttribute("newProducts", newProducts);
			req.setAttribute("root", req.getContextPath());
			
			return "WEB-INF/jsp/myshop/mall/mall.jsp";
			
		} catch(SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "상품 검색 실패 : DB 오류");
			req.setAttribute("url", "index.jsp");
		}
		return "message.jsp";
	}

}
