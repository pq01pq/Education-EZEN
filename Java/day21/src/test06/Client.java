package test06;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame implements Runnable, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private InetAddress hostAddr;
	private DatagramSocket socket;
	private DatagramPacket sendPacket, receivePacket;
	
	private String name;
	
	private Container con;
	
	private JScrollPane msgPane;
	private JTextArea msgArea;
	
	private JPanel msgPanel;
	private JLabel msgLabel;
	private JTextField msgField;
	private JButton sendButton;
	
	public Client() {
		this("");
	}
	
	public Client(String title) {	// 서버로 메세지를 보내는 프로그램
		super(title);
		if(title.equals("")) {
			name = JOptionPane.showInputDialog(
					this, "사용자명", "로그인", JOptionPane.QUESTION_MESSAGE);
		} else {
			name = title;
		}
		this.setTitle(name);
		setFrame();
		
		try {
			hostAddr = InetAddress.getByName("localhost");
			socket = new DatagramSocket(12346);
			
			new Thread(this).start();	// 메세지를 받기 시작
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				receivePacket = new DatagramPacket(new byte[65508], 65508);
				socket.receive(receivePacket);
				msgArea.append("[host] " + new String(receivePacket.getData()).trim() + "\n");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage() {
		
	}
	
	public void recieveMessage() {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER :
				String message = msgField.getText();
				msgField.setText("");
				msgArea.append("[guest] " + message + "\n");
				sendPacket = new DatagramPacket(
						message.getBytes(), message.getBytes().length, hostAddr, 12345);
				socket.send(sendPacket);
				break;
				
			default :
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			switch(e.getButton()) {
			case MouseEvent.BUTTON1:
				if(e.getSource() == sendButton) {
					String message = msgField.getText();
					msgField.setText("");
					msgArea.append("[guest] " + message + "\n");
					sendPacket = new DatagramPacket(
							message.getBytes(), message.getBytes().length, hostAddr, 12345);
					socket.send(sendPacket);
				}
				break;
				
			default :
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
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
		
		msgLabel = new JLabel(name);
		
		msgField = new JTextField();
		msgField.addKeyListener(this);
		
		sendButton = new JButton("전송");
		sendButton.addMouseListener(this);
		
		msgPanel.add("West", msgLabel);
		msgPanel.add("Center", msgField);
		msgPanel.add("East", sendButton);
	}
	
	public static void main(String[] args) {
		new Client();
	}
	
}
