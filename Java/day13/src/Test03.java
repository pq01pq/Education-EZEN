import java.awt.*;

class MyFrame03 extends Frame {
	private static final long serialVersionUID = 1L;
	
	// ��, ��, �߾�
	private Label lb = new Label("java awt programming!!", Label.LEFT);
	
	// �⺻ ������
	public MyFrame03(String title) {
		super(title);
		
		this.add(lb);	// ���̺��� �ø��ڴ�
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}
