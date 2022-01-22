import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MyFrame03 extends Frame implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Button bt = new Button("확인");
	private GridBagLayout gb1 = new GridBagLayout();
	
	public void init() {
		this.setLayout(gb1);
		this.add(bt);
		bt.addMouseListener(this);
	}
	
	// 기본 생성자
	public MyFrame03(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		init();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(e.getButton()) {
		case MouseEvent.BUTTON1:
			System.out.println("마우스 좌클릭");
			break;
		case MouseEvent.BUTTON2:
			System.out.println("마우스 스크롤 클릭");
			break;
		case MouseEvent.BUTTON3:
			System.out.println("마우스 우클릭");
			break;
		default :
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class Test03 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame03 mf = new MyFrame03("awt 실습");
	}

}
