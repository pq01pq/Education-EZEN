import java.awt.*;

class MyFrame02 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Label lb = new Label("당신의 혈액형은?");
	private Choice ch = new Choice();
	private Panel p1 = new Panel();
	
	private Label lb2 = new Label("알파벳 목록");
	private List alpha = new List();
	private Panel p2 = new Panel();
	
	// 기본 생성자
	public MyFrame02(String title) {
		super(title);
		
		this.setSize(400, 300);
		this.init();
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add("Center", p1);
		this.add("East", p2);
		p1.setLayout(new GridLayout(2, 1));
		this.add(lb);
		this.add(ch);
		ch.add("A형");
		ch.add("B형");
		ch.add("O형");
		ch.add("AB형");
		ch.add("기타");
		p2.setPreferredSize(new Dimension(
				(int)(this.getWidth() / 5.0),this.getHeight()));
		p2.setLayout(new BorderLayout());
		p2.add("North", lb2);
		p2.add("Center", alpha);
		for(int i = 'A'; i <= 'Z'; i++) {
			alpha.add("" + (char)i);
		}
	}
}

public class Test02 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame02 mf = new MyFrame02("awt 실습");
	}

}
