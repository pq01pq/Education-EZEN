package dao.impl;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import dao.TeamDAO;
import dto.TeamDTO;

public class TeamDAOImpl extends SimpleJdbcTemplate implements TeamDAO {

	protected RowMapper<TeamDTO> mapper;

	public TeamDAOImpl(DataSource dataSource) {
		super(dataSource);
		mapper = new RowMapper<TeamDTO>() {
			@Override
			public TeamDTO mapRow(ResultSet rs, int rownum) throws SQLException {
				TeamDTO team = new TeamDTO();
				team.setId(rs.getInt("team_id"));
				team.setName(rs.getString("name"));
				return team;
			}
		};
	}

	@Override
	public List<TeamDTO> getTeamList() throws DataAccessException {
		String sql = "select * from sts_team";
		List<TeamDTO> teams = this.query(sql, mapper);
		return teams;
	}

	@Override
	public TeamDTO getTeam(Integer id) throws DataAccessException {
		String sql = "select * from sts_team where team_id=?";
		Object[] args = new Object[] {id};
		TeamDTO team = this.queryForObject(sql, mapper, args);
		return team;
	}

}
