package sugang.client.controller;

import java.io.*;
import java.net.*;
import java.util.*;

import sugang.client.view.*;
import sugang.model.*;

public class Client implements Runnable {
	
	private InetAddress hostAddr;
	private Socket socket;
	
	private byte[] data;
	private ByteArrayInputStream bais;
	private ByteArrayOutputStream baos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private ClientView subjectView;
	
	private String name;
	private String id;
	private ArrayList<SubjectVO> subjects;
	
	public Client(String name, String id) {
		super();
		this.name = name;
		this.id = id;
		subjects = new ArrayList<>();
		init();
		
		subjectView = new ClientView("수강신청", this);
		subjectView.setDisplay(subjects);
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			subjects = receive();
			subjectView.setDisplay(subjects);
		}	
	}
	
	public boolean send(String function, String key) {
		return send(function, null, key);
	}
	
	// use at search
	public boolean send(String function, String option, String key) {
		return send(function, option, null, key);
	}
	
	// use at register, cancel
	public boolean send(String function, String option, String code, String key) {
		StudentVO student = new StudentVO(name, id, function, option, code, key);
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(new BufferedOutputStream(baos));
			oos.writeObject(student);
			oos.flush();
			socket.getOutputStream().write(baos.toByteArray());
			return true;
		} catch(IOException e) {
			System.out.println(student.getFunction() + " 전송 실패");
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<SubjectVO> receive() {
		try {
			data = new byte[65508];
			socket.getInputStream().read(data);
			bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(new BufferedInputStream(bais));
			return (ArrayList<SubjectVO>)ois.readObject();
		} catch(IOException e) {
			System.out.println("수신 실패");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void init() {
		try {
			hostAddr = InetAddress.getByName("localhost");
			System.out.println("호스트 IP: " + hostAddr.getHostAddress());
			socket = new Socket(hostAddr, 10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 서버 연결시 객체 초기화하는 과정
		// 2. 서버로부터 강의 목록을 받음
		this.subjects = receive();
		System.out.println("2. 강의목록 수신");
		// 3. 서버로 학생정보를 전달
		send(null, null);
		System.out.println("3. 학생정보 전달");
	}
	
	public static void main(String[] args) {
//		String name = JOptionPane.showInputDialog("이름");
//		String id = JOptionPane.showInputDialog("학번");
		new Client("오정석", "2012140416");
	}
	
}
