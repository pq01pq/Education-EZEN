package my.member;

import java.sql.*;
import java.util.*;

import my.db.ConnectionPoolBean;

public class MemberDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectionPoolBean pool;
	
	public ConnectionPoolBean getPool() {
		return pool;
	}

	public void setPool(ConnectionPoolBean pool) {
		this.pool = pool;
	}

	String url, user, password;
	
	private String column;
	private String keyword;
	
	public MemberDAO() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			url = "jdbc:oracle:thin:@localhost:1521:xe";
//			user = "bigdata3";
//			password = "bigdata3";
//		} catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int insert(MemberDTO member) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "insert into member"
					+ "(no,name,id,passwd,ssn1,ssn2,email,hp1,hp2,hp3,joindate) "
					+ "values(member_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getId());
			ps.setString(3, member.getPasswd());
			ps.setString(4, member.getSsn1());
			ps.setString(5, member.getSsn2());
			ps.setString(6, member.getEmail());
			ps.setString(7, member.getHp1());
			ps.setString(8, member.getHp2());
			ps.setString(9, member.getHp3());
			
			int res = ps.executeUpdate();
			return res;
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public MemberDTO selectNo(int key) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from member where no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			List<MemberDTO> members = makeList(rs);
			return members.get(0);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<MemberDTO> select(String key) throws SQLException {
		return null;
	}
	
	public List<MemberDTO> select(String column, String key) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from member where " + column + "=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			List<MemberDTO> members = makeList(rs);
			return members;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public boolean checkSsn(String ssn1, String ssn2) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from member where ssn1=? and ssn2=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ssn1);
			ps.setString(2, ssn2);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<MemberDTO> search() throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from member";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<MemberDTO> members = makeList(rs);
			return members;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<MemberDTO> search(String key) throws SQLException {
		return null;
	}
	
	public List<MemberDTO> search(String column, String key) throws SQLException {
		try {
			con = pool.getConnection();
			key = "%" + key + "%";
			String sql = "select * from member where " + column + " like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public int update(MemberDTO member) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "update member set "
					+ "passwd=?,email=?,hp1=?,hp2=?,hp3=? "
					+ "where no=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getPasswd());
			ps.setString(2, member.getEmail());
			ps.setString(3, member.getHp1());
			ps.setString(4, member.getHp2());
			ps.setString(5, member.getHp3());
			ps.setInt(6, member.getNo());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public int delete(int no) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "delete from member where no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			int res = ps.executeUpdate();
			return res;
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	private List<MemberDTO> makeList(ResultSet rs) throws SQLException {
		List<MemberDTO> members = new ArrayList<>();
		while(rs.next()) {
			MemberDTO member = new MemberDTO();
			member.setNo(rs.getInt("no"));
			member.setName(rs.getString("name"));
			member.setId(rs.getString("id"));
			member.setPasswd(rs.getString("passwd"));
			member.setSsn1(rs.getString("ssn1"));
			member.setSsn2(rs.getString("ssn2"));
			member.setEmail(rs.getString("email"));
			member.setHp1(rs.getString("hp1"));
			member.setHp2(rs.getString("hp2"));
			member.setHp3(rs.getString("hp3"));
			member.setJoindate(rs.getString("joindate"));
			members.add(member);
		}
		
		return members;
	}
}
