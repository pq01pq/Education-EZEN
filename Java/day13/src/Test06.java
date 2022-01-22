import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

class MyFrame06 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Button bt1 = new Button("버튼 1");
	private Button bt2 = new Button("버튼 2");
	private Button bt3 = new Button("버튼 3");
	private Button bt4 = new Button("버튼 4");
	private Button bt5 = new Button("버튼 5");
	
	// Frame 클래스의 default layout이 BorderLayout임
	// 상하좌우, 중앙 지정하는 클래스
	// BorderLayout에서 위치를 지정하지 않으면 default로 CENTER가 됨
	// (좌우 간격, 상하 간격)
	private BorderLayout bl = new BorderLayout(5, 10);
	
	// 기본 생성자
	public MyFrame06(String title) {
		super(title);
		
		this.setLayout(bl);
		this.add("North", bt1);	this.add("South", bt2);
		this.add("West", bt3);	this.add("East", bt4);
		this.add("Center", bt5);
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test06 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame06 mf = new MyFrame06("awt 실습");
	}

}
