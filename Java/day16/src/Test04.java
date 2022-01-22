import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyImage04 extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private Image img;
	private int xSize, ySize;
	
	public MyImage04(Image img, int xSize, int ySize) {
		this.img = img;
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 100, 0, xSize, ySize, this);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public void setSize(int x, int y) {
		this.xSize = x;
		this.ySize = y;
	}
}

class MyFrame04 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Panel menuPanel;
	private Button zzaZang, zzamBbong, tangSooYook, friedRice;
	private MyImage04 myImg;
	
	// ±âº» »ý¼ºÀÚ
	public MyFrame04(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		setFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == zzaZang) {
			myImg.setImg(Toolkit.getDefaultToolkit().getImage("image/zzaZang.jpg"));
		} else if(e.getSource() == zzamBbong) {
			myImg.setImg(Toolkit.getDefaultToolkit().getImage("image/zzamBbong.jpg"));
		} else if(e.getSource() == tangSooYook) {
			myImg.setImg(Toolkit.getDefaultToolkit().getImage("image/tangSooYook.jpg"));
		} else if(e.getSource() == friedRice) {
			myImg.setImg(Toolkit.getDefaultToolkit().getImage("image/friedRice.jpg"));
		}
		myImg.repaint();
	}
	
	public void setFrame() {
		this.setLayout(new BorderLayout());
		
		menuPanel = new Panel(new GridLayout(1, 4));
		this.add("North", menuPanel);
		
		zzaZang = new Button("Â¥Àå¸é");
		zzamBbong = new Button("Â«»Í");
		tangSooYook = new Button("ÅÁ¼öÀ°");
		friedRice = new Button("ººÀ½¹ä");
		
		zzaZang.addActionListener(this);
		zzamBbong.addActionListener(this);
		tangSooYook.addActionListener(this);
		friedRice.addActionListener(this);
		
		menuPanel.add(zzaZang);
		menuPanel.add(zzamBbong);
		menuPanel.add(tangSooYook);
		menuPanel.add(friedRice);
		
		myImg = new MyImage04(
				Toolkit.getDefaultToolkit().getImage("image/symbol.jpg"), 200, 200);
		this.add("Center", myImg);
	}
	
}

public class Test04 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame04 mf = new MyFrame04("awt ½Ç½À");
	}

}
