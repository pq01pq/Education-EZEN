package book;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	String url, user, password;
	
	public BookDAO() {
		super();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "bigdata3";
			password = "bigdata3";
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(BookDTO book) throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "insert into book(bookname,writer,publisher,price,indate) "
					+ "values(?,?,?,?,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getWriter());
			ps.setString(3, book.getPublisher());
			ps.setInt(4, book.getPrice());
			if(ps.executeUpdate() == 0) {
				System.out.println("입력 실패");
			}
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public ArrayList<BookDTO> searchAll() throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from book";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<BookDTO> books = new ArrayList<BookDTO>();
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
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public ArrayList<BookDTO> search(String key) throws SQLException {
		if(key != null && key.trim().equals("")) {
			return searchAll();
		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
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
			
			ArrayList<BookDTO> books = new ArrayList<BookDTO>();
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
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		
	}
	
	public ArrayList<BookDTO> search(String column, String key) throws SQLException {
		if(column != null && column.trim().equals("")) {
			return search(key);
		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
			key = "%" + key + "%";
			String sql = "select * from book where " + column + " like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			ArrayList<BookDTO> books = new ArrayList<BookDTO>();
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
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public void delete(String key) throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "delete from book where bookname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			int res = ps.executeUpdate();
			if(res < 1) {
				System.out.println("삭제 실패");
			}
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
}