package shop.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import shop.CommandIf;
import shop.ProductDAO;

public class ProductInsertCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String saveDirectory = req.getServletContext().getRealPath("myshop/file");
		String msg = null, url = null;
		try {
			MultipartRequest mr = new MultipartRequest(req, saveDirectory, 10*1024*1024, "utf-8");
			ProductDAO dao = new ProductDAO();
			if(dao.insert(mr) > 0) {
				msg = "상품 등록 완료";
				url = "prod_list.admin";
			} else {
				msg = "상품 등록 실패 : DB SQL 수행결과 없음";
				url = "prod_list.admin";
			}
		} catch(IOException e) {
			msg = "파일 인식 실패";
			url = "prod_list.admin";
		} catch(SQLException e) {
			msg = "상품 등록 실패 : DB 오류";
			url = "prod_list.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
