import java.awt.*;

class MyFrame04 extends Frame {
	private static final long serialVersionUID = 1L;
	
	// 운영체제에 있는 컴포넌트를 가져옴
	// 윈도우가 정해준대로 주기 때문에 크기 등 지정할 필요 없음
	//private FileDialog fdlg = new FileDialog(this, "저장", FileDialog.SAVE);
	private FileDialog fdlg = new FileDialog(this, "열기", FileDialog.LOAD);
	
	// 기본 생성자
	public MyFrame04(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {}
		fdlg.setVisible(true);
	}
}

public class Test04 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame04 mf = new MyFrame04("awt 실습");
	}

}
