import java.awt.*;
import java.awt.event.*;

class MyEvent07 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
}

class MyEvent072 implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}	// ���콺�� �������� �ʰ� ������ ���� ����

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("aaa");
	}	// ���콺�� ������ ���� ����

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class MyEvent073 extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}
}

class MyFrame07 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Button bt = new Button("����");
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add("South", bt);
//		MyEvent07 me = new MyEvent07();
//		bt.addActionListener(me);
		// �̺�Ʈ�� �����ϸ� �̺�Ʈ ������ ���� ������ �����
		
		// 1. �̺�Ʈ �������̽��� ������ Ŭ���� �̿�
//		MyEvent072 me = new MyEvent072();
//		bt.addMouseListener(me);
		
		// 2. �̺�Ʈ Ŭ������ ��ӹ��� Ŭ������ �̿�
//		MyEvent073 me = new MyEvent073();
//		bt.addMouseListener(me);
		
		// 3. ��ü�� ActionListener �������̽��� �ٷ� ����
		bt.addActionListener(this);
		
		
	}
	
	// �⺻ ������
	public MyFrame07(String title) {
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
		System.exit(0);
	}
}

public class Test07 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame07 mf = new MyFrame07("awt �ǽ�");
	}

}
