import java.awt.*;

class MyFrame03 extends Frame {
	private static final long serialVersionUID = 1L;
	
	// this : � ������Ʈ ���� �ø��ų�. null�� ��
	// true�� â�� ������ ������ �ٸ����� ���ű�
	private Dialog dlg = new Dialog(this, "���", true);
	private Label lb = new Label("����Դϴ�.");
	private Button bt = new Button("Ȯ��");
	
	// �⺻ ������
	public MyFrame03(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		this.init();
		
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {}
		dlg.setBounds(xPos, yPos, 200, 150);	// location + size
		dlg.setVisible(true);
		
	}
	
	public void init() {
		dlg.setLayout(new BorderLayout());
		dlg.add("Center", lb);
		dlg.add("South", bt);
	}
}

public class Test03 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame03 mf = new MyFrame03("awt �ǽ�");
	}

}
