import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame09 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Button[] button;
	Panel upPanel, downPanel;
	TextArea messageArea;
	
	// 기본 생성자
	public MyFrame09(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		this.init();
	}
	
	public void init() {
		this.setLayout(new GridLayout(2, 1));
		
		upPanel = new Panel();
		upPanel.setLayout(new GridLayout(1, 3));
		this.add("North", upPanel);
		
		button = new Button[3];
		button[0] = new Button("left");
		button[1] = new Button("middle");
		button[2] = new Button("right");
		for(int i = 0; i < button.length; i++) {
			button[i].addActionListener(this);
			upPanel.add(button[i]);
		}
		
		messageArea = new TextArea();
		this.add("Center", messageArea);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message;
		switch(e.getActionCommand()) {
		case "left":
			message = "왼쪽버튼 눌림\n";
			break;
		case "middle":
			message = "가운데버튼 눌림\n";
			break;
		case "right":
			message = "오른쪽버튼 눌림\n";
			break;
		default:
			message = "error\n";
		}
		messageArea.setText(message);
		
//		try {
//			Thread.sleep(3000);
//		} catch(InterruptedException ee) {}
//		messageArea.setText("click button");
	}
	
}

public class Test09 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame09 mf = new MyFrame09("awt 실습");
	}

}
