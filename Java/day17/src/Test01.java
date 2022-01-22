import java.awt.*;
import java.awt.event.*;

class StartButton extends Button {
	private static final long serialVersionUID = 1L;
	
	private Image img;
	
	public StartButton() {
		super();
	}
	
	public StartButton(Image img) {
		super();
		this.img = img;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}

class MineButton extends Button {
	private static final long serialVersionUID = 1L;
	
	private Image img;
	private int i, j;
	
	public int countNear;
	public boolean isMine, isClicked;
	
	public MineButton() {
		super();
		this.countNear = 0;
		this.isMine = false;
	}
	
	public MineButton(Image img) {
		super();
		this.countNear = 0;
		this.isMine = false;
		this.img = img;
	}

	@Override
	public void paint(Graphics g) {
		if(img != null) {
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public int getIndexI() {
		return this.i;
	}
	
	public int getIndexJ() {
		return this.j;
	}
	
	public void setIndex(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
}

class MineSearch extends Frame implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Panel minePanel, controlPanel, startPanel;
	private Label countLabel, timeLabel;
	private StartButton startButton;
	private MineButton[][] mineButton;
	
	private Image none, mineIn, mineOut, mineBoom, flag, start, game, success, fail;
	private int mineCount, time;
	
	// 기본 생성자
	public MineSearch(String title) {
		super(title);
		this.setSize(400, 400);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		setFrame();
		init();
	}
	
	// 마우스 제자리에서 눌렀다 뗏을 때
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	// 마우스 눌렀을 때
	@Override
	public void mousePressed(MouseEvent e) {}
	
	// 마우스 뗐을 때
	@Override
	public void mouseReleased(MouseEvent e) {
		Button button = (Button)e.getSource();
		String command = button.getActionCommand();
		MineButton pressedMineButton;
		switch(e.getButton()) {
		case MouseEvent.BUTTON1:
			switch(command) {
			case "start":
				generateMine();
				break;
			case "fail":
			case "success":
				startButton.setActionCommand("start");
				init();
				break;
			case "none":
				pressedMineButton = (MineButton)button;
				excludeButton(pressedMineButton.getIndexI(), pressedMineButton.getIndexJ());
				break;
			case "mine":
				pressedMineButton = (MineButton)button;
				mineBoom(pressedMineButton);
				startButton.setActionCommand("fail");
				break;
			default :
			}
			break;

		case MouseEvent.BUTTON3:
			switch(command) {
			case "none":
			case "mine":
				pressedMineButton = (MineButton)button;
				pressedMineButton.setActionCommand("flag");
				pressedMineButton.setImg(flag);
				pressedMineButton.repaint();
				break;
			case "flag":
				pressedMineButton = (MineButton)button;
				if(pressedMineButton.isMine) {
					pressedMineButton.setActionCommand("mine");
					pressedMineButton.setImg(none);
					pressedMineButton.repaint();
				} else {
					pressedMineButton.setActionCommand("none");
					pressedMineButton.setImg(none);
					pressedMineButton.repaint();
				}
				break;
			default :
			}
			break;

		default:
		}
		
	}
	
	// 마우스 올려놨을 때
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	// 마우스 빠져나왔을 때
	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void mineBoom(MineButton pressedMineButton) {
		
	}
	
	public void generateMine() {
		int randomI, randomJ, minI, minJ, maxI, maxJ;
		while(this.mineCount < 10) {
			randomI = (int)(Math.random() * 9);
			randomJ = (int)(Math.random() * 9);
			if(!mineButton[randomI][randomJ].isMine) {
				mineButton[randomI][randomJ].isMine = true;
				mineButton[randomI][randomJ].setActionCommand("mine");
				mineButton[randomI][randomJ].setLabel("mine");
				mineButton[randomI][randomJ].repaint();
				minI = randomI - 1; if(minI < 0) minI = 0;
				minJ = randomJ - 1; if(minJ < 0) minJ = 0;
				maxI = randomI + 1; if(maxI > 8) maxI = 8;
				maxJ = randomJ + 1; if(maxJ > 8) maxJ = 8;
				for(int p = minI; p <= maxI; p++) {
					for(int q = minJ; q <= maxJ; q++) {
						mineButton[p][q].countNear++;
					}
				}
				this.mineCount++;
			}
		}
		
		countLabel.setText(this.mineCount + " 개");
		startButton.setImg(game);
		startButton.repaint();
	}
	
	public void excludeButton(int i, int j) {
		if(mineButton[i][j].isClicked || mineButton[i][j].isMine) {
			return;
		}
		
		mineButton[i][j].isClicked = true;
		mineButton[i][j].setEnabled(false);
		mineButton[i][j].setImg(null);
		mineButton[i][j].repaint();
		if(mineButton[i][j].countNear > 0) {
			mineButton[i][j].setLabel(
					String.valueOf(mineButton[i][j].countNear));
		}
		
		if(mineButton[i][j].countNear > 0) {
			mineButton[i][j].setLabel(String.valueOf(mineButton[i][j].countNear));
			return;
		}
		
		if(i > 0) {
			excludeButton(i - 1, j);
		} // up
		
		if(i < 8) {
			excludeButton(i + 1, j);
		} // down
		
		if(j > 0) {
			excludeButton(i, j - 1);
		} // left
		
		if(j < 8) {
			excludeButton(i, j + 1);
		} // right
	}
	
	public void init() {
		for(int i = 0; i < mineButton.length; i++) {
			for(int j = 0; j < mineButton[i].length; j++) {
				mineButton[i][j].countNear = 0;
				mineButton[i][j].isMine = false;
				mineButton[i][j].isClicked = false;
				mineButton[i][j].setActionCommand("none");
				mineButton[i][j].setImg(none);
				mineButton[i][j].repaint();
			}
		}
		
		this.mineCount = 0;
		this.time = 60;
		startButton.setActionCommand("start");
		startButton.setImg(start);
		startButton.repaint();
	}
	
	public void setFrame() {
		this.setLayout(new BorderLayout());
		
		minePanel = new Panel(new GridLayout(9, 9, 5, 5));
		this.add("Center", minePanel);
		
		mineButton = new MineButton[9][9];
		for(int i = 0; i < mineButton.length; i++) {
			for(int j = 0; j < mineButton[i].length; j++) {
				mineButton[i][j] = new MineButton();
				mineButton[i][j].addMouseListener(this);;
				mineButton[i][j].setIndex(i, j);;
				minePanel.add(mineButton[i][j]);
			}
		}
		
		controlPanel = new Panel(new BorderLayout());
		this.add("North", controlPanel);
		
		countLabel = new Label(mineCount + " 개");
		controlPanel.add("West", countLabel);
		
		timeLabel = new Label(time + " 초");
		controlPanel.add("East", timeLabel);
		
		startPanel = new Panel(new FlowLayout());
		controlPanel.add("Center", startPanel);
		
		startButton = new StartButton();
		startButton.setPreferredSize(new Dimension(50, 50));
		startButton.addMouseListener(this);
		startPanel.add("Center", startButton);
		
		this.none = Toolkit.getDefaultToolkit().getImage("img/none.jpg");
		this.mineIn = Toolkit.getDefaultToolkit().getImage("img/mine_in.jpg");
		this.mineOut = Toolkit.getDefaultToolkit().getImage("img/mine_out.jpg");
		this.mineBoom = Toolkit.getDefaultToolkit().getImage("img/mine_boom.jpg");
		this.flag = Toolkit.getDefaultToolkit().getImage("img/flag.jpg");
		this.start = Toolkit.getDefaultToolkit().getImage("img/start.jpg");
		this.game = Toolkit.getDefaultToolkit().getImage("img/game.jpg");
		this.success = Toolkit.getDefaultToolkit().getImage("img/success.jpg");
		this.fail = Toolkit.getDefaultToolkit().getImage("img/fail.jpg");
	}

	
}

public class Test01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MineSearch mine = new MineSearch("마인밭 뚫기");
	}

}
