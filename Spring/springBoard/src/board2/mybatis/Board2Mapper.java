package board2.mybatis;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDTO;
import board2.dto.Board2DTO;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class Board2Mapper {
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
	
	public static int insert(Board2DTO article) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.insert("insert", article);
		sqlSession.commit();
		sqlSession.close();
		return res;
	}
	
	public static int count() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne("count");
		sqlSession.close();
		return count;
	}
	
	public static List<Board2DTO> selectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Board2DTO> articles = sqlSession.selectList("selectAll");
		sqlSession.close();
		return articles;
	}
	
	public static List<Board2DTO> select(String column, String key) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> map = new Hashtable<>();
		map.put("column", column);
		map.put("key", key);
		List<Board2DTO> articles = sqlSession.selectList("selectColKey", map);
		sqlSession.close();
		return articles;
	}
	
	public static List<Board2DTO> select(int num) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Board2DTO> articles = sqlSession.selectList("select");
		sqlSession.close();
		return articles;
	}
	
	public static List<Board2DTO> selectPage(int pageNum, int numPerPage) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, Integer> map = new Hashtable<>();
		map.put("start", Integer.valueOf(numPerPage * (pageNum - 1)));
		map.put("end", Integer.valueOf(numPerPage * pageNum));
		List<Board2DTO> articles = sqlSession.selectList("selectPage", map);
		sqlSession.close();
		return articles;
	}
	
	public static List<Board2DTO> searchAll() {
		return selectAll();
	}
	
	public static List<Board2DTO> search(String key) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		key = "%" + key + "%";
		List<Board2DTO> articles = sqlSession.selectList("searchKey", key);
		sqlSession.close();
		return articles;
	}
	
	public static List<Board2DTO> search(String column, String key) {
		if(key == null || key.trim().equals("")) {
			return searchAll();
		}
		
		if(column == null || column.trim().equals("")) {
			return search(key);
		}
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		key = "%" + key + "%";
		Map<String, String> map = new Hashtable<>();
		map.put("column", column);
		map.put("key", key);
		List<Board2DTO> articles = sqlSession.selectList("searchColKey", map);
		sqlSession.close();
		return articles;
	}
	
	public static int update(Board2DTO article) {
		
		String sql = "update board2 set "
				+ "email=?,subject=?,passwd=?,readcount=?,content=?,ip=?,filename=?,filesize=? "
				//		1		2		3			4			5	6			7			8
				+ "where num=?";
				//			9
		return 0;
	}
	
	public int delete(int num) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.delete("delete", num);
		sqlSession.commit();
		sqlSession.close();
		return res;
	}
	
}
