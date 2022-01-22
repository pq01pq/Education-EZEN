package shop.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CommandIf;

public class CartDeleteCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			HttpSession session = req.getSession();
			CartBean cart = (CartBean)session.getAttribute("cart");
			if(cart != null) {
				if(cart.remove(pnum) != null) {
					
					return "cartList.mall";
					
				} else {
					msg = "장바구니에 상품 없음";
					url = "cartList.mall";
				}
			} else {
				msg = "세션기간 만료";
				url = "cartList.mall";
			}
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "장바구니 삭제 실패 : 잘못된 파라미터";
			url = "cartList.mall";
		}
		return "message.jsp";
	}

}
