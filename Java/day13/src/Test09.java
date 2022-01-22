import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

class MyFrame09 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Label label;
	private TextField title;
	private TextArea contents;
	private BorderLayout bl = new BorderLayout();
	private BorderLayout north_bl = new BorderLayout();
	private Panel north_p = new Panel();
	private Button button = new Button("확인");
	private FlowLayout south_fl = new FlowLayout(FlowLayout.RIGHT);
	private Panel south_p = new Panel();
	
	// 기본 생성자
	public MyFrame09(String title) {
		super(title);
		
		label = new Label("제목 : ");
		this.title = new TextField();
		contents = new TextArea();
		
		this.setLayout(bl);
		this.add("North", north_p);
		north_p.setLayout(north_bl);
		north_p.add("West", label);
		north_p.add("Center", this.title);
		this.add("Center", contents);
		this.add("South", south_p);
		south_p.setLayout(south_fl);
		south_p.add(button);
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test09 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame09 mf = new MyFrame09("awt 실습");
	}

}
