package member;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	
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
	
	public int insert(MemberDTO member) throws SQLException {
		try {
			con = ds.getConnection();
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
			if(con != null) con.close();
		}
	}
	
	public MemberDTO selectNo(int key) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from member where no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			List<MemberDTO> members = makeList(rs);
			return members.get(0);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<MemberDTO> select(String key) throws SQLException {
		return null;
	}
	
	public List<MemberDTO> select(String column, String key) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from member where " + column + "=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			List<MemberDTO> members = makeList(rs);
			return members;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public boolean checkSsn(String ssn1, String ssn2) throws SQLException {
		try {
			con = ds.getConnection();
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
			if(con != null) con.close();
		}
	}
	
	public List<MemberDTO> searchAll() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from member";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<MemberDTO> members = makeList(rs);
			return members;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<MemberDTO> search(String key) throws SQLException {
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		try {
			con = ds.getConnection();
			key = "%" + key + "%";
			String sql = "select * from member where name like ? or id like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, key);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<MemberDTO> search(String column, String key) throws SQLException {
		if(column == null || column.trim().equals("")) {
			return search(key);
		}
		
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		try {
			con = ds.getConnection();
			key = "%" + key + "%";
			String sql = "select * from member where " + column + " like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public int update(MemberDTO member) throws SQLException {
		try {
			con = ds.getConnection();
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
			if(con != null) con.close();
		}
	}
	
	public int delete(int no) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "delete from member where no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			int res = ps.executeUpdate();
			return res;
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	protected List<MemberDTO> makeList(ResultSet rs) throws SQLException {
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
