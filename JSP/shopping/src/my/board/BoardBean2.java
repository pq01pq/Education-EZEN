package my.board;

import java.util.*;

import com.oreilly.servlet.MultipartRequest;

import java.io.File;
import java.sql.*;

import my.db.ConnectionPoolBean;

public class BoardBean2 {
	private ConnectionPoolBean pool;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public BoardBean2() throws Exception {
		pool = new ConnectionPoolBean();
	}
	
	public int insert(BoardDTO2 article) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "insert into board2"
					+ "(num,writer,email,subject,passwd,reg_date,content,ip,filename,filesize) "
						//	1		2		3		4				5		6	7		8
					+ "values(board2_seq.nextval,?,?,?,?,sysdate,?,?,?,?)";
											 //  1 2 3 4         5 6 7 8
			ps = con.prepareStatement(sql);
			ps.setString(1, article.getWriter());
			ps.setString(2, article.getEmail());
			ps.setString(3, article.getSubject());
			ps.setString(4, article.getPasswd());
			ps.setString(5, article.getContent());
			ps.setString(6, article.getIp());
			ps.setString(7, article.getFileName());
			ps.setLong(8, article.getFileSize());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public int insert(MultipartRequest mr) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "insert into board2"
					+ "(num,writer,email,subject,passwd,reg_date,content,ip,filename,filesize) "
						//	1		2		3		4				5		6	7		8
					+ "values(board2_seq.nextval,?,?,?,?,sysdate,?,?,?,?)";
											 //  1 2 3 4         5 6 7 8
			ps = con.prepareStatement(sql);
			ps.setString(1, mr.getParameter("writer"));
			ps.setString(2, mr.getParameter("email"));
			ps.setString(3, mr.getParameter("subject"));
			ps.setString(4, mr.getParameter("passwd"));
			ps.setString(5, mr.getParameter("content"));
			ps.setString(6, mr.getParameter("ip"));
			ps.setString(7, mr.getFilesystemName("fileName"));
			File file = mr.getFile("fileName");
			long fileSize = 0L;
			if(file != null){
				fileSize = file.length();
			}
			ps.setLong(8, fileSize);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public int count() throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select count(*) from board2";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<BoardDTO2> selectAll() throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from board2 order by num desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<BoardDTO2> select(String column, String key) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from board2 where " + column + "=? order by num desc";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<BoardDTO2> select(int num) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from board2 where num=? order by num desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<BoardDTO2> selectPage(int pageNum, int totalArticlesNum, int numPerPage) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from "
					+ "(select rownum as rnum,b.* from board2 b) "
					+ "where rnum>? and rnum<=? "
					+ "order by num desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, totalArticlesNum - numPerPage * pageNum);
			ps.setInt(2, totalArticlesNum - numPerPage * (pageNum - 1));
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<BoardDTO2> searchAll() throws SQLException {
		return selectAll();
	}
	
	public List<BoardDTO2> search(String key) throws SQLException {
		try {
			con = pool.getConnection();
			key = "%" + key + "%";
			String sql = "select * from board2 where "
					+ "subject like ? or "
					+ "content like ? "
					+ "order by num desc";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, key);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<BoardDTO2> search(String column, String key) throws SQLException {
		if(key == null || key.trim().equals("")) {
			return searchAll();
		}
		
		if(column == null || column.trim().equals("")) {
			return search(key);
		}
		
		try {
			con = pool.getConnection();
			key = "%" + key + "%";
			String sql = "select * from board2 where " + column + " like ? order by num desc";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public int update(BoardDTO2 article) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "update board2 set "
					+ "email=?,subject=?,passwd=?,readcount=?,content=?,ip=?,filename=?,filesize=?"
					//		1		2		3			4			5	6			7			8
					+ "where num=?";
					//			9
			ps = con.prepareStatement(sql);
			ps.setString(1, article.getEmail());
			ps.setString(2, article.getSubject());
			ps.setString(3, article.getPasswd());
			ps.setInt(4, article.getReadCount());
			ps.setString(5, article.getContent());
			ps.setString(6, article.getIp());
			ps.setString(7, article.getFileName());
			ps.setLong(8, article.getFileSize());
			ps.setInt(9, article.getNum());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public int update(MultipartRequest mr) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "update board2 set "
					+ "email=?,subject=?,passwd=?,readcount=?,content=?,ip=?,filename=?,filesize=?"
					//		1		2		3			4			5	6			7			8
					+ "where num=?";
					//			9
			ps = con.prepareStatement(sql);
			ps.setString(1, mr.getParameter("email"));
			ps.setString(2, mr.getParameter("subject"));
			ps.setString(3, mr.getParameter("passwd"));
			ps.setInt(4, Integer.parseInt(mr.getParameter("readCount")));
			ps.setString(5, mr.getParameter("content"));
			ps.setString(6, mr.getParameter("ip"));
			String newFileName = mr.getFilesystemName("fileName");
			if(newFileName == null || newFileName.trim().equals("")) {
				newFileName = mr.getParameter("preFileName");
			}
			ps.setString(7, newFileName);
			File file = mr.getFile(newFileName);
			long fileSize = 0L;
			if(file != null){
				fileSize = file.length();
			}
			ps.setLong(8, fileSize);
			ps.setInt(9, Integer.parseInt(mr.getParameter("num")));
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public int delete(int num) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "delete from board2 where num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	private List<BoardDTO2> list(ResultSet rs) throws SQLException {
		List<BoardDTO2> articles = new ArrayList<>();
		while(rs.next()) {
			BoardDTO2 article = new BoardDTO2();
			article.setNum(rs.getInt("num"));
			article.setWriter(rs.getString("writer"));
			article.setEmail(rs.getString("email"));
			article.setSubject(rs.getString("subject"));
			article.setPasswd(rs.getString("passwd"));
			article.setRegDate(rs.getString("reg_date"));
			article.setReadCount(rs.getInt("readcount"));
			article.setContent(rs.getString("content"));
			article.setIp(rs.getString("ip"));
			article.setFileName(rs.getString("filename"));
			article.setFileSize(rs.getInt("filesize"));
			articles.add(article);
		}
		return articles;
	}
	
	public ConnectionPoolBean getPool() {
		return pool;
	}

	public void setPool(ConnectionPoolBean pool) {
		this.pool = pool;
	}
	
}
