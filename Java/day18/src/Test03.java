import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MyCanvas03 extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private Image img;
	
	public MyCanvas03() {}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	
}

class MyFrame03 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Container con;
	private JToolBar jToolBar;
	private JButton[] jButton;
	private Image[] kart;
	private MyCanvas03 imgPanel;
	
	// 기본 생성자
	public MyFrame03(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		init();
	}
	
	public void init() {
		this.con = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		con.setLayout(new BorderLayout());
		jToolBar = new JToolBar("J툴바");
		con.add("North", jToolBar);
		
		jButton = new JButton[4];
		jButton[0] = new JButton("다오");
		jButton[1] = new JButton("배찌");
		jButton[2] = new JButton("디지니");
		jButton[3] = new JButton("우니");
		
		kart = new Image[4];
		
		for(int i = 0; i < jButton.length; i++) {
			kart[i] = Toolkit.getDefaultToolkit().getImage("img/kart" + String.valueOf(i) + ".jpg");
			jToolBar.add(jButton[i]);
			jButton[i].addActionListener(this);
		}
		
		imgPanel = new MyCanvas03();
		con.add("Center", imgPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "다오":
			imgPanel.setImg(kart[0]);
			imgPanel.repaint();
			break;
		case "배찌":
			imgPanel.setImg(kart[1]);
			imgPanel.repaint();
			break;
		case "디지니":
			imgPanel.setImg(kart[2]);
			imgPanel.repaint();
			break;
		case "우니":
			imgPanel.setImg(kart[3]);
			imgPanel.repaint();
			break;
		default :
		}
		
	}
	
}

public class Test03 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame03 mf = new MyFrame03("swing 실습");
	}

}
