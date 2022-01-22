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
	}	// 마우스를 움직이지 않고 눌렀다 떼는 동작

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("aaa");
	}	// 마우스를 눌렀다 떼는 동작

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
	
	private Button bt = new Button("종료");
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add("South", bt);
//		MyEvent07 me = new MyEvent07();
//		bt.addActionListener(me);
		// 이벤트를 전달하면 이벤트 동작이 들어올 때까지 대기함
		
		// 1. 이벤트 인터페이스를 구현한 클래스 이용
//		MyEvent072 me = new MyEvent072();
//		bt.addMouseListener(me);
		
		// 2. 이벤트 클래스를 상속받은 클래스를 이용
//		MyEvent073 me = new MyEvent073();
//		bt.addMouseListener(me);
		
		// 3. 객체에 ActionListener 인터페이스를 바로 구현
		bt.addActionListener(this);
		
		
	}
	
	// 기본 생성자
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
		MyFrame07 mf = new MyFrame07("awt 실습");
	}

}
