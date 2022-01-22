package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int num = Integer.parseInt(req.getParameter("num"));
			String passwd = req.getParameter("passwd");
			
			String type = req.getParameter("type");
			String url = null;
			switch(type){
			case "update":
				url = "updateInput.board";
				break;
			case "delete":
				url = "delete.board";
				break;
			default :
			}
			req.setAttribute("num", num);
			req.setAttribute("passwd", passwd);
			req.setAttribute("url", url);
			
			return "WEB-INF/jsp/board/checkPasswd.jsp";
			
		} catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("msg", "글 상태 변경 실패 : 잘못된 파라미터");
			req.setAttribute("url", "start.board");
		}
		return "message.jsp";
	}

}
