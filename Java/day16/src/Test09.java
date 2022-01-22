import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DigdogButton extends Button {
	private static final long serialVersionUID = 1L;
	
	Image img;
	int xSize, ySize;
	
	public DigdogButton() {}
	
	public DigdogButton(Image digdog) {
		this.img = digdog;
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 10, 10, 50, 50, this);
	}
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image digdog) {
		this.img = digdog;
	}

	public void setSize(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
}

class DigdogGame extends Frame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private Panel digdogPanel, controlPanel;
	private DigdogButton[] digdogButton;
	private Button startButton;
	private Label scoreDisplay, timeDisplay;
	private int score, time;
	int preDigdogIndex;
	

	public DigdogGame(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setLayout(new BorderLayout());
		setDigdogButton();
		setControlPanel();
		buttonEnable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			buttonEnable(true);
			Thread timeThread = new Thread(this);
			timeThread.start();	// 시간 스레드 시작
		} else if(e.getActionCommand().equals("digdog")) {
			score++;
			scoreDisplay.setText("점수 " + score + "점");
			DigdogButton digdog = (DigdogButton)e.getSource();
			digdog.setActionCommand("");
			digdog.setImg(
					Toolkit.getDefaultToolkit().getImage("image/digdog_in.jpg"));
			digdog.repaint();
			generateRandomDigdog();
		}
	}
	
	@Override
	public void run() {
		init();
		generateRandomDigdog();
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
		for(int i = 0; i < digdogButton.length; i++) {
			digdogButton[i].setActionCommand("");
			digdogButton[i].setImg(
					Toolkit.getDefaultToolkit().getImage("image/digdog_in.jpg"));
			digdogButton[i].repaint();
		}
		this.score = 0;
		this.time = 10;
		this.preDigdogIndex = -1;
		scoreDisplay.setText("점수 0점");
		timeDisplay.setText("시간 10초");
	}
	
	public void generateRandomDigdog() {
		int randomIndex = (int)(Math.random() * 9);
		if(randomIndex == this.preDigdogIndex) {
			generateRandomDigdog();
			return;
		}
		preDigdogIndex = randomIndex;
		digdogButton[randomIndex].setActionCommand("digdog");
		digdogButton[randomIndex].setImg(
				Toolkit.getDefaultToolkit().getImage("image/digdog_out.jpg"));
		digdogButton[randomIndex].repaint();
	}
	
	public void buttonEnable(boolean enable) {
		startButton.setEnabled(!enable);
		for(int i = 0; i < digdogButton.length; i++) {
			digdogButton[i].setEnabled(enable);
		}
	}
	
	public void setDigdogButton() {
		digdogPanel = new Panel();
		digdogPanel.setLayout(new GridLayout(3, 3));
		this.add("Center", digdogPanel);
		
		digdogButton = new DigdogButton[9];
		for(int i = 0; i < digdogButton.length; i++) {
			digdogButton[i] = new DigdogButton(
					Toolkit.getDefaultToolkit().getImage("image/digdog_in.jpg"));
			digdogButton[i].addActionListener(this);
			digdogPanel.add(digdogButton[i]);
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

public class Test09 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DigdogGame game = new DigdogGame("Gatcha");
	}

}
