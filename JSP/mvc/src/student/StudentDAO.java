package student;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class StudentDAO {
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
	
	public int insertStudent(StudentDTO dto) throws SQLException {
		try {
			con = ds.getConnection();
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
	
	public List<StudentDTO> listStudent() throws SQLException {
		try {
			con = ds.getConnection();
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
	
	public List<StudentDTO> findStudnet(String key) throws SQLException {
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			String sql = "delete from student where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<StudentDTO> makeArrayList(ResultSet rs) throws SQLException {
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
