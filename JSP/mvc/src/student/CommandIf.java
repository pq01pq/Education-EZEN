package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public interface CommandIf {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
