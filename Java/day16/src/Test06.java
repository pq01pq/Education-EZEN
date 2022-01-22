import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyButton06 extends Button {
	private static final long serialVersionUID = 1L;
	
	private Image img;
	private int count;
	
	public MyButton06() {
		this.count = 0;
	}
	
	public MyButton06(Image img) {
		this.img = img;
		this.count = 0;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, 100, 100, this);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}

class MyFrame06 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Panel menuPanel, billPanel;
	private MyButton06[] menuButton;
	private Label titleLabel, totalLabel;
	private TextArea billArea;
	private final String[] menuString = new String[] {
			"밥", "국", "김치", 
			"물", "콜라", "사이다", 
			"소주", "맥주", "데낄라", 
	};
	private final int[] price = new int[] {
			10, 20, 30,
			40, 50, 60,
			70, 80, 90
	};
	private int[] count = new int[9];
	
	// 기본 생성자
	public MyFrame06(String title) {
		super(title);
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		setFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < menuButton.length; i++) {
			if(e.getSource() == menuButton[i]) {
				count[i]++;
				break;
			}
		}
		
		String bill = "";
		int total = 0;
		for(int i = 0; i < menuButton.length; i++) {
			if(count[i] > 0) {
				bill += menuString[i] + " : " +
						count[i] + "개 " + (count[i] * price[i]) + "원\n" ;
				total += count[i] * price[i];
			}
		}
		
		billArea.setText(bill);
		totalLabel.setText("총액 " + total + "원");
	}
	
	public void setFrame() {
		this.setLayout(new BorderLayout());
		
		menuPanel = new Panel(new GridLayout(3, 3));
		this.add("Center", menuPanel);
		
		menuButton = new MyButton06[9];
		for(int i = 0; i < menuButton.length; i++) {
			menuButton[i] = new MyButton06(Toolkit.getDefaultToolkit().getImage(
					"menu" + (i + 1) + ".jpg"));
			menuButton[i].setName(menuString[i]);
			menuButton[i].addActionListener(this);
			menuPanel.add(menuButton[i]);
		}
		
		billPanel = new Panel(new BorderLayout());
		this.add("East", billPanel);
		
		titleLabel = new Label("주문내역서");
		billArea = new TextArea();
		totalLabel = new Label("총액 0원");
		
		billPanel.add("North", titleLabel);
		billPanel.add("Center", billArea);
		billPanel.add("South", totalLabel);
		
		billArea.setPreferredSize(new Dimension(150, 400));
	}

	
}

public class Test06 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame06 mf = new MyFrame06("awt 실습");
	}

}
