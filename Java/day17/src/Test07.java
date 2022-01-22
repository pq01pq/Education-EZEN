import java.awt.*;
import javax.swing.*;

class MyFrame07 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	ImageIcon ii1 = new ImageIcon("img/a.gif");
	ImageIcon ii2 = new ImageIcon("img/b.gif");
	ImageIcon ii3 = new ImageIcon("img/c.gif");
	ImageIcon ii4 = new ImageIcon("img/d.gif");
	
	JToggleButton jtb1 = new JToggleButton(ii1);
	JToggleButton jtb2 = new JToggleButton(ii2);
	JToggleButton jtb3 = new JToggleButton("버튼3", ii3);
	JToggleButton jtb4 = new JToggleButton("버튼4", ii4);
	ButtonGroup bg = new ButtonGroup();
	
	// 기본 생성자
	public MyFrame07(String title) {
		super(title);
		this.setSize(600, 300);
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
		
		con.setLayout(new GridLayout(1, 4));
		
		bg.add(jtb1);
		bg.add(jtb2);
		bg.add(jtb3);
		bg.add(jtb4);
		
		con.add(jtb1);
		con.add(jtb2);
		con.add(jtb3);
		con.add(jtb4);
		
		jtb1.setSelectedIcon(ii4);
		
		jtb2.setToolTipText("2번버튼이닷");
		
		jtb3.setHorizontalTextPosition(SwingConstants.CENTER);
		jtb3.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		jtb4.setRolloverIcon(ii1);
		
		// 단축키 : alt + 숫자키
		jtb1.setMnemonic('1');
		jtb2.setMnemonic('2');
		jtb3.setMnemonic('3');
		jtb4.setMnemonic('4');
		
	}
	
}

public class Test07 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame07 mf = new MyFrame07("swing 실습");
	}

}
