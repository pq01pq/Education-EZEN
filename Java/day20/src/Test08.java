import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyFrame08 extends JFrame implements Runnable, ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	
	private JTextArea jta = new JTextArea();
	private JScrollPane jsp = new JScrollPane(jta);
	private JLabel lb = new JLabel("�޼��� : ", JLabel.RIGHT);
	private JTextField jtf = new JTextField();
	private JButton jbt = new JButton("����");
	private JPanel jp = new JPanel();
	
	private InetAddress ia;
	private Socket soc;
	private PrintWriter pw;
	private BufferedReader in;
	
	public void init(){
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		jta.setFont(new Font("", Font.PLAIN, 15));
		jta.setEditable(false);
		con.add("Center", jsp);
		con.add("South", jp);
		jp.setLayout(new BorderLayout());
		jp.add("West", lb);
		jp.add("Center", jtf);
		jp.add("East", jbt);
	}
	public void start(){
		jtf.addKeyListener(this);
		jbt.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public MyFrame08(String title){
		super(title);
		this.init();
		this.start();
		super.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int y = (int)(screen.getHeight()/2 - super.getHeight()/2);
		super.setLocation(x, y);
		super.setResizable(false);
		super.setVisible(true);
		try{
			ia = InetAddress.getByName("localhost");
			soc = new Socket(ia, 12345);
			jta.setText("���� ���� ����!!\n");
			pw = new PrintWriter(soc.getOutputStream(), true);
		}catch(IOException e){}
		Thread th = new Thread(this);
		th.start();
	}
	public void run(){
		try{
			in = new BufferedReader
				(new InputStreamReader(soc.getInputStream()));
			String clMsg = "";
			while(true){
				clMsg = in.readLine();
				jta.append("server : " + clMsg+"\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jbt){
			pw.println(jtf.getText());
			pw.flush();
			jta.append("client : " + jtf.getText()+"\n");
			jtf.setText("");
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			pw.println(jtf.getText());
			pw.flush();
			jta.append("client : " + jtf.getText()+"\n");
			jtf.setText("");
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class Test08 {
	@SuppressWarnings("unused")
	public static void main(String[] args){
		MyFrame08 mf = new MyFrame08("ä��Ŭ���̾�Ʈ");
	}
}