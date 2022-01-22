package member.mybatis;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import member.dto.MemberDTO;

public class MemberMapper {

	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "/Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch(IOException e) {
			throw new RuntimeException("Configuration.xml 파일 빌드 실패" + e, e);
		}
	}
	
	public static int insert(MemberDTO member) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.insert("insert", member);
		sqlSession.commit();
		sqlSession.close();
		return res;
	}
	
	public static List<MemberDTO> selectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<MemberDTO> students = sqlSession.selectList("searchAll");
		sqlSession.close();
		return students;
	}
	
	public static MemberDTO selectNo(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO student = sqlSession.selectOne("selectNo", no);
		sqlSession.close();
		return student;
	}
	
	public static List<MemberDTO> select(String key) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<MemberDTO> students = sqlSession.selectList("selectKey", key);
		sqlSession.close();
		return students;
	}
	
	public static List<MemberDTO> select(String column, String key) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> map = new Hashtable<String, String>();
		map.put("column", column);
		map.put("key", key);
		List<MemberDTO> students = sqlSession.selectList("selectColKey", map);
		sqlSession.close();
		return students;
	}
	
	public static List<MemberDTO> searchAll(){
		return selectAll();
	}
	
	public static List<MemberDTO> search(String key) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<MemberDTO> students = sqlSession.selectList("searchKey", key);
		sqlSession.close();
		return students;
	}
	
	public static List<MemberDTO> search(String column, String key) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> map = new Hashtable<String, String>();
		map.put("column", column);
		map.put("key", key);
		List<MemberDTO> students = sqlSession.selectList("searchColKey", map);
		sqlSession.close();
		return students;
	}
	
	public static boolean checkSsn(String ssn1, String ssn2) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> map = new Hashtable<String, String>();
		map.put("ssn1", ssn1);
		map.put("ssn2", ssn2);
		List<MemberDTO> members = sqlSession.selectList("checkSsn", map);
		sqlSession.close();
		return members.size() > 0;
	}
	
	public static int update(MemberDTO member) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.update("update", member);
		sqlSession.commit();
		sqlSession.close();
		return res;
	}
	
	public static int delete(int no){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.delete("delete", no);
		sqlSession.commit();
		sqlSession.close();
		return res;
	}
	
}
