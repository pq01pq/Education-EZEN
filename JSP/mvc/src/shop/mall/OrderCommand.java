package shop.mall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CommandIf;
import shop.ProductDTO;

public class OrderCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ProductDTO> products = null;
		String type = req.getParameter("type");
		if(type != null) {
			switch(type) {
			case "now":{
				try {
					int qty = Integer.parseInt(req.getParameter("qty"));
					String listKey = req.getParameter("listKey");
					int pnum = Integer.parseInt(req.getParameter("pnum"));
					
					ProductList productList = ProductList.getInstance();
					products = new ArrayList<>();
					ProductDTO product = productList.getProduct(listKey, pnum);
					product.setPqty(qty);
					products.add(product);
				} catch(NullPointerException | NumberFormatException e) {
					e.printStackTrace();
					req.setAttribute("msg", "주문 실패 : 잘못된 파라미터");
					req.setAttribute("url", "start.mall");
					return "message.jsp";
				}
				break;
			}
			case "cart":{
				HttpSession session = req.getSession();
				CartBean cart = (CartBean)session.getAttribute("cart");
				products = cart.getProducts();
				break;
			}
			default :{
				
			}
			}
		} else {
			req.setAttribute("msg", "주문 실패 : 주문 타입 없음");
			req.setAttribute("url", "start.mall");
			return "message.jsp";
		}
		req.setAttribute("products", products);
		
		return "WEB-INF/jsp/myshop/mall/mall_order.jsp";
	}

}
