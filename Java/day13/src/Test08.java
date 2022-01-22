import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;

class MyFrame08 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private Label label;
	private BorderLayout bl;
	private GridLayout dialGrid;
	private Panel dialPanel;
	private Button[] button;
	private Font font;
	private Color color;
	private Cursor cursor;
	
	// 기본 생성자
	public MyFrame08(String title) {
		super(title);
		
		bl = new BorderLayout();
		this.setLayout(bl);
		
		// label
		label = new Label("전화기 예제", Label.CENTER);
		{
			// font
			font = new Font("", Font.PLAIN, 30);
			label.setFont(font);
			// font end
			
			// color
			color = new Color(0,255,0);
			label.setForeground(color);
			label.setBackground(Color.BLUE);
			// color end
			
			//cursor
			cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
			label.setCursor(cursor);	// new Cursor(Cursor.WAIT_CURSOR)
			// cursor end
		}
		this.add("North", label);
		// label end
			
		// button 
		button = new Button[12];
		for(int i = 0; i < 9; i++) {
			button[i] = new Button(String.valueOf(i + 1));
			button[i].setForeground(Color.RED);
		}
		button[9] = new Button("*");
		button[10] = new Button("0"); button[10].setForeground(Color.RED);
		button[11] = new Button("#");
		// button end
		
		// grid
		dialGrid = new GridLayout(4, 3, 10, 10);
		{
			// panel
			dialPanel = new Panel();
			dialPanel.setLayout(dialGrid);
			this.add("Center", dialPanel);
			// panel end
		}
		for(int i = 0; i < button.length; i++) {
			dialPanel.add(button[i]);
		}
		// grid end
		
		
		
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test08 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame08 mf = new MyFrame08("awt 실습");
	}

}
