package sugang.server.service;

import java.sql.*;
import java.util.*;

import sugang.model.*;
import sugang.server.repository.*;

public class SubjectService implements IService<SubjectVO> {
	
	private IDAO<SubjectVO> subjectDAO;
	
	public SubjectService(Connection connection) {
		super();
		this.subjectDAO = new SubjectDAO(connection);
	}

	@Override
	public boolean insert(SubjectVO element) {
	// 강의코드 : primary key (중복 비허용)
	ArrayList<SubjectVO> subjects = subjectDAO.select(element.getCode());
		if(subjects.size() > 0) {
			return false;
		}
				
		return subjectDAO.insert(element);
	}
	
	@Override
	public ArrayList<SubjectVO> select(String column, String key) {
		return subjectDAO.select(column, key);
	}
	
	@Override
	public ArrayList<SubjectVO> select(String primaryKey) {
		return subjectDAO.select(primaryKey);
	}

	@Override
	public ArrayList<SubjectVO> search(String option, String key) {
		String column;
		switch(option) {
		case "강의명":
			column = "title";
			break;
		case "강의코드":
			column = "code";
			break;
		case "교수명":
			column = "professor";
			break;
		case "강의실":
			column = "room";
			break;
		case "요일":
			column = "schedule";
			break;
		default :
			column = "";
		}
		
		key = "%" + key + "%";
		
		if(column.equals("")) {
			return subjectDAO.search(key);
		} else {
			return subjectDAO.search(column, key);
		}
	}

	@Override
	public ArrayList<SubjectVO> searchAll() {
		return subjectDAO.searchAll();
	}

	@Override
	public boolean update(SubjectVO element, String column, String key) {
		ArrayList<SubjectVO> subjects = subjectDAO.select(key);
		if(subjects.size() != 1) {
			return false;
		}
		
		SubjectVO subject = subjects.get(0);
		Hashtable<String, String> updateTable = new Hashtable<>();
		if(!subject.getTitle().equals(element.getTitle())) {
			updateTable.put("title", element.getTitle());
		}
		if(!subject.getCode().equals(element.getCode())) {
			updateTable.put("code", element.getCode());
		}
		if(!subject.getProfessor().equals(element.getProfessor())) {
			updateTable.put("professor", element.getProfessor());
		}
		if(!subject.getRoom().equals(element.getRoom())) {
			updateTable.put("room", element.getRoom());
		}
		if(!subject.getSchedule().equals(element.getSchedule())) {
			updateTable.put("schedule", element.getSchedule());
		}
		if(subject.getLimit() != element.getLimit()) {
			updateTable.put("title", String.valueOf(element.getLimit()));
		}
		
		return subjectDAO.update(updateTable, column, key);
	}

	@Override
	public SubjectVO delete(String option, String key) {
		ArrayList<SubjectVO> subjects = subjectDAO.select(key);
		if(subjects.size() != 1) {
			return null;
		}
		
		SubjectVO delSubject = subjects.get(0);
		
		if(subjectDAO.delete(option, key)) {
			return delSubject;
		} else {
			return null;
		}
	}

	@Override
	public boolean register(SubjectVO element) {
//		element.setRegNumber(element.getRegNumber() + 1);
		/*
		 * 등록 학생 목록과 대기열 학생 목록 미구현
		 */
		return this.update(element, "code", element.getCode());
	}

	@Override
	public boolean cancel(SubjectVO element) {
//		element.setRegNumber(element.getRegNumber() - 1);
		/*
		 * 등록 학생 목록과 대기열 학생 목록 미구현
		 */
		return this.update(element, "code", element.getCode());
	}

}
