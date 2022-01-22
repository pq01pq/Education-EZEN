package student.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import student.dto.StudentDTO;

public class StudentDAOImpl implements StudentDAO {

	private JdbcTemplate jdbcTemplate;
	private RowMapper<StudentDTO> rowMapper;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public StudentDAOImpl() {
		rowMapper = new RowMapper<StudentDTO>() {
			@Override
			public StudentDTO mapRow(ResultSet rs, int rownum) throws SQLException {
				StudentDTO student = new StudentDTO();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setCname(rs.getString("cname"));
				return student;
			}
		};
	}

	@Override
	public List<StudentDTO> listStudent() {
		String sql = "select * from student";
		List<StudentDTO> students = jdbcTemplate.query(sql, rowMapper);
		return students;
	}

	@Override
	public List<StudentDTO> findStudent(String name) {
		String sql = "select * from student where name like ?";
		Object[] args = new Object[] {"%" + name + "%"};
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	@Override
	public int insertStudent(StudentDTO student) {
		String sql = "insert into student(id,name,cname) values(?,?,?)";
		Object[] args = new Object[] {student.getId(), student.getName(), student.getCname()};
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int deleteStudent(String id) {
		String sql = "delete from student where id=?";
		Object[] args = new Object[] {id};
		return jdbcTemplate.update(sql, args);
	}

}
