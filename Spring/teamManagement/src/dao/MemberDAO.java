package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dto.MemberDTO;

public interface MemberDAO {
	void insertMember(MemberDTO member) throws DataAccessException;
	void deleteMember(MemberDTO member) throws DataAccessException;
	void updateMember(MemberDTO member) throws DataAccessException;
	MemberDTO getMember(Integer id) throws DataAccessException;
	List<MemberDTO> getMembers(Integer id) throws DataAccessException;
}
