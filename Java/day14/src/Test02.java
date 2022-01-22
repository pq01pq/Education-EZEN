import java.awt.*;

class MyFrame02 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Label lb = new Label("����� ��������?");
	private Choice ch = new Choice();
	private Panel p1 = new Panel();
	
	private Label lb2 = new Label("���ĺ� ���");
	private List alpha = new List();
	private Panel p2 = new Panel();
	
	// �⺻ ������
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
		ch.add("A��");
		ch.add("B��");
		ch.add("O��");
		ch.add("AB��");
		ch.add("��Ÿ");
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
		MyFrame02 mf = new MyFrame02("awt �ǽ�");
	}

}
