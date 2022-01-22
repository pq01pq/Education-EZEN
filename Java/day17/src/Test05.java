import java.awt.*;
import javax.swing.*;

class MyFrame05 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// 기본 생성자
	public MyFrame05(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		init();
		
		try {
			Thread.sleep(2000);
		} catch(InterruptedException e) {}
		
//		JOptionPane.showMessageDialog(this, "버전1.0",
//				"알림", JOptionPane.INFORMATION_MESSAGE);
		
//		int res = JOptionPane.showConfirmDialog(this, "지뢰찾기프로그램 ㅈㄴ어렵",
//				"질문", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//		System.out.println(res);
		
		String name = JOptionPane.showInputDialog(this, "당신의 이름은?", "질문",
				JOptionPane.QUESTION_MESSAGE);
		System.out.println(name);
		
//		JFileChooser jfc = new JFileChooser(".");
//		int res = jfc.showOpenDialog(this);
//		if(res == 0) {
//			System.out.println(jfc.getSelectedFile().getName());
//		} else {
//			System.out.println("파일 선택 안함");
//		}
	}
	
	@SuppressWarnings("unused")
	public void init() {
		Container con = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Color color = JColorChooser.showDialog(this, "색상골라", Color.GREEN);
//		con.setBackground(color);
	}
}

public class Test05 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame05 mf = new MyFrame05("swing 실습");
	}

}
