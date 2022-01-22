import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

class MyFrame06 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Button bt1 = new Button("��ư 1");
	private Button bt2 = new Button("��ư 2");
	private Button bt3 = new Button("��ư 3");
	private Button bt4 = new Button("��ư 4");
	private Button bt5 = new Button("��ư 5");
	
	// Frame Ŭ������ default layout�� BorderLayout��
	// �����¿�, �߾� �����ϴ� Ŭ����
	// BorderLayout���� ��ġ�� �������� ������ default�� CENTER�� ��
	// (�¿� ����, ���� ����)
	private BorderLayout bl = new BorderLayout(5, 10);
	
	// �⺻ ������
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
		MyFrame06 mf = new MyFrame06("awt �ǽ�");
	}

}
