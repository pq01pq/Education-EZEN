import java.awt.*;
import javax.swing.*;

class MyFrame02 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JToolBar jtb = new JToolBar();
	private JButton jbt1 = new JButton("1��");
	private JButton jbt2 = new JButton("2��");
	
	private JTabbedPane jtp = new JTabbedPane();
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	
	// �⺻ ������
	public MyFrame02(String title) {
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
		con.add("North", jtb);	// �����ϰ� ��ġ ���� ����
		jtb.add(jbt1);
		jtb.add(jbt2);
		
		con.add("Center",jtp);
		jp1.setBackground(Color.RED);
		jp2.setBackground(Color.BLUE);
		jtp.addTab("1�� �г�", jp1);
		jtp.addTab("2�� �г�", jp2);
		
	}
	
}

public class Test02 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame02 mf = new MyFrame02("swing �ǽ�");
	}

}
