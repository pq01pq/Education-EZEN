package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberDAOImpl extends SimpleJdbcTemplate implements MemberDAO {
	
	InsertMember insertMember;
	MembersQuery membersQuery;
	MemberQuery memberQuery;
	DeleteMember deleteMember;
	UpdateMember updateMember;
	
	public MemberDAOImpl(DataSource dataSource) {
		super(dataSource);
		insertMember = new InsertMember(dataSource);
		membersQuery = new MembersQuery(dataSource);
		memberQuery = new MemberQuery(dataSource);
		deleteMember = new DeleteMember(dataSource);
		updateMember = new UpdateMember(dataSource);
	}

	@Override
	public void insertMember(MemberDTO member) throws DataAccessException {
		Object[] params = new Object[] {member.getName(), member.getTeam().getId()};
		insertMember.update(params);
	}

	@Override
	public void deleteMember(MemberDTO member) throws DataAccessException {
		Object[] params = new Object[] {member.getId()};
		deleteMember.update(params);
	}

	@Override
	public void updateMember(MemberDTO member) throws DataAccessException {
		Object[] params = new Object[] {member.getTeam().getId(), member.getId()};
		updateMember.update(params);
	}

	@Override
	public MemberDTO getMember(Integer id) throws DataAccessException {
		return memberQuery.findObject(id);
	}

	@Override
	public List<MemberDTO> getMembers(Integer id) throws DataAccessException {
		return membersQuery.execute(id);
	}

}
