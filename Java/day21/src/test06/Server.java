package test06;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Server extends JFrame implements Runnable, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private InetAddress guestAddr;
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
	
	public Server(String title) {
		super(title);
		name = title;
		guestAddr = null;
		setFrame();
		
		try {
			socket = new DatagramSocket(12345);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		msgArea.setText("서버 시작...\n");
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {	// 서버로 전송된 메세지가 있는지 계속 확인하여 각 세션에 뿌려주는 시스템
		try {
			while(true) {
				receivePacket = new DatagramPacket(new byte[65508], 65508);
				socket.receive(receivePacket);
				guestAddr = receivePacket.getAddress();
				msgArea.append("[guest] " + new String(receivePacket.getData()).trim() + "\n");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
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
				if(guestAddr != null) {
					msgArea.append("[host] " + message + "\n");
					sendPacket = new DatagramPacket(
							message.getBytes(), message.getBytes().length, guestAddr, 12346);
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
					if(guestAddr != null) {
						msgArea.append("[host] " + message + "\n");
						sendPacket = new DatagramPacket(
								message.getBytes(), message.getBytes().length, guestAddr, 12346);
						socket.send(sendPacket);
					}
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
