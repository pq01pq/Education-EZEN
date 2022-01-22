package book;

import java.sql.*;
import java.util.*;

public class BookDAO {//CRUD
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	String url, user, pass;
	
	public BookDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "bigdata3";
		pass = "bigdata3";
	}
	
	public int insertBook(String bookname, String writer, 
						String publisher, int price, String indate) {
		int res = 0;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into book values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookname);
			ps.setString(2, writer);
			ps.setString(3, publisher);
			ps.setInt(4, price);
			ps.setString(5, indate);
			res = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int deleteBook(String bookname) {
		int res = 0;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "delete from book where bookname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookname);
			res = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<BookDTO> listBook() {
		ArrayList<BookDTO> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from book";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setBookname(rs.getString("bookname"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setPrice(rs.getInt("price"));
				dto.setIndate(rs.getString("indate"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<BookDTO> searchBook(String search, String searchString){
		ArrayList<BookDTO> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from book where "+search+" like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+searchString+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setBookname(rs.getString("bookname"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setPrice(rs.getInt("price"));
				dto.setIndate(rs.getString("indate"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
















