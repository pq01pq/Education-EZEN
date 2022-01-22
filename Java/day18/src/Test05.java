import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Rider extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Image img;
	private int distance;
	private int rank;
	private double record;
	private boolean finished;
	private boolean recorded;
	private int characterSize;
	
	public Rider(Container container) {
		super();
		this.distance = 0;
		this.record = 0.0;
		this.finished = false;
		this.characterSize = container.getWidth() / 10;
	}

	@Override
	public void run() {
		while(!finished) {
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {}
			distance += (int)(Math.random() * 5) + 1;
			repaint();
			
			if(distance >= this.getHeight() - characterSize) {
				finished = true;
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, this.getWidth() / 4, distance, characterSize, characterSize, this);
	}
	
	public Image getImg() {
		return img;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}

	public int getDistance() {
		return distance;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public double getRecord() {
		return record;
	}
	
	public void setRecord(double record) {
		this.record = record;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public boolean isRecorded() {
		return recorded;
	}

	public void setRecorded(boolean recorded) {
		this.recorded = recorded;
	}
	
}

class KartRider extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private Container container;
	private JPanel setPanel, trackPanel, ctrlPanel, displayPanel;
	private GridLayout displayLayout;
	private JLabel countRiderLabel;
	private JTextField countRiderField;
	private JButton exeButton, initButton, startButton;
	private Rider[] rider;
	private Thread timeThread;
	private Thread[] riderThread;
	private int countRider;
	private int rankCounter;
	private double time;
	private boolean finished;
	
	// 기본 생성자
	public KartRider(String title) {
		super(title);
		this.setSize(500, 900);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		memAlloc();
		init();
	}
	
	@Override
	public void run() {
		while(!finished) {
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {}
			time += 0.01;
			
			for(int i = 0; i < countRider; i++) {
				finished &= rider[i].isFinished();
				if(rider[i].isFinished() && !rider[i].isRecorded()) {
					displayPanel.add(new JLabel(rankCounter + "등 : " + ((int)(time * 100)) / 100.0 + "초"));
					rider[i].setRank(rankCounter);
					rider[i].setRecord(time);
					rider[i].setRecorded(true);
					displayLayout.setRows(displayLayout.getRows() + 1);
					rankCounter++;
				}
			}
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "exe":
			exe();
			break;
		case "init":
			init();
			break;
		case "start":
			rush();
			break;
			
		default :
		}
		
	}
	
	public void rush() {
		if(countRider > 0) {
			
			for(int i = 0; i < countRider; i++) {
				riderThread[i].start();
			}
			
			timeThread.start();
		}
	}
	
	public void init() {
		countRider = 0;
		rankCounter = 1;
		time = 0;
		finished = false;
		trackPanel.removeAll();
		displayPanel.removeAll();
		displayLayout.setRows(1);
		countRiderField.setText("");
	}
	
	public void exe() {
		countRider = Integer.parseInt(countRiderField.getText());
		
		trackPanel.setLayout(new GridLayout(1, countRider));
		
		rider = new Rider[countRider];
		riderThread = new Thread[countRider];
		
		timeThread = new Thread(this);
		
		if(countRider > 0) {
			for(int i = 0; i < countRider; i++) {
				rider[i] = new Rider(container);
				rider[i].setImg(Toolkit.getDefaultToolkit().getImage("img/kart" + (int)(Math.random() * 3) + ".jpg"));
				rider[i].repaint();
				trackPanel.add(rider[i]);
				riderThread[i] = new Thread(rider[i]);
			}
		} else {
			init();
		}
	}
	
	public void memAlloc() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		setPanel = new JPanel(new GridLayout(1, 4));
		container.add("North", setPanel);
		
		countRiderLabel = new JLabel("유저 수");
		countRiderField = new JTextField();
		exeButton = new JButton("실행");
		initButton = new JButton("초기화");
		exeButton.addActionListener(this);
		initButton.addActionListener(this);
		exeButton.setActionCommand("exe");
		initButton.setActionCommand("init");
		setPanel.add(countRiderLabel);
		setPanel.add(countRiderField);
		setPanel.add(exeButton);
		setPanel.add(initButton);
		
		trackPanel = new JPanel();
		container.add("Center", trackPanel);
		
		ctrlPanel = new JPanel(new BorderLayout());
		ctrlPanel.setPreferredSize(new Dimension(400, 100));
		container.add("South", ctrlPanel);
		
		displayPanel = new JPanel();
		ctrlPanel.add("Center", displayPanel);
		
		displayLayout = new GridLayout(1, 1);
		displayPanel.setLayout(displayLayout);
		
		startButton = new JButton("Turbo Start");
		startButton.addActionListener(this);
		startButton.setActionCommand("start");
		ctrlPanel.add("East", startButton);
	}
	
	public int getCountRider() {
		return countRider;
	}

	public double getTime() {
		return time;
	}
	
}

public class Test05 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		KartRider kart = new KartRider("카트라이더 러쉬플러스");
	}

}
