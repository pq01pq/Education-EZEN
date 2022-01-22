import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class MyFrame06 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JButton[] jbt = new JButton[8];
	
	// 기본 생성자
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
			jbt[i] = new JButton("버튼" + i);
			con.add(jbt[i]);
		}
		
		LineBorder b0 = new LineBorder(Color.RED, 4);
												// 선 두께
		jbt[0].setBorder(b0);
		
		MatteBorder b1 = new MatteBorder(10, 5, 10, 5, Color.BLUE);
										// 위, 왼, 아래, 오른 테두리 두께
		jbt[1].setBorder(b1);
		
		// 테두리 경계 없앰
		EmptyBorder b2 = new EmptyBorder(4,4,4,4);
		jbt[2].setBorder(b2);
		
		// 테두리를 빈공간으로
		EtchedBorder b3 = new EtchedBorder(EtchedBorder.LOWERED);
		jbt[3].setBorder(b3);
		
		// 들어가거나 나온 버튼(각짐)
		BevelBorder b4 = new BevelBorder(BevelBorder.RAISED);
		jbt[4].setBorder(b4);
		
		// 들어가거나 나온 버튼(둥금)
		SoftBevelBorder b5 = new SoftBevelBorder(SoftBevelBorder.LOWERED);
		jbt[5].setBorder(b5);;
		
		TitledBorder b6 = new TitledBorder("title");
		jbt[6].setBorder(b6);
		
		CompoundBorder b7 = new CompoundBorder(b6, b4);
											// 순서대로 버튼모양 적용
		jbt[7].setBorder(b7);;
	}
	
}

public class Test06 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame06 mf = new MyFrame06("swing 실습");
	}

}
