import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;

class MyFrame05 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Button bt1 = new Button("버튼 1");
	private Button bt2 = new Button("버튼 2");
	private Button bt3 = new Button("버튼 3");
	private Button bt4 = new Button("버튼 4");
	private Button bt5 = new Button("버튼 5");
	private Button bt6 = new Button("버튼 6");
	
	// (행, 열, 좌우 간격, 상하 간격)
	private GridLayout gl = new GridLayout(3, 2, 5, 10);
	
	// 기본 생성자
	public MyFrame05(String title) {
		super(title);
		this.setLayout(gl);
		this.add(bt1);	this.add(bt2);
		this.add(bt3);	this.add(bt4);
		this.add(bt5);	this.add(bt6);
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test05 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame05 mf = new MyFrame05("awt 실습");
	}

}
