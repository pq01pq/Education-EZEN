import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;

class MyFrame04 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Button bt = new Button("Ȯ��");
	private Button bt2 = new Button("Ȯ��2");
	
	// �⺻�� �߾�, ��, ��
	private FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
	
	// �⺻ ������
	public MyFrame04(String title) {
		super(title);
		
		this.setLayout(fl);;
		this.add(bt);
		this.add(bt2);
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test04 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame04 mf = new MyFrame04("awt �ǽ�");
	}

}