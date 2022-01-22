import java.awt.*;

class MyFrame03 extends Frame {
	private static final long serialVersionUID = 1L;
	
	// 좌, 우, 중앙
	private Label lb = new Label("java awt programming!!", Label.LEFT);
	
	// 기본 생성자
	public MyFrame03(String title) {
		super(title);
		
		this.add(lb);	// 레이블을 올리겠다
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}
