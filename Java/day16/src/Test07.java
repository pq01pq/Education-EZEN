import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame07 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final int ROCK = 1;
	private static final int SISSOR = 2;
	private static final int PAPER = 3;
	
	private Panel controlPanel;
	private Label messageLabel;
	private Image img;
	private int myRsp, comRsp;
	private int win, lose, draw;
	
	// ±âº» »ý¼ºÀÚ
	public MyFrame07(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		setFrame();
		init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int command = Integer.parseInt(e.getActionCommand());
		switch(command) {
		case ROCK:
		case SISSOR:
		case PAPER:
			myRsp = command;
			generateComRsp();
			match();
			break;
		case 4:
			messageLabel.setText(
					"ÀüÀû : " + win + "½Â " + lose + "ÆÐ " + draw + "¹«");
			break;
		case 5:
			init();
			break;
		case 6:
			System.exit(0);
			break;
		default :
		}
	}
	
	public void match() {
		switch(myRsp){
			case ROCK:
				switch(comRsp) {
				case ROCK:
					messageLabel.setText("ºñ±è");
					draw++;
					break;
				case SISSOR:
					messageLabel.setText("ÀÌ±è");
					win++;
					break;
				case PAPER:
					messageLabel.setText("Áü");
					lose++;
					break;
				default :
					generateComRsp();
					match();
				}
				break;
			case SISSOR:
				switch(comRsp) {
				case ROCK:
					messageLabel.setText("Áü");
					lose++;
					break;
				case SISSOR:
					messageLabel.setText("ºñ±è");
					draw++;
					break;
				case PAPER:
					messageLabel.setText("ÀÌ±è");
					win++;
					break;
				default :
					generateComRsp();
					match();
				}
				break;
			case PAPER:
				switch(comRsp) {
				case ROCK:
					messageLabel.setText("ÀÌ±è");
					win++;
					break;
				case SISSOR:
					messageLabel.setText("Áü");
					lose++;
					break;
				case PAPER:
					messageLabel.setText("ºñ±è");
					draw++;
					break;
				default :
					generateComRsp();
					match();
				}
				break;
			default:
				generateComRsp();
				match();
		}
	}
	
	public void generateComRsp() {
		comRsp = (int)(Math.random() * 3) + 1;
		img = Toolkit.getDefaultToolkit().getImage("image/rsp" + comRsp + ".jpg");
		repaint();
	}
	
	public void init() {
		messageLabel.setText("Á×À½ÀÇ °¡À§¹ÙÀ§º¸");
		
		img = Toolkit.getDefaultToolkit().getImage("image/rsp0.gif");
		repaint();
		
		myRsp = comRsp = 0;
		win = lose = draw = 0;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 100, 100, 150, 150, this);
	}
	
	public void setFrame() {
		this.setLayout(new BorderLayout());
		
		controlPanel = new Panel(new GridLayout(6, 1));
		this.add("East", controlPanel);
		
		controlPanel.add(new Button("°¡À§"));
		controlPanel.add(new Button("¹ÙÀ§"));
		controlPanel.add(new Button("º¸"));
		controlPanel.add(new Button("ÀüÀû"));
		controlPanel.add(new Button("´Ù½Ã"));
		controlPanel.add(new Button("Á¾·á"));
		
		for(int i = 0; i < controlPanel.getComponentCount(); i++) {
			((Button)controlPanel.getComponent(i)).addActionListener(this);
			((Button)controlPanel.getComponent(i)).setActionCommand(String.valueOf(i + 1));
		}
		
		messageLabel = new Label();
		messageLabel.setAlignment(Label.CENTER);
		this.add("North", messageLabel);
	}
}

public class Test07 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame07 mf = new MyFrame07("awt ½Ç½À");
	}

}
