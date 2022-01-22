package test05;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame implements Runnable, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private InetAddress inetAddr;
	private Socket socket;
	
	private BufferedReader br;
	private PrintWriter pw;
	
	private Container con;
	
	private JScrollPane msgPane;
	private JTextArea msgArea;
	
	private JPanel msgPanel;
	private JLabel msgLabel;
	private JTextField msgField;
	private JButton sendButton;
	
	public Client(String title) {	// 서버로 메세지를 보내는 프로그램
		super(title);
		setFrame();
		
		try {
			inetAddr = InetAddress.getByName("localhost");
			socket = new Socket(inetAddr, 12345);
			
			pw = new PrintWriter(socket.getOutputStream(), true);
			// true면 버퍼가 꽉차면 자동으로 비움
			
			Thread client = new Thread(this);
			client.start();	// 메세지를 받기 시작
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {	// 서버에서 온 메세지 받는 프로그램
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String reMsg;
			while(true) {
				reMsg = br.readLine();
				msgArea.append("[서버] " + reMsg);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int pressed = e.getKeyCode();
		switch(pressed) {
		case KeyEvent.VK_ENTER:
			String msg = msgField.getText();
			msgField.setText("");
			msgArea.append("[나] " + msg + "\n");
			pw.println(msg);
			pw.flush();
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
				String msg = msgField.getText();
				msgField.setText("");
				msgArea.append("[나] " + msg + "\n");
				pw.println(msg);
				pw.flush();
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
		new Client("클라이언트");
	}
	
}
