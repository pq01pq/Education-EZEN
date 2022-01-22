import java.awt.*;

class MyFrame01 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Label label1 = new Label("�����ϴ� ������?");
	private Checkbox box11 = new Checkbox("�ٳ���");
	private Checkbox box12 = new Checkbox("����");
	private Checkbox box13 = new Checkbox("���");
	private Checkbox box14 = new Checkbox("������");
	private Panel fruit = new Panel();
	
	private Label label2 = new Label("����� ���̴��?");
	private CheckboxGroup cg = new CheckboxGroup();
	private Checkbox box21 = new Checkbox("10��", cg, false);
	private Checkbox box22 = new Checkbox("20��", cg, false);
	private Checkbox box23 = new Checkbox("30��", cg, false);
	private Checkbox box24 = new Checkbox("40��", cg, false);
	private Panel age = new Panel();
	
	// �⺻ ������
	public MyFrame01(String title) {
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
	
	public void init() {
		this.setLayout(new GridLayout(4, 1));
		this.add(label1);
		this.add(fruit);
		this.add(label2);
		this.add(age);
		
		fruit.setLayout(new GridLayout(1, 4));
		fruit.add(box11);
		fruit.add(box12);
		fruit.add(box13);
		fruit.add(box14);
		
		age.setLayout(new GridLayout(1, 4));
		age.add(box21);
		age.add(box22);
		age.add(box23);
		age.add(box24);
	}
}

public class Test01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame01 mf = new MyFrame01("awt �ǽ�");
	}

}
