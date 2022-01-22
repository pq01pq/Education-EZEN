package sugang.server.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

import sugang.model.*;
import sugang.server.repository.*;

public class StudentService implements IService<StudentVO> {
	
	private IDAO<StudentVO> studentDAO;
	
	public StudentService(Connection connection) {
		super();
		this.studentDAO = new StudentDAO(connection);
	}

	@Override
	public boolean insert(StudentVO element) {
		return studentDAO.insert(element);
	}
	
	@Override
	public ArrayList<StudentVO> select(String column, String key) {
		return studentDAO.select(column, key);
	}
	
	@Override
	public ArrayList<StudentVO> select(String primaryKey) {
		return studentDAO.select(primaryKey);
	}

	@Override
	public ArrayList<StudentVO> search(String option, String key) {
		return studentDAO.search(option, key);
	}

	@Override
	public ArrayList<StudentVO> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(StudentVO element, String column, String key) {
		ArrayList<String> subjectCodes = element.getSubjectCodes();
		String codeString = "";
		if(subjectCodes != null) {
			for(int i = 0; i < subjectCodes.size(); i++) {
				codeString += subjectCodes.get(i);
				if(i < subjectCodes.size() - 1) {
					codeString += ",";
				}
			}
		}
		Hashtable<String, String> updateTable = new Hashtable<>();
		updateTable.put("subjects", codeString);
		
		return studentDAO.update(updateTable, column, key);
	}

	@Override
	public StudentVO delete(String option, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean register(StudentVO student) {
		ArrayList<StudentVO> students = studentDAO.select(student.getId());
		if(students.size() != 1) {
			System.out.println("학생 검색 실패");
			return false;
		}
		
		StudentVO storedStudent = students.get(0);
		
		System.out.println(storedStudent.getId());
		
		ArrayList<String> subjectCodes = storedStudent.getSubjectCodes();
		if(subjectCodes == null) {
			subjectCodes = new ArrayList<>();
		}
		subjectCodes.add(student.getCode());
		storedStudent.setSubjectCodes(subjectCodes);
		
		return this.update(storedStudent, "id", student.getId());
	}

	public boolean cancel(StudentVO student) {
		ArrayList<StudentVO> students = studentDAO.select(student.getId());
		if(students.size() != 1) {
			System.out.println("학생 검색 실패");
			return false;
		}
		
		StudentVO storedStudent = students.get(0);
		ArrayList<String> subjectCodes = storedStudent.getSubjectCodes();
		if(subjectCodes == null) {
			subjectCodes = new ArrayList<>();
		}
		subjectCodes.remove(student.getCode());
		storedStudent.setSubjectCodes(subjectCodes);
		
		return this.update(storedStudent, "id", student.getId());
	}

}
