import java.awt.*;

class MyFrame05 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private MenuBar mb = new MenuBar();
	private Menu file = new Menu("����");
	private Menu edit = new Menu("����");
	private Menu help = new Menu("����");
	
	private MenuItem fNew = new MenuItem("�� ����");
	private MenuItem fOpen = new MenuItem("����");
	private MenuItem fSave = new MenuItem("����");
	private MenuItem fExit = new MenuItem("����");
	
	private Menu eColor = new Menu("����");
	private Menu eSize = new Menu("ũ��");
	
	private CheckboxMenuItem eRed = new CheckboxMenuItem("����");
	private CheckboxMenuItem eGreen = new CheckboxMenuItem("�ʷ�");
	private CheckboxMenuItem eBlue = new CheckboxMenuItem("�Ķ�");
	
	public void init() {
		this.setMenuBar(mb);
		mb.add(file);
		file.add(fNew);
		file.addSeparator();
		file.add(fOpen);
		file.add(fSave);
		file.addSeparator();
		file.add(fExit);	
		mb.add(edit);
		edit.add(eColor);	// �޴� ���� �޴�
		eColor.add(eRed);
		eColor.add(eGreen);
		eColor.add(eBlue);
		edit.addSeparator();
		edit.add(eSize);
		mb.add(help);
	}
	
	// �⺻ ������
	public MyFrame05(String title) {
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
}

public class Test05 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame05 mf = new MyFrame05("awt �ǽ�");
	}

}
