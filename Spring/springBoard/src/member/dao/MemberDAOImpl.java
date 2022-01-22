package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import member.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
	
	RowMapper<MemberDTO> rowMapper;
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public MemberDAOImpl() {
		rowMapper = new RowMapper<MemberDTO>() {
			@Override
			public MemberDTO mapRow(ResultSet rs, int rownum) throws SQLException {
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
				return member;
			}
		};
	}

	@Override
	public int insert(MemberDTO member) {
		String sql = "insert into member"
				+ "(no,name,id,passwd,ssn1,ssn2,email,hp1,hp2,hp3,joindate) "
				+ "values(member_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] args = new Object[] {
				member.getName(),
				member.getId(),
				member.getPasswd(),
				member.getSsn1(),
				member.getSsn2(),
				member.getEmail(),
				member.getHp1(),
				member.getHp2(),
				member.getHp3()
		};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public MemberDTO selectNo(int key) {
		String sql = "select * from member where no=?";
		Object[] args = new Object[] {Integer.valueOf(key)};
		return jdbcTemplate.queryForObject(sql, rowMapper, args);
	}

	@Override
	public List<MemberDTO> selectAll() {
		return searchAll();
	}

	@Override
	public List<MemberDTO> select(String key) {
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		String sql = "select * from member where id=?";
		Object[] args = new Object[] {key};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public List<MemberDTO> select(String column, String key) {
		if(column == null || column.trim().equals("")) {
			return select(key);
		}
		
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		String sql = "select * from member where " + column + "=?";
		Object[] args = new Object[] {key};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public boolean checkSsn(String ssn1, String ssn2) {
		String sql = "select * from member where ssn1=? and ssn2=?";
		Object[] args = new Object[] {ssn1, ssn2};
		List<MemberDTO> members = jdbcTemplate.query(sql, rowMapper, args);
		return members.size() > 0;
	}

	@Override
	public List<MemberDTO> searchAll() {
		String sql = "select * from member";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<MemberDTO> search(String key) {
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		key = "%" + key + "%";
		String sql = "select * from member where name like ? or id like ?";
		Object[] args = new Object[] {key, key};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public List<MemberDTO> search(String column, String key) {
		if(column == null || column.trim().equals("")) {
			return search(key);
		}
		
		if(key == null || key.trim().equals("")) {
			return null;
		}
		
		key = "%" + key + "%";
		String sql = "select * from member where " + column + " like ?";
		Object[] args = new Object[] {key};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public int update(MemberDTO member) {
		String sql = "update member set "
				+ "passwd=?,email=?,hp1=?,hp2=?,hp3=? "
				+ "where no=?";
		Object[] args = new Object[] {
				member.getPasswd(),
				member.getEmail(),
				member.getHp1(),
				member.getHp2(),
				member.getHp3(),
				member.getNo()
		};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(int no) {
		String sql = "delete from member where no=?";
		Object[] args = new Object[] {Integer.valueOf(no)};
		return jdbcTemplate.update(sql, args);
	}

}
