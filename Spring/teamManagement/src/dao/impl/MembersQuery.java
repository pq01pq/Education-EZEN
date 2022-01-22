package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import dao.TeamDAO;
import dto.MemberDTO;
import dto.TeamDTO;

public class MembersQuery extends MappingSqlQuery<MemberDTO> {
	
	private static String sql = "select member_id, sts_member.name as memberName, "
			+ "sts_team.team_id, sts_team.name as teamName from sts_member, sts_team where "
			+ "sts_member.team_id=sts_team.team_id and sts_team.team_id=?";
	
	public MembersQuery(DataSource dataSource) {
		super(dataSource, sql);
		super.declareParameter(new SqlParameter("team_id", Types.INTEGER));
		compile();
	}

	@Override
	protected MemberDTO mapRow(ResultSet rs, int rownum) throws SQLException {
		MemberDTO member = new MemberDTO();
		member.setId(rs.getInt("member_id"));
		member.setName(rs.getString("memberName"));
		TeamDTO team = new TeamDTO();
		team.setId(rs.getInt("team_id"));
		team.setName(rs.getString("teamName"));
		member.setTeam(team);
		return member;
	}
}
