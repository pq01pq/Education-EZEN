import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NumButton extends Button {
	private static final long serialVersionUID = 1L;
	
	//private int xSize, ySize;
	private int num;
	
	@Override
	public void paint(Graphics g) {
		g.setFont(new Font("", Font.BOLD, 30));
		g.drawString(String.valueOf(num), 50, 50);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

//	public void setSize(int xSize, int ySize) {
//		this.xSize = xSize;
//		this.ySize = ySize;
//	}
	
}

class NumGame extends Frame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private Panel numPanel, controlPanel;
	private NumButton[] numButton;
	private Button startButton;
	private Label scoreDisplay, timeDisplay;
	private int score, time, count;
	boolean enable;
	private int[] randomSecuence;
	

	public NumGame(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setLayout(new BorderLayout());
		setNumButton();
		setControlPanel();
		buttonEnable(false);
		randomSecuence = new int[9];
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			buttonEnable(true);
			Thread timeThread = new Thread(this);
			timeThread.start();	// 시간 스레드 시작
		} else if(e.getActionCommand().equals(String.valueOf(count))) {
			count++;
			score++;
			scoreDisplay.setText("점수 " + score + "점");
			if(count >= 10) {
				count = 1;
				generateRandomButton();
			}
		}
	}
	
	@Override
	public void run() {
		init();
		generateRandomButton();
		while(time > 0) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
			time--;
			timeDisplay.setText("시간 " + time + "초");
		}
		buttonEnable(false);
	}
	
	public void init() {
		for(int i = 0; i < numButton.length; i++) {
			numButton[i].setNum(0);
			numButton[i].setActionCommand("");
		}
		this.score = 0;
		this.time = 10;
		this.count = 1;
		scoreDisplay.setText("점수 0점");
		timeDisplay.setText("시간 10초");
	}
	
	public void generateRandomButton() {
		for(int i = 0; i < randomSecuence.length; i++) {
			randomSecuence[i] = (int)(Math.random() * 9) + 1;
			for(int j = 0; j < i; j++) {
				if(randomSecuence[i] == randomSecuence[j]) {
					i--;
					break;
				}
			}
		}
		
		for(int i = 0; i < randomSecuence.length; i++) {
			numButton[i].setActionCommand(String.valueOf(randomSecuence[i]));
			numButton[i].setNum(randomSecuence[i]);
			numButton[i].repaint();
		}
	}

	public void buttonEnable(boolean enable) {
		startButton.setEnabled(!enable);
		for(int i = 0; i < numButton.length; i++) {
			numButton[i].setEnabled(enable);
		}
	}
	
	public void setNumButton() {
		numPanel = new Panel();
		numPanel.setLayout(new GridLayout(3, 3));
		this.add("Center", numPanel);
		
		numButton = new NumButton[9];
		for(int i = 0; i < numButton.length; i++) {
			numButton[i] = new NumButton();
			numButton[i].addActionListener(this);
			numPanel.add(numButton[i]);
		}
	}
	
	public void setControlPanel() {
		controlPanel = new Panel(new GridLayout(3, 1));
		this.add("East", controlPanel);
		controlPanel.setPreferredSize(new Dimension(
				(int)(this.getWidth() / 5.0), this.getHeight()));
		
		scoreDisplay = new Label();
		timeDisplay = new Label();
		
		controlPanel.add(scoreDisplay);
		controlPanel.add(timeDisplay);
		
		startButton = new Button("시작");
		startButton.addActionListener(this);
		controlPanel.add("South", startButton);
	}
}

public class Test05 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		NumGame game = new NumGame("숫자 게임");
	}

}
