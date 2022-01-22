package dao;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

import dto.MemoDTO;

public class MemoDAO {
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
	
	public int insert(MemoDTO memo) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "insert into memo(id,email,content) values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, memo.getId());
			ps.setString(2, memo.getEmail());
			ps.setString(3, memo.getContent());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<MemoDTO> selectAll() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from memo";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	protected List<MemoDTO> list(ResultSet rs) throws SQLException {
		List<MemoDTO> list = new ArrayList<>();
		while(rs.next()) {
			MemoDTO memo = new MemoDTO();
			memo.setId(rs.getString("id"));
			memo.setEmail(rs.getString("email"));
			memo.setContent(rs.getString("content"));
			list.add(memo);
		}
		return list;
	}
}
