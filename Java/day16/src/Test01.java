import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame01 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Button bt = new Button("확인");
//	private int count = 0;
	
	// 기본 생성자
	public MyFrame01(String title) {
		super(title);
		init();
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(true);
		this.setVisible(true);
	}
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add("South", bt);
		bt.addActionListener(this);
	}
	
	public void update (Graphics g) {
		g.clearRect(0, 0, 400, 300);
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.drawLine(50, 50, 100, 100); // 앞에 두자리 시작점, 뒤에 두자리 끝점, 두점을 연결
		g.drawRect(50, 50, 100, 100); // (앞의 두자리 시작점, 3번째 가로길이, 4번째 세로길이
		g.setColor(Color.RED);
		g.fillRect(200, 50, 100, 100);
		g.fillOval(50, 50, 100, 100); // 사각형과 같은 크기를 지정 후, 그 크기를 꽉 채우는 원을 그림
		int x[] = new int[] {70,160,40,80};
		int y[] = new int[] {70,130,120,170};
		g.setColor(Color.BLUE);
		g.fillPolygon(x, y, 4); // 꼭짓점들, 꼭짓점 수
		g.setFont(new Font("", Font.BOLD, 30));
		g.drawString("JAVA graphics", 50, 200);
		Image img = Toolkit.getDefaultToolkit().getImage("image/neutron_star.jpg");
		g.drawImage(img, 50, 50, 150, 100, this); // 시작좌표(x,y) , 길이(w,h), 소속
		
		
//		System.out.println(++count);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	
}

public class Test01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame01 mf = new MyFrame01("awt 실습");
	}

}
