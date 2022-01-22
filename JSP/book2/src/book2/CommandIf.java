package book2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandIf {
	public Object command(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, SQLException, IOException;
}
