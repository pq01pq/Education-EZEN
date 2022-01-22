package test06;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Server extends JFrame implements Runnable, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private ServerSocket svSocket;
	
	private ArrayList<ClientSession> clSessions;
	private LinkedList<Message> messages;
	
	private String name;
	
	private Container con;
	
	private JScrollPane msgPane;
	private JTextArea msgArea;
	
	private JPanel msgPanel;
	private JLabel msgLabel;
	private JTextField msgField;
	private JButton sendButton;
	
	public Server(String title) {
		super(title);
		name = title;
		setFrame();
		
		clSessions = new ArrayList<>();
		messages = new LinkedList<>();
		msgArea.setText("서버 시작...\n");
		
		try {
			svSocket = new ServerSocket(12345);
			new Thread(this).start();
			
			while(true) {
				Socket socket = svSocket.accept();	// 클라이언트 응대 대기상태
				ClientSession clSession = new ClientSession(this, socket);
				clSessions.add(clSession);
				System.out.println("클라이언트 세션 생성");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {	// 서버로 전송된 메세지가 있는지 계속 확인하여 각 세션에 뿌려주는 시스템
		while(true) {
			Message message = messages.poll();
			
			if(message != null) {
				for(ClientSession clSession : clSessions) {
					if(clSession.getName().equals(message.getName())) {
						continue;
					}
					clSession.sendMessage(message);
				}
				msgArea.append(message.toString());
			}
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER :
			Message message = new Message(name, msgField.getText() + "\n");
			msgField.setText("");
			msgArea.append(message.toString());
			for(ClientSession clSession : clSessions) {
				clSession.sendMessage(message);
			}
			break;
			
		default :
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(e.getButton()) {
		case MouseEvent.BUTTON1:
			if(e.getSource() == sendButton) {
				Message message = new Message(name, msgField.getText() + "\n");
				msgField.setText("");
				msgArea.append(message.toString());
				for(ClientSession clSession : clSessions) {
					clSession.sendMessage(message);
				}
			}
			break;
			
		default :
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public ArrayList<ClientSession> getClSessions() {
		return clSessions;
	}
	
	public Message checkMessage() {
		return messages.peek();
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public String getName() {
		return name;
	}
	
	public void setFrame() {
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		con = this.getContentPane();
		con.setLayout(new BorderLayout());
		
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		
		msgPane = new JScrollPane(msgArea);
		con.add("Center", msgPane);
		
		msgPanel = new JPanel(new BorderLayout());
		con.add("South", msgPanel);
		
		msgLabel = new JLabel(this.getTitle());
		
		msgField = new JTextField();
		msgField.addKeyListener(this);
		
		sendButton = new JButton("전송");
		sendButton.addMouseListener(this);
		
		msgPanel.add("West", msgLabel);
		msgPanel.add("Center", msgField);
		msgPanel.add("East", sendButton);
	}
	
	public static void main(String[] args) {
		new Server("관리자");
	}
	
}
