package grade2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class Client extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private InetAddress hostAddr;
	private DatagramSocket socket;
	private DatagramPacket sendPacket, receivePacket;
	private ByteArrayOutputStream baos;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private Container con, insertContainer, deleteContainer;
	private JButton insertButton, insertConfirmButton, deleteButton, deleteConfirmButton, 
			loadButton, saveButton, exitButton;
	private JPanel controlPanel, inputPanel, inputTextPanel;
	private JTabbedPane displayTab;
	private JLabel	inputNameLabel, inputKorLabel, inputEngLabel, deleteNameLabel;
	private JDialog inputDialog, deleteDialog;
	private JTextField insertNameField, deleteNameField, korField, engField;
	private JTextArea nameDisplayArea, korDisplayArea, engDisplayArea, totalDisplayArea, rankDisplayArea;
	
	private String name;
	
	public Client() {
		this("");
	}
	
	public Client(String title) {
		super(title);
		if(title.equals("")) {
			name = JOptionPane.showInputDialog(
					this, "사용자명", "로그인", JOptionPane.QUESTION_MESSAGE);
		} else {
			name = title;
		}
		setFrame();
		this.setTitle(name);
		init();
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(true) {
			setDisplay(receive());	// 수신 대기
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Student student;
		switch(e.getActionCommand()) {
		case "insert":
			inputDialog.setVisible(true);
			break;
		case "delete":
			deleteDialog.setVisible(true);
			break;
		case "insert_confirm":
			inputDialog.setVisible(false);
			student = new Student(insertNameField.getText(),
					Integer.parseInt(korField.getText()), Integer.parseInt(engField.getText()), "insert");
			send(student);		
//			if(manage.insert(student)) {
//				setDisplay();
//			} else {
//				JOptionPane.showMessageDialog(
//						  this, "존재하는 이름", "알림", JOptionPane.INFORMATION_MESSAGE);
//			}
			insertNameField.setText("");
			korField.setText("");
			engField.setText("");
			
			break;
		case "delete_confirm":
			deleteDialog.setVisible(false);
			student = new Student(deleteNameField.getText(), "delete");
			send(student);
//			if(manage.delete(deleteNameField.getText())) {
//				setDisplay();
//			} else {
//				JOptionPane.showMessageDialog(
//						this, "없는 이름", "알림", JOptionPane.INFORMATION_MESSAGE);
//			}
			deleteNameField.setText("");
			break;
		case "save":
//			manage.save();
			break;
		case "load":
//			manage.load();
//			setDisplay();
			break;
		case "exit":
			System.exit(0);
			default :
				
		}
	}
	
	public DisplayResponse receive() {
		try {
			receivePacket = new DatagramPacket(new byte[65508], 65508);
			socket.receive(receivePacket);
			ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(receivePacket.getData())));
			return (DisplayResponse)ois.readObject();
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void send(Student student) {
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(new BufferedOutputStream(baos));
			oos.writeObject(student);
			oos.flush();
			sendPacket = new DatagramPacket(
					baos.toByteArray(), baos.toByteArray().length, hostAddr, 12345);
			socket.send(sendPacket);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDisplay(DisplayResponse displayResponse) {
		String viewLine;
		nameDisplayArea.setText("");
		while((viewLine = displayResponse.getOrderByName().poll()) != null) {
			nameDisplayArea.append(viewLine + "\n");
		}
		
		korDisplayArea.setText("");
		while((viewLine = displayResponse.getOrderByKor().poll()) != null) {
			korDisplayArea.append(viewLine + "\n");
		}
		
		engDisplayArea.setText("");
		while((viewLine = displayResponse.getOrderByEng().poll()) != null) {
			engDisplayArea.append(viewLine + "\n");
		}
		
		totalDisplayArea.setText("");
		while((viewLine = displayResponse.getOrderByTotal().poll()) != null) {
			totalDisplayArea.append(viewLine + "\n");
		}
		
		rankDisplayArea.setText("");
		while((viewLine = displayResponse.getOrderByRank().poll()) != null) {
			rankDisplayArea.append(viewLine + "\n");
		}
	}
	
	public void init() {
		try {
			hostAddr = InetAddress.getByName("localhost");
			socket = new DatagramSocket(12346);
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
		
		this.con = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setLayout(new BorderLayout());
		
		// 상단 컨트롤 패널
		controlPanel = new JPanel(new GridLayout(1, 3));
		con.add("North", controlPanel);
		
		insertButton = new JButton("입력");
		deleteButton = new JButton("삭제");
//		saveButton = new JButton("저장");
//		loadButton = new JButton("열기");
		exitButton = new JButton("종료");
		insertButton.setActionCommand("insert");
		deleteButton.setActionCommand("delete");
//		saveButton.setActionCommand("save");
//		loadButton.setActionCommand("load");
		exitButton.setActionCommand("exit");
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
//		saveButton.addActionListener(this);
//		loadButton.addActionListener(this);
		exitButton.addActionListener(this);
		controlPanel.add(insertButton);
		controlPanel.add(deleteButton);
//		controlPanel.add(saveButton);
//		controlPanel.add(loadButton);
		controlPanel.add(exitButton);
		
		// 중심 화면 패널
		displayTab = new JTabbedPane();
		con.add("Center", displayTab);
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
		 new Client();
	}

}
