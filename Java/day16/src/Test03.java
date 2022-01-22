import java.awt.*;
import java.awt.event.*;

class MyButton03 extends Button{
	private static final long serialVersionUID = 1L;
	
	private Image img;
	
	public MyButton03(Image img) {
		this.img = img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public void paint(Graphics g) {
		g.drawImage(img, 5, 5, this.getWidth()-10, this.getHeight()-10, this);
	}
}

class MyFrame03 extends Frame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private Image img = Toolkit.getDefaultToolkit().getImage("image/radioactive.jpg");
	private MyButton03 mb = new MyButton03(img);
	private Label lb = new Label("ÇÙÆø¹ß¹öÆ°", Label.CENTER);
	
	public MyFrame03(String title) {
		super(title);
		
		this.setLayout(new BorderLayout());
		lb.setFont(new Font("", Font.PLAIN, 20));
		this.add("South", lb);
		this.add("Center", mb);	mb.addActionListener(this);
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2) - this.getWidth()/2;
		int ypos = (int)(screen.getHeight()/2) - this.getHeight()/2;
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		img = Toolkit.getDefaultToolkit().getImage("image/castle_bravo.jpg");
		mb.setImg(img);
		mb.repaint();
	}
}
public class Test03 {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame03 mf = new MyFrame03("awt½Ç½À");
	}
}
