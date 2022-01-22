import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame08 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Button bt1 = new Button("���� ��ư");
	private Button bt2 = new Button("������ ��ư");
	
	public void init() {
		this.setLayout(new GridLayout(1, 2));
		this.add(bt1);
		this.add(bt2);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
	}
	
	// �⺻ ������
	public MyFrame08(String title) {
		super(title);
		this.init();
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1) {
			System.out.println("���ʹ�ư ����");
		} else if(e.getSource() == bt2) {
			System.out.println("�����ʹ�ư ����");
		}
	}
}

public class Test08 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame08 mf = new MyFrame08("awt �ǽ�");
	}

}
