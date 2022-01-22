package sugang.server.controller;

import java.io.*;
import java.net.*;
import java.util.*;

import sugang.model.*;

public class ClientSession implements Runnable {
	
	private Socket socket;
	private Server server;
	
	byte[] data;
	private ByteArrayInputStream bais;
	private ByteArrayOutputStream baos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private String name, id;
	private ArrayList<SubjectVO> subjects;
	
	public ClientSession(Socket socket, Server server) {
		super();
		this.socket = socket;
		this.server = server;
		subjects = new ArrayList<>();
		init();
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			StudentVO student = receive();
			switch(student.getFunction()) {
			case StudentVO.REGISTER:
			case StudentVO.CANCEL:
//				System.out.println(student.getCode()); // error check
				server.updateStudent(student);
//				server.updateSubject(student); // 미구현
				
				subjects = server.searchSubjects(student.getOption(), student.getSearchKey()); // 화면에 띄울 강의 목록
//				for(SubjectVO subject : subjects) { // error check
//					System.out.println(subject.getCode());
//				}
				ArrayList<StudentVO> students = server.selectStudent(student.getId());
				ArrayList<String> subjectCodes = students.get(0).getSubjectCodes(); // 신청한 강의 목록
//				for(String code : subjectCodes) { // error check
//					System.out.println(code);
//				}
				if(subjectCodes != null) {
					for(int i = 0; i < subjects.size(); i++) {
						for(int j = 0; j < subjectCodes.size(); j++) {
							if(subjects.get(i).getCode().equals(subjectCodes.get(j))) {
								subjects.get(i).setRegistered(true);
							}
						}
					}
				}
				
				send(subjects);
				break;
				
			case StudentVO.SEARCH:
				send(server.searchSubjects(student.getOption(), student.getSearchKey()));
				break;
				
			default :
			}
			
		}
	}
	
	public boolean send(ArrayList<SubjectVO> subjects) {
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(new BufferedOutputStream(baos));
			oos.writeObject(subjects);
			oos.flush();
			socket.getOutputStream().write(baos.toByteArray());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public StudentVO receive() {
		try {
			data = new byte[65508];
			socket.getInputStream().read(data);
			bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(new BufferedInputStream(bais));
			return (StudentVO)ois.readObject();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void init() {
		// 서버 연결시 객체 초기화하는 과정
		// 1. 클라이언트에게 전체 과목을 전달함
		send(server.searchAllSubjects());
		System.out.println("1. 강의목록 전송");
		// 4. 클라이언트에게 이름과 학번 정보를 받음
		StudentVO student = receive();
		System.out.println("4. 학생정보 수신");
		
		this.name = student.getName();
		this.id = student.getId();
		
		if(server.selectStudent(id).size() == 0) {
			if(server.insertStudent(student)) {
				System.out.println("학생정보 등록 성공");
			} else {
				System.out.println("학생정보 등록 실패");
			}
		}
	}
	
}
