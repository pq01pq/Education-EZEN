package shop.mall;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CategoryDTO;
import shop.CommandIf;
import shop.ProductDTO;

public class CategoryProductListCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String cCode = req.getParameter("ccode");
			
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<CategoryDTO> categories = (List<CategoryDTO>)session.getAttribute("categories");
			for(CategoryDTO category : categories) {
				if(category.getCode().equals(cCode)) {
					req.setAttribute("category", category);
					break;
				}
			}
			
			ProductList productList = ProductList.getInstance();
			List<ProductDTO> products = productList.getList(cCode);
			if(products == null) {
				products = productList.select("pcategory_fk", cCode);
			}
			req.setAttribute("products", products);
			req.setAttribute("root", req.getContextPath());
			
			return "WEB-INF/jsp/myshop/mall/mall_cgProdList.jsp";
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
