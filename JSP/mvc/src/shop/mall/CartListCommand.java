package shop.mall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CommandIf;
import shop.ProductDTO;

public class CartListCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		CartBean cart = (CartBean)session.getAttribute("cart");
		if(cart == null) {
			cart = new CartBean();
			session.setAttribute("cart", cart);
		}
		List<ProductDTO> products = cart.getProducts();
		req.setAttribute("products", products);
		req.setAttribute("root", req.getContextPath());
		return "WEB-INF/jsp/myshop/mall/mall_cartList.jsp";
	}

}
