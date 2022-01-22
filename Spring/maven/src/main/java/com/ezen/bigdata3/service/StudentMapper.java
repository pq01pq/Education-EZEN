package com.ezen.bigdata3.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.bigdata3.model.StudentDTO;

@Service
public class StudentMapper {

	@Autowired
	private SqlSession sqlSession;
	
	public List<StudentDTO> listStudent(){
		return sqlSession.selectList("listStudent"); // xml의 id
	}
	
	public int insertStudent(StudentDTO student){
		int res = sqlSession.insert("insertStudent", student);
		sqlSession.commit();
		return res;
	}
	
	public int deleteStudent(String id){
		int res = sqlSession.delete("deleteStudent", id);
		sqlSession.commit();
		return res;
	}
	
	public List<StudentDTO> findStudent(String key){
		return sqlSession.selectList("findStudent", key);
	}
}
