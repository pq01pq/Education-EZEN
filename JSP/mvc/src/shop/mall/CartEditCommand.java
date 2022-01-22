package shop.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.CommandIf;

public class CartEditCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			int qty = Integer.parseInt(req.getParameter("qty"));
			HttpSession session = req.getSession();
			CartBean cart = (CartBean)session.getAttribute("cart");
			cart.modify(pnum, qty);
			msg = "상품 수량이 수정되었습니다.";
			url = "cartList.mall";
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			msg = "상품 수량 수정 실패 : 잘못된 파라미터";
			url = "cartList.mall";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
