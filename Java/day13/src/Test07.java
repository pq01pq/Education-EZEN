import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;

class MyFrame07 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Button yesButton = new Button("YES");
	private Button noButton = new Button("NO");
	private Button noButton2 = new Button("NO2");
	
	// 한 레이아웃을 분할해서 쓰고싶으면 패널을 사용
	private Panel p1 = new Panel();
	private Panel p2 = new Panel();
	
	// 기본 생성자
	public MyFrame07(String title) {
		super(title);
		
		this.setLayout(new BorderLayout());
//		this.add("South", yesButton);
//		this.add("South", noButton);
		this.add("South", p1);
		p1.setLayout(new GridLayout(1, 2));
		p1.add(yesButton);
//		p1.add(noButton);
		p1.add(p2);
		p2.setLayout(new GridLayout(2, 1));
		p2.add(noButton);
		p2.add(noButton2);
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test07 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame07 mf = new MyFrame07("awt 실습");
	}

}
