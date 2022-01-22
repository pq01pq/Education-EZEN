package shop.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CommandIf;
import shop.ProductDTO;

public class CartAddCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String listKey = req.getParameter("listKey");
			int qty = Integer.parseInt(req.getParameter("qty"));
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			ProductList productList = ProductList.getInstance();
			ProductDTO addProduct = productList.getProduct(listKey, pnum);
			addProduct.setPqty(qty);
			
			HttpSession session = req.getSession();
			CartBean cart = (CartBean)session.getAttribute("cart");
			if(cart == null) {
				cart = new CartBean();
				session.setAttribute("cart", cart);
			}
			cart.add(addProduct);
			
			return "cartList.mall";
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("msg", "장바구니 추가 실패 : 잘못된 파라미터");
			req.setAttribute("url", "start.mall");
		}
		return "message.jsp";
		
	}

}
