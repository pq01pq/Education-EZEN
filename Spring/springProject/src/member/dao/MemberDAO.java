package member.dao;

import java.util.List;

import member.dto.MemberDTO;

public interface MemberDAO {
	public int insert(MemberDTO member);
	public MemberDTO selectNo(int key);
	public List<MemberDTO> selectAll();
	public List<MemberDTO> select(String key);
	public List<MemberDTO> select(String column, String key);
	public boolean checkSsn(String ssn1, String ssn2);
	public List<MemberDTO> searchAll();
	public List<MemberDTO> search(String key);
	public List<MemberDTO> search(String column, String key);
	public int update(MemberDTO member);
	public int delete(int no);
}
