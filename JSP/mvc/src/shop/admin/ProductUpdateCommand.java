package shop.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import shop.CommandIf;
import shop.ProductDAO;

public class ProductUpdateCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = null, url = null;
		try {
			ProductDAO dao = new ProductDAO();
			String saveDirectory = req.getServletContext().getRealPath("myshop/file");
			MultipartRequest mr = new MultipartRequest(req, saveDirectory, 10*1024*1024, "utf-8");
			String preImageName = mr.getParameter("preImage");
			File preImage = null;
			if(preImageName != null){
				preImage = new File(saveDirectory, preImageName);
			}
			
			if(dao.update(mr) > 0 ){
				if(preImage != null && mr.getFilesystemName("pimage") != null){
					preImage.delete();
				}
				msg = "상품 수정 완료";
				url = "prod_list.admin";
			} else { 
				msg = "상품 수정 실패 : DB SQL 수행결과 없음";
				url = "prod_list.admin";
			}
		} catch(IOException e) {
			e.printStackTrace();
			msg = "상품 수정 실패 : 파일 인식 실패";
			url = "prod_list.admin";
		} catch(SQLException e) {
			e.printStackTrace();
			msg = "상품 수정 실패 : DB 오류";
			url = "prod_list.admin";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
