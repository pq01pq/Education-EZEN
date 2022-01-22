package book2;

import javax.naming.*;
import javax.sql.*;

import java.sql.*;
import java.util.*;

public class BookDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static DataSource ds;
	static {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");	// java:comp/env/ : 라이브러리를 찾아가는 경로
		} catch(NamingException e) {
			System.err.println("lookup실패" + e.getMessage());
		}
	}
	
	public int insert(BookDTO book) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "insert into book(bookname,writer,publisher,price,indate) "
					+ "values(?,?,?,?,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getWriter());
			ps.setString(3, book.getPublisher());
			ps.setInt(4, book.getPrice());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<BookDTO> searchAll() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from book";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<BookDTO> search(String key) throws SQLException {
		if(key == null || key.trim().equals("")) {
			return searchAll();
		}
		
		try {
			con = ds.getConnection();
			key = "%" + key + "%";
			String sql = "select * from book where "
					+ "bookname like ? or "
					+ "writer like ? or "
					+ "publisher like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, key);
			ps.setString(3, key);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		
	}
	
	public List<BookDTO> search(String column, String key) throws SQLException {
		if(column == null || column.trim().equals("")) {
			return search(key);
		}
		
		try {
			con = ds.getConnection();
			key = "%" + key + "%";
			String sql = "select * from book where " + column + " like ?";
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
	
	public int delete(String key) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "delete from book where bookname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	protected List<BookDTO> list(ResultSet rs) throws SQLException {
		List<BookDTO> books = new ArrayList<BookDTO>();
		while(rs.next()) {
			BookDTO book = new BookDTO();
			book.setBookName(rs.getString("bookname"));
			book.setWriter(rs.getString("writer"));
			book.setPublisher(rs.getString("publisher"));
			book.setPrice(rs.getInt("price"));
			book.setIndate(rs.getString("indate"));
			books.add(book);
		}
		return books;
	}
}
