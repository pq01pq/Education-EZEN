package sugang.client.view;

import java.awt.*;
import javax.swing.*;

import sugang.model.*;

public class TimeTableView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Container container;
	private String[] weekDay = new String[] {
			"월", "화", "수", "목", "금", "토"
	};
	private JLabel[][] timeTableLabel = new JLabel[9][7];
	
	public TimeTableView(String title) throws HeadlessException {
		super(title);
		setFrame();
	}
	
	public void setFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setSize(800, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		container = this.getContentPane();
		container.setLayout(new GridLayout(9, 7));
		
		for(int i = 0; i < timeTableLabel.length; i++) {
			for(int j = 0; j < timeTableLabel[i].length; j++) {
				timeTableLabel[i][j] = new JLabel();
				container.add(timeTableLabel[i][j]);
			}
		}
		
		timeTableLabel[0][0].setText("");
		
		for(int i = 1; i < timeTableLabel.length; i++) {
			timeTableLabel[i][0].setText(String.valueOf(i) + "교시");
		}
		
		for(int j = 1; j < timeTableLabel[0].length; j++) {
			timeTableLabel[0][j].setText(weekDay[j - 1]);
		}
		
	}
	
	public static void main(String[] args) {
		new TimeTableView("시간표");
	}
	
}
