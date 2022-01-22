import java.awt.*;
import javax.swing.*;

class MyFrame01 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JDesktopPane jtp = new JDesktopPane();
	private JInternalFrame jif1 = new JInternalFrame("1��", true, true, true, true);
	private JInternalFrame jif2 = new JInternalFrame("2��", false, true, true, true);
	private JInternalFrame jif3 = new JInternalFrame("3��", true, false, true, true);
	private JInternalFrame jif4 = new JInternalFrame("4��", true, true, false, true);
	private JInternalFrame jif5 = new JInternalFrame("5��", true, true, true, false);
						// ����, ũ������ ���, �ݱ� ���, �ִ�ȭ ���, �ּ�ȭ ���
	
	// �⺻ ������
	public MyFrame01(String title) {
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
	
	public void init() {
		Container con = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		con.setLayout(new BorderLayout());
		con.add("North", new Label("�����Ӿȿ� ������ �ֱ�"));
		con.add("Center", jtp);
		
		jif1.setBounds(0, 0, 200, 150);
		jif1.setVisible(true);
		jif2.setBounds(0, 20, 200, 150);
		jif2.setVisible(true);
		jif3.setBounds(0, 40, 200, 150);
		jif3.setVisible(true);
		jif4.setBounds(0, 60, 200, 150);
		jif4.setVisible(true);
		jif5.setBounds(0, 80, 200, 150);
		jif5.setVisible(true);
		jtp.add(jif1);
		jtp.add(jif2);
		jtp.add(jif3);
		jtp.add(jif4);
		jtp.add(jif5);
		
		jif1.setLayout(new GridLayout(2, 2));
		jif1.add(new Button("jif1"));
		jif1.add(new Button("jif2"));
		jif1.add(new Button("jif3"));
		jif1.add(new Button("jif4"));
	}
	
}

public class Test01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame01 mf = new MyFrame01("swing �ǽ�");
	}

}
