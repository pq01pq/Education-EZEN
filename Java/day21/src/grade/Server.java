package grade;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import javax.swing.*;

public class Server extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private InetAddress guestAddr;
	private DatagramSocket socket;
	private DatagramPacket sendPacket, receivePacket;
	private ByteArrayOutputStream baos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private Container container, insertContainer, deleteContainer;
	private JButton insertButton, insertConfirmButton, deleteButton, deleteConfirmButton, 
			loadButton, saveButton, exitButton;
	private JPanel controlPanel, inputPanel, inputTextPanel;
	private JTabbedPane displayTab;
	private JLabel	inputNameLabel, inputKorLabel, inputEngLabel, deleteNameLabel;
	private JDialog inputDialog, deleteDialog;
	private JTextField insertNameField, deleteNameField, korField, engField;
	private JTextArea nameDisplayArea, korDisplayArea, engDisplayArea, totalDisplayArea, rankDisplayArea;
	
	private Manage manage;
	private DisplayResponse displayResponse;
	
	// 기본 생성자
	public Server(String title) {
		super(title);
		setFrame();
		init();
		manage = new ManageGradeServer();
		displayResponse = new DisplayResponse();
		sortAndDisplay();
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(true) {
			Student student = receive();	// 수신 대기
			switch(student.getCommand()) {
			case "insert":
				manage.insert(student);
				break;
			case "delete":
				manage.delete(student.getName());
				break;
				
			default :
			}
			sortAndDisplay();
			send(displayResponse);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "insert":
			inputDialog.setVisible(true);
			break;
		case "delete":
			deleteDialog.setVisible(true);
			break;
		case "insert_confirm":
			inputDialog.setVisible(false);
			Student student = new Student(insertNameField.getText(),
					Integer.parseInt(korField.getText()), Integer.parseInt(engField.getText()));
			if(manage.insert(student)) {
				sortAndDisplay();
			} else {
				JOptionPane.showMessageDialog(
						  this, "존재하는 이름", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
			insertNameField.setText("");
			korField.setText("");
			engField.setText("");
			
			break;
		case "delete_confirm":
			if(manage.delete(deleteNameField.getText())) {
				sortAndDisplay();
			} else {
				JOptionPane.showMessageDialog(
						this, "없는 이름", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
			deleteNameField.setText("");
			break;
//		case "save":
//			manage.save();
//			break;
//		case "load":
//			manage.load();
//			sortAndDisplay();
//			break;
		case "exit":
			System.exit(0);
			default :
				
		}
	}
	
	public Student receive() {
		try {
			receivePacket = new DatagramPacket(new byte[65508], 65508);
			socket.receive(receivePacket);
			guestAddr = receivePacket.getAddress();
			ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(receivePacket.getData())));
			return (Student)ois.readObject();
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void send(DisplayResponse displayResponse) {
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(new BufferedOutputStream(baos));
			oos.writeObject(displayResponse);
			oos.flush();
			sendPacket = new DatagramPacket(
					baos.toByteArray(), baos.toByteArray().length, guestAddr, 12346);
			socket.send(sendPacket);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sortAndDisplay() {
		displayResponse.clear();
		
		manage.sort("name");
		displayResponse.setOrderByName(manage.toViewList());
		nameDisplayArea.setText(manage.view());
		
		manage.sort("kor");
		displayResponse.setOrderByKor(manage.toViewList());
		korDisplayArea.setText(manage.view());
		
		manage.sort("eng");
		displayResponse.setOrderByEng(manage.toViewList());
		engDisplayArea.setText(manage.view());
		
		manage.sort("total");
		displayResponse.setOrderByTotal(manage.toViewList());
		totalDisplayArea.setText(manage.view());
		
		manage.sort("rank");
		displayResponse.setOrderByRank(manage.toViewList());
		rankDisplayArea.setText(manage.view());
	}
	
	public void init() {
		try {
			socket = new DatagramSocket(12345);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFrame() {
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		this.container = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setLayout(new BorderLayout());
		
		// 상단 컨트롤 패널
		controlPanel = new JPanel(new GridLayout(1, 3));
		container.add("North", controlPanel);
		
		insertButton = new JButton("입력");
		deleteButton = new JButton("삭제");
		saveButton = new JButton("저장");
		loadButton = new JButton("열기");
		exitButton = new JButton("종료");
		insertButton.setActionCommand("insert");
		deleteButton.setActionCommand("delete");
		saveButton.setActionCommand("save");
		loadButton.setActionCommand("load");
		exitButton.setActionCommand("exit");
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		exitButton.addActionListener(this);
		controlPanel.add(insertButton);
		controlPanel.add(deleteButton);
		controlPanel.add(saveButton);
		controlPanel.add(loadButton);
		controlPanel.add(exitButton);
		
		// 중심 화면 패널
		displayTab = new JTabbedPane();
		container.add("Center", displayTab);
		nameDisplayArea = new JTextArea();
		korDisplayArea = new JTextArea();
		engDisplayArea = new JTextArea();
		totalDisplayArea = new JTextArea();
		rankDisplayArea = new JTextArea();
		nameDisplayArea.setName("name");
		korDisplayArea.setName("kor");
		engDisplayArea.setName("eng");
		totalDisplayArea.setName("total");
		rankDisplayArea.setName("rank");
		nameDisplayArea.setEditable(false);
		korDisplayArea.setEditable(false);
		engDisplayArea.setEditable(false);
		totalDisplayArea.setEditable(false);
		rankDisplayArea.setEditable(false);
		
		
		
//		nameDisplayPanel = new JPanel();
//		korDisplayPanel = new JPanel();
//		engDisplayPanel = new JPanel();
//		totalDisplayPanel = new JPanel();
//		rankDisplayPanel = new JPanel();
//		nameDisplayPanel.setName("name");
//		korDisplayPanel.setName("kor");
//		engDisplayPanel.setName("eng");
//		totalDisplayPanel.setName("total");
//		rankDisplayPanel.setName("rank");
		displayTab.add("이름", nameDisplayArea);
		displayTab.add("국어", korDisplayArea);
		displayTab.add("영어", engDisplayArea);
		displayTab.add("총점", totalDisplayArea);
		displayTab.add("순위", rankDisplayArea);
		
		// 입력 다이얼로그
		inputDialog = new JDialog(this, "성적 입력", false);
		inputDialog.setSize(200, 200);
		inputDialog.setLocation(this.getX(), this.getY());
		insertContainer = inputDialog.getContentPane();
		insertContainer.setLayout(new BorderLayout());
		
		inputPanel = new JPanel(new GridLayout(3, 1));
		insertContainer.add("West", inputPanel);
		
		inputNameLabel = new JLabel("이름");
		inputKorLabel = new JLabel("국어");
		inputEngLabel = new JLabel("영어");
		inputPanel.add(inputNameLabel);
		inputPanel.add(inputKorLabel);
		inputPanel.add(inputEngLabel);
		
		inputTextPanel = new JPanel(new GridLayout(3, 1));
		insertContainer.add("Center", inputTextPanel);
		
		insertNameField = new JTextField();
		korField = new JTextField();
		engField = new JTextField();
		inputTextPanel.add("이름", insertNameField);
		inputTextPanel.add("국어", korField);
		inputTextPanel.add("영어", engField);
		
		insertConfirmButton = new JButton("확인");
		insertConfirmButton.addActionListener(this);
		insertConfirmButton.setActionCommand("insert_confirm");
		insertContainer.add("South", insertConfirmButton);
		
		// 삭제 다이얼로그
		deleteDialog = new JDialog(this, "삭제", false);
		deleteDialog.setSize(200, 120);
		deleteDialog.setLocation(this.getX(), this.getY());
		deleteContainer = deleteDialog.getContentPane();
		deleteContainer.setLayout(new BorderLayout());
		
		deleteNameField = new JTextField();
		deleteContainer.add("Center", deleteNameField);
		
		deleteNameLabel = new JLabel("이름");
		deleteContainer.add("West", deleteNameLabel);
		
		deleteConfirmButton = new JButton("확인");
		deleteConfirmButton.addActionListener(this);
		deleteConfirmButton.setActionCommand("delete_confirm");
		deleteContainer.add("South", deleteConfirmButton);
	}
	
	public static void main(String[] args) {
		new Server("관리자");
	}

}
