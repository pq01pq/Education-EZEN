import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class MyFrame06 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JButton[] jbt = new JButton[8];
	
	// �⺻ ������
	public MyFrame06(String title) {
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
		con.setLayout(new GridLayout(4, 2));
		
		for(int i = 0; i < jbt.length; i++) {
			jbt[i] = new JButton("��ư" + i);
			con.add(jbt[i]);
		}
		
		LineBorder b0 = new LineBorder(Color.RED, 4);
												// �� �β�
		jbt[0].setBorder(b0);
		
		MatteBorder b1 = new MatteBorder(10, 5, 10, 5, Color.BLUE);
										// ��, ��, �Ʒ�, ���� �׵θ� �β�
		jbt[1].setBorder(b1);
		
		// �׵θ� ��� ����
		EmptyBorder b2 = new EmptyBorder(4,4,4,4);
		jbt[2].setBorder(b2);
		
		// �׵θ��� ���������
		EtchedBorder b3 = new EtchedBorder(EtchedBorder.LOWERED);
		jbt[3].setBorder(b3);
		
		// ���ų� ���� ��ư(����)
		BevelBorder b4 = new BevelBorder(BevelBorder.RAISED);
		jbt[4].setBorder(b4);
		
		// ���ų� ���� ��ư(�ձ�)
		SoftBevelBorder b5 = new SoftBevelBorder(SoftBevelBorder.LOWERED);
		jbt[5].setBorder(b5);;
		
		TitledBorder b6 = new TitledBorder("title");
		jbt[6].setBorder(b6);
		
		CompoundBorder b7 = new CompoundBorder(b6, b4);
											// ������� ��ư��� ����
		jbt[7].setBorder(b7);;
	}
	
}

public class Test06 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame06 mf = new MyFrame06("swing �ǽ�");
	}

}
