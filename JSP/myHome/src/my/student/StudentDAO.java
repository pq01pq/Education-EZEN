package my.student;

import java.sql.*;
import java.util.*;

public class StudentDAO {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	String url, user, pass;
	
	public StudentDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "bigdata3";
			pass = "bigdata3";
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int insertStudent(StudentDTO dto) throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into student values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getCname());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public ArrayList<StudentDTO> listStudent() throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from student";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return makeArrayList(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public ArrayList<StudentDTO> findStudnet(String key) throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from student where name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			return makeArrayList(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public int deleteStudent(String key) throws SQLException {
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "delete from student where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public ArrayList<StudentDTO> makeArrayList(ResultSet rs) throws SQLException {
		ArrayList<StudentDTO> list = new ArrayList<>();
		while(rs.next()) {
			StudentDTO dto = new StudentDTO();
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setCname(rs.getString("cname"));
			list.add(dto);
		}
		return list;
	}
}
