package com.ezen.bigdata3.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ezen.bigdata3.model.BoardDTO;

public class BoardDAOImpl implements BoardDAO {
	
	protected RowMapper<BoardDTO> rowMapper;
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public BoardDAOImpl() {
		rowMapper = new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rownum) throws SQLException {
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
				return article;
			}
		};
	}

	@Override
	public int insert(BoardDTO article) throws DataAccessException {
		if(article.getParentNum() > -1) {
			increaseGroupOf(article);	// 답글
		}
		
		String sql = "insert into board"
				+ "(num,writer,email,subject,passwd,reg_date,content,ip,re_step,re_level,re_group,parent_num) "
					//    1     2      3        4               5    6     7       8        9        10
				+ "values(board_seq.nextval,?,?,?,?,sysdate,?,?,?,?,?,?)";
										//  1 2 3 4         5 6 7 8 9 10
		Object[] args = new Object[] {
				article.getWriter(),	// 1
				article.getEmail(),		// 2
				article.getSubject(),	// 3
				article.getPasswd(),	// 4
				article.getContent(),	// 5
				article.getIp(),		// 6
				article.getReStep(),	// 7
				article.getReLevel(),	// 8
				article.getReGroup(),	// 9
				article.getParentNum()	// 10
		};
		return jdbcTemplate.update(sql, args);
	}
	
	private int increaseGroupOf(BoardDTO article) throws DataAccessException {
		String sql = "update board "
				+ "set re_step=re_step+1 "
				+ "where re_group=? and re_step>=?";
		Object[] args = new Object[] {
				article.getReGroup(),
				article.getReStep()
		};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<BoardDTO> selectAll() throws DataAccessException {
		String sql = "select * from board order by re_group desc, re_step asc";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<BoardDTO> select(String column, String key) throws DataAccessException {
		if(column == null || column.trim().equals("")) {
			try {
				return select(Integer.parseInt(key));
			} catch(NullPointerException | NumberFormatException e) {
				return null;
			}
		}
		String sql = "select * from board where " + column + "=? order by re_group desc, re_step asc";
		Object[] args = new Object[] {key};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public List<BoardDTO> select(int num) throws DataAccessException {
		String sql = "select * from board where num=? order by re_group desc, re_step asc";
		Object[] args = new Object[] {Integer.valueOf(num)};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public List<BoardDTO> selectPage(int pageNum, int numPerPage) throws DataAccessException {
		String sql =	"select * from ("
							+ "select rownum rnum,b.* from ("
								+ "select * from board order by re_group desc, re_step asc"
							+ ") b"
						+ ") where rnum>? and rnum<=?";
		Object[] args = new Object[] {
				Integer.valueOf(numPerPage * (pageNum - 1)),
				Integer.valueOf(numPerPage * pageNum)
		};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public List<BoardDTO> searchAll() throws DataAccessException {
		return selectAll();
	}

	@Override
	public List<BoardDTO> search(String key) throws DataAccessException {
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		key = "%" + key + "%";
		String sql = "select * from board where "
				+ "subject like ? or "
				+ "content like ? "
				+ "order by re_group desc, re_step asc";
		Object[] args = new Object[] {key, key};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public List<BoardDTO> search(String column, String key) throws DataAccessException {
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		if(column == null || column.trim().equals("")) {
			return search(key);
		}
		
		key = "%" + key + "%";
		String sql = "select * from board where " + column + " like ? order by re_group desc, re_step asc";
		Object[] args = new Object[] {key};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public int update(BoardDTO article) throws DataAccessException {
		String sql = "update board set "
				+ "email=?,subject=?,passwd=?,readcount=?,content=?,ip=? "
				+ "where num=?";
		Object[] args = new Object[] {
				article.getEmail(),
				article.getSubject(),
				article.getPasswd(),
				article.getReadCount(),
				article.getContent(),
				article.getIp(),
				article.getNum()
		};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(int num) throws DataAccessException {
		
		String sql = "delete from board where num=?";
		Object[] args = new Object[] {Integer.valueOf(num)};
		return jdbcTemplate.update(sql, args);
	}
	
//	private int decreaseGroupOf(BoardDTO article) throws DataAccessException {
//		String sql = "update board "
//				+ "set re_step=re_step-1 "
//				+ "where re_group=? and re_step>?";
//		Object[] args = new Object[] {
//				article.getReGroup(),
//				article.getReStep()
//		};
//		return jdbcTemplate.update(sql, args);
//	}

	@Override
	public int count() throws DataAccessException {
		String sql = "select count(*) from board";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public int maxGroup() throws DataAccessException {
		String sql = "select max(re_group) from board";
		String maxGroup = jdbcTemplate.queryForObject(sql, String.class);
		if(maxGroup == null) {
			return -1;
		} else {
			return Integer.parseInt(maxGroup);
		}
	}
	
//	public static void main(String[] args) {
//		String sql = "select max(i) from test2";
//		JdbcTemplate template = new JdbcTemplate(new DriverManagerDataSource("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "bigdata3", "bigdata3"));
//		int i = template.queryForInt(sql);
//		System.out.println(i);
//		String s = template.queryForObject(sql, String.class);
//		System.out.println(s);
//		System.out.println("null");
//	}

}
