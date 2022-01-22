package shop;

import java.util.*;
import javax.naming.*;
import javax.sql.*;
import com.oreilly.servlet.MultipartRequest;
import java.sql.*;

public class ProductDAO {
	private static DataSource ds;
	static {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");	// java:comp/env/ : 라이브러리를 찾아가는 경로
		} catch(NamingException e) {
			System.err.println("lookup실패" + e.getMessage());
		}
	}
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public int insert(MultipartRequest mr) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "insert into product "
					+ "(pnum,pname,pcategory_fk,pcompany,pimage,"
					+ "pqty,price,pspec,pcontents,point,pinputdate) "
					+ "values(prod_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
											// 1 2 3 4 5 6 7 8 9
			ps = con.prepareStatement(sql);
			ps.setString(1, mr.getParameter("pname"));
			ps.setString(2, mr.getParameter("ccode") + mr.getParameter("pcode"));
			ps.setString(3, mr.getParameter("pcompany"));
			ps.setString(4, mr.getFilesystemName("pimage"));
			ps.setInt(5, Integer.parseInt(mr.getParameter("pqty")));
			ps.setInt(6, Integer.parseInt(mr.getParameter("price")));
			ps.setString(7, mr.getParameter("pspec"));
			ps.setString(8, mr.getParameter("pcontents"));
			ps.setInt(9, Integer.parseInt(mr.getParameter("point")));
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<ProductDTO> searchAll() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from product";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<ProductDTO> select(int pnum) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from product where pnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pnum);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<ProductDTO> select(String column, String key) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from product where " + column + "=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public int update(MultipartRequest mr) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "update product set ";
			sql += "pname=?,";
			sql += "pcompany=?,";
			sql += "pimage=?,";
			sql += "pqty=?,";
			sql += "price=?,";
			sql += "pspec=?,";
			sql += "pcontents=?,";
			sql += "point=? ";
			sql += "where pnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mr.getParameter("pname"));
			ps.setString(2, mr.getParameter("pcompany"));
			if(mr.getFilesystemName("pimage") == null) {
				ps.setString(3, mr.getParameter("preImage"));
			} else {
				ps.setString(3, mr.getFilesystemName("pimage"));
			}
			ps.setInt(4, Integer.parseInt(mr.getParameter("pqty")));
			ps.setInt(5, Integer.parseInt(mr.getParameter("price")));
			ps.setString(6, mr.getParameter("pspec"));
			ps.setString(7, mr.getParameter("pcontents"));
			ps.setInt(8, Integer.parseInt(mr.getParameter("point")));
			ps.setInt(9, Integer.parseInt(mr.getParameter("pnum")));
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public int delete(int pnum) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "delete from product where pnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pnum);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	private List<ProductDTO> list(ResultSet rs) throws SQLException {
		List<ProductDTO> categories = new ArrayList<>();
		while(rs.next()) {
			ProductDTO product = new ProductDTO();
			product.setPnum(rs.getInt("pnum"));
			product.setPname(rs.getString("pname"));
			product.setPcategory_fk(rs.getString("pcategory_fk"));
			product.setPcompany(rs.getString("pcompany"));
			product.setPimage(rs.getString("pimage"));
			product.setPqty(rs.getInt("pqty"));
			product.setPrice(rs.getInt("price"));
			product.setPspec(rs.getString("pspec"));
			product.setPcontents(rs.getString("pcontents"));
			product.setPoint(rs.getInt("point"));
			product.setPinputdate(rs.getString("pinputdate"));
			categories.add(product);
		}
		return categories;
	}

}
