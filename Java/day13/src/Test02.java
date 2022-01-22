import java.awt.*;

class MyFrame02 extends Frame {
	private static final long serialVersionUID = 1L;
	
	// 기본 생성자
	public MyFrame02(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test02 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame02 mf = new MyFrame02("awt 실습");
	}

}
