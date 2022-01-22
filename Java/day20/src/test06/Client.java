package test06;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame implements Runnable, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private InetAddress inetAddr;
	private Socket socket;
	
	private ByteArrayInputStream bais;
	private ByteArrayOutputStream baos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
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
			inetAddr = InetAddress.getByName("localhost");
			socket = new Socket(inetAddr, 12345);
			
//			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
//			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
			
			
			
			
			new Thread(this).start();	// 메세지를 받기 시작
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Message request = new Message(name, "request", true);
		sendMessage(request);	// 1) 클라이언트에서 세션으로 접속 요청을 보냄
		System.out.println("요청 전송");
		Message response = recieveMessage();
		System.out.println("응답 확인");
		System.out.println(response.getName());
		System.out.println(response.getMessage());
		if(response.getMessage().equals("deny")) {
			return;
		}
		
		while(true) {
			msgArea.append(recieveMessage().toString());	// 메세지 수신 대기상태
		}
	}
	
	public void sendMessage(Message message) {
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(new BufferedOutputStream(baos));
			oos.writeObject(message);
			oos.flush();
			socket.getOutputStream().write(baos.toByteArray());
//			socket.getOutputStream().flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Message recieveMessage() {
		try {
			byte[] data = new byte[65508];
			socket.getInputStream().read(data);
			bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(new BufferedInputStream(bais));
			Message message = (Message)ois.readObject();
			return message;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
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
			sendMessage(message);
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
				sendMessage(message);
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
