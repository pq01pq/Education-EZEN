package sugang.server.controller;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import sugang.model.*;
import sugang.server.service.*;
import sugang.server.view.*;

public class Server {
	
	private Connection connection;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user, password;
	
	private IService<SubjectVO> subjectService;
	private IService<StudentVO> studentService;
	
	private ServerSocket serverSocket;
	private ArrayList<ClientSession> clientSessions;
	
//	private SubjectView viewPage;
	
//	private String name;

	public Server(String name, String user, String pass) {
		super();
//		this.name = name;
		this.user = user;
		this.password = pass;
		
		init();
		
		subjectService = new SubjectService(connection);
		studentService = new StudentService(connection);
		
		new ServerView(name, this);
		
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				clientSessions.add(new ClientSession(socket, this));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, user, password);
			
			serverSocket = new ServerSocket(10000);
			clientSessions = new ArrayList<>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertSubject(SubjectVO subject) {
		return subjectService.insert(subject);
	}
	
	public ArrayList<SubjectVO> selectSubjects(String column, String key) {
		if(column.equals("code")) {
			return subjectService.select(key);
		} else {
			return subjectService.select(column, key);
		}
	}
	
	public ArrayList<SubjectVO> searchSubjects(String option, String keyword) {
		return subjectService.search(option, keyword);
	}
	
	public ArrayList<SubjectVO> searchAllSubjects() {
		return subjectService.searchAll();
	}
	
	public boolean updateSubject(StudentVO student) {
		ArrayList<SubjectVO> subjects = selectSubjects("code", student.getCode());
		if(subjects.size() != 1) {
			return false;
		}
		SubjectVO subject = subjects.get(0);
		
		switch(student.getFunction()) {
		case StudentVO.REGISTER:
			return subjectService.register(subject);
		case StudentVO.CANCEL:
			return subjectService.cancel(subject);
		default:
			return false;
		}
	}
	
	public SubjectVO deleteSubject(String code) {
		return subjectService.delete("code", code);
	}
	
	public boolean insertStudent(StudentVO student) {
		return studentService.insert(student);
	}
	
	public ArrayList<StudentVO> selectStudent(String id) {
		return studentService.select("id", id);
	}
	
	public boolean updateStudent(StudentVO student) {
		switch(student.getFunction()) {
		case StudentVO.REGISTER:
			return studentService.register(student);
		case StudentVO.CANCEL:
			return studentService.cancel(student);
		default:
			return false;
		}
	}
	
	public static void main(String[] args) {
		new Server("수강신청 시스템 관리자", "bigdata3", "bigdata3");
	}

}
