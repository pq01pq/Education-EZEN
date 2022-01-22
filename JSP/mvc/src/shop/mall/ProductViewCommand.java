package shop.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CommandIf;
import shop.ProductDTO;

public class ProductViewCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			String listKey = req.getParameter("listKey");
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			ProductList productList = ProductList.getInstance();
			ProductDTO product = productList.getProduct(listKey, pnum);
			if(product != null) {
				req.setAttribute("product", product);
				req.setAttribute("root", req.getContextPath());
				
				return "WEB-INF/jsp/myshop/mall/mall_prodView.jsp";
				
			} else {
				msg = "상품 검색 실패";
				url = "start.mall";
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "상품 검색 실패 : 잘못된 파라미터";
			url = "start.mall";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
