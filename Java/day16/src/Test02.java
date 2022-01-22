import java.awt.*;
import java.awt.event.*;
class MyImage02 extends Canvas{
	private static final long serialVersionUID = 1L;
	
	private Image img = null;
	private int xsize, ysize;
	
	public MyImage02() {
		img = Toolkit.getDefaultToolkit().getImage("image/neutron_star.jpg");
		xsize = 200;
		ysize = 200;
	}
	public void setSize(int x, int y) {
		this.xsize = x;
		this.ysize = y;
	}
	public void paint(Graphics g) {
		g.drawImage(img, 30, 30, xsize, ysize, this);
	}
}


class MyFrame02 extends Frame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private Button bt1 = new Button("확대");
	private Button bt2 = new Button("축소");
	private Button bt3 = new Button("원본");
	private Button bt4 = new Button("종료");
	
	private Panel p = new Panel();
	int xsize = 200;
	int ysize = 200;
	
	private MyImage02 mi = new MyImage02();
	
	/*
	Image img = Toolkit.getDefaultToolkit().getImage("image/suji.jpg");
	int xsize = 200;
	int ysize = 200;
	
	public void paint(Graphics g) {
		g.drawImage(img, 50, 50, xsize, ysize, this);
	}
	*/
	public void init() {
		this.setLayout(new BorderLayout());
		p.setPreferredSize(new Dimension(150, 500));
		this.add("Center", mi);
		this.add("East", p);
		p.setLayout(new GridLayout(4,1));
		p.add(bt1);	bt1.addActionListener(this);
		p.add(bt2);	bt2.addActionListener(this);
		p.add(bt3);	bt3.addActionListener(this);
		p.add(bt4);	bt4.addActionListener(this);
	}
	
	public MyFrame02(String title) {
		super(title);
		
		this.init();
		
		this.setSize(650, 500);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2) - this.getWidth()/2;
		int ypos = (int)(screen.getHeight()/2) - this.getHeight()/2;
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bt1) {
			xsize += 50;
			ysize += 50;
			if (xsize>400) {
				xsize = 400;
				ysize = 400;
			}
		}else if (e.getSource()==bt2) {
			xsize -= 50;
			ysize -= 50;
			if (xsize<50) {
				xsize = 50;
				ysize = 50;
			}
		}else if (e.getSource()==bt3) {
			xsize = 200;
			ysize = 200;
		}else if (e.getSource()==bt4) {
			System.exit(0);
		}
		mi.setSize(xsize, ysize);
		mi.repaint();
	}
}
public class Test02 {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		MyFrame02 mf = new MyFrame02("awt실습");
	}
}
