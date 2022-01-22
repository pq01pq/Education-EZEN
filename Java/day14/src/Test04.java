import java.awt.*;

class MyFrame04 extends Frame {
	private static final long serialVersionUID = 1L;
	
	// �ü���� �ִ� ������Ʈ�� ������
	// �����찡 �����ش�� �ֱ� ������ ũ�� �� ������ �ʿ� ����
	//private FileDialog fdlg = new FileDialog(this, "����", FileDialog.SAVE);
	private FileDialog fdlg = new FileDialog(this, "����", FileDialog.LOAD);
	
	// �⺻ ������
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
		MyFrame04 mf = new MyFrame04("awt �ǽ�");
	}

}
