package student.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import student.dto.StudentDTO;

public class StudentMapper {

	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "org/mybatis/example/Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch(IOException e) {
			throw new RuntimeException("Configuration.xml 파일 빌드 실패" + e, e);
		}
	}
	
	public static List<StudentDTO> listStudent(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<StudentDTO> students = sqlSession.selectList("listStudent"); // xml의 id
		sqlSession.close();
		return students;
	}
	
	public static int insertStudent(StudentDTO student){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.insert("insertStudent", student);
		sqlSession.commit();
		sqlSession.close();
		return res;
	}
	
	public static int deleteStudent(String id){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.delete("deleteStudent", id);
		sqlSession.commit();
		sqlSession.close();
		return res;
	}
	
	public static List<StudentDTO> findStudent(String key){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<StudentDTO> students = sqlSession.selectList("findStudent", key);
		sqlSession.close();
		return students;
	}
}
