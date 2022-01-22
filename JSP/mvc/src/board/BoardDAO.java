package board;

import java.util.*;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;

public class BoardDAO {
	private static DataSource ds;
	static {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		} catch(NamingException e) {
			System.err.println("lookup실패" + e.getMessage());
		}
	}
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public BoardDAO() {}
	
	public int insert(BoardDTO article) throws SQLException {
		if(article.getParentNum() > -1) {
			updateGroupOf(article);	// 답글
		}
		
		try {
			con = ds.getConnection();
			String sql = "insert into board"
					+ "(num,writer,email,subject,passwd,reg_date,content,ip,re_step,re_level,re_group,parent_num) "
						//	1		2		3		4				5	6		7		8		9		10
					+ "values(board_seq.nextval,?,?,?,?,sysdate,?,?,?,?,?,?)";
											//  1 2 3 4         5 6 7 8 9 10
			ps = con.prepareStatement(sql);
			ps.setString(1, article.getWriter());
			ps.setString(2, article.getEmail());
			ps.setString(3, article.getSubject());
			ps.setString(4, article.getPasswd());
			ps.setString(5, article.getContent());
			ps.setString(6, article.getIp());
			ps.setInt(7, article.getReStep());
			ps.setInt(8, article.getReLevel());
			ps.setInt(9, article.getReGroup());
			ps.setInt(10, article.getParentNum());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	private int updateGroupOf(BoardDTO article) throws SQLException {
		// 부모와 같은 그룹의 글들을 부모의 스텝을 기준으로 스텝을 변경함
		try {
			con = ds.getConnection();
			String sql = "update board "
					+ "set re_step=re_step+1 "
					+ "where re_group=? and re_step>=?"; 
			ps = con.prepareStatement(sql);
			ps.setInt(1, article.getReGroup());
			ps.setInt(2, article.getReStep());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<BoardDTO> selectAll() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from board order by re_group desc, re_step asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<BoardDTO> select(String column, String key) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from board where " + column + "=? order by re_group desc, re_step asc";
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
	
	public List<BoardDTO> select(int num) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from board where num=? order by re_group desc, re_step asc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<BoardDTO> selectPage(int pageNum, int numPerPage) throws SQLException {
		try {
			con = ds.getConnection();
			// rnum의 범위에 의해 선택(rnum과 모든것을 선택(정렬해서 선택))
			String sql =	"select * from ("
								+ "select rownum rnum,b.* from ("
									+ "select * from board order by re_group desc, re_step asc"
								+ ") b"
							+ ") where rnum>? and rnum<=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, numPerPage * (pageNum - 1));
			ps.setInt(2, numPerPage * pageNum);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<BoardDTO> searchAll() throws SQLException {
		return selectAll();
	}
	
	public List<BoardDTO> search(String key) throws SQLException {
		try {
			con = ds.getConnection();
			key = "%" + key + "%";
			String sql = "select * from board where "
					+ "subject like ? or "
					+ "content like ? "
					+ "order by re_group desc, re_step asc";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, key);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<BoardDTO> search(String column, String key) throws SQLException {
		if(key == null || key.trim().equals("")) {
			return searchAll();
		}
		
		if(column == null || column.trim().equals("")) {
			return search(key);
		}
		
		try {
			con = ds.getConnection();
			key = "%" + key + "%";
			String sql = "select * from board where " + column + " like ? order by re_group desc, re_step asc";
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
	
	public int update(BoardDTO article) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "update board set "
					+ "email=?,subject=?,passwd=?,readcount=?,content=?,ip=? "
					+ "where num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, article.getEmail());
			ps.setString(2, article.getSubject());
			ps.setString(3, article.getPasswd());
			ps.setInt(4, article.getReadCount());
			ps.setString(5, article.getContent());
			ps.setString(6, article.getIp());
			ps.setInt(7, article.getNum());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public int delete(int num) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "delete from board where num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public int count() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select count(*) from board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public int maxGroup() throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select max(re_group) from board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			if(rs.getString(1) == null) {
				return -1;
			} else {
				return rs.getInt(1);
			}
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	private List<BoardDTO> list(ResultSet rs) throws SQLException {
		List<BoardDTO> articles = new ArrayList<>();
		while(rs.next()) {
			BoardDTO article = new BoardDTO();
			article.setNum(rs.getInt("num"));
			article.setWriter(rs.getString("writer"));
			article.setEmail(rs.getString("email"));
			article.setSubject(rs.getString("subject"));
			article.setPasswd(rs.getString("passwd"));
			article.setRegDate(rs.getString("reg_date"));
			article.setReadCount(rs.getInt("readcount"));
			article.setContent(rs.getString("content"));
			article.setIp(rs.getString("ip"));
			article.setReStep(rs.getInt("re_step"));
			article.setReLevel(rs.getInt("re_level"));
			article.setReGroup(rs.getInt("re_group"));
			article.setParentNum(rs.getInt("parent_num"));
			articles.add(article);
		}
		return articles;
	}
	
}
