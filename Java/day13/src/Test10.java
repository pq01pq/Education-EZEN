import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;

class Calculator extends Frame {
	private static final long serialVersionUID = 1L;
	
	private TextField formula;
	private Panel opPanel;
	private GridLayout opGrid;
	private String[] opString;
	private Button[] operation;
	private Font formulaFont, operationFont;
	
	// 기본 생성자
	public Calculator(String title) {
		super(title);
		this.init();
		
		this.setSize(400, 400);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)( (screen.getWidth() / 2) - (this.getWidth() / 2) );
		int yPos = (int)( (screen.getHeight() / 2) - (this.getHeight() / 2) );
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void init() {
		// create border layout
		this.setLayout(new BorderLayout());

		// add formula display upside in border
		formula = new TextField();
		this.add("North", formula);

		// set formula font
		formulaFont = new Font("", Font.PLAIN, 50);
		formula.setFont(formulaFont);
		formula.setBackground(Color.DARK_GRAY);
		formula.setForeground(Color.BLACK);

		// create buttons
		opString = new String[] {
				"7","8","9","+","4","5","6","-","1","2","3","*","0","=","%","/"
		};
		operation = new Button[opString.length];
		for(int i = 0; i < opString.length; i++) {
			operation[i] = new Button(opString[i]);
		}
		
		// add operation panel on center in border
		opPanel = new Panel();
		this.add("Center", opPanel);

		// set panel layout as grid
		opGrid = new GridLayout(4, 4, 20, 20);
		opPanel.setLayout(opGrid);

		operationFont = new Font("", Font.PLAIN, 30);
		// add buttons on panel
		for(int i = 0; i < operation.length; i++) {
			operation[i].setFont(operationFont);
			operation[i].setBackground(Color.WHITE);
			operation[i].setForeground(Color.BLACK);			
		}
		operation[3].setForeground(Color.BLUE);
		operation[7].setForeground(Color.BLUE);
		operation[11].setForeground(Color.BLUE);
		operation[14].setForeground(Color.BLUE);
		operation[15].setForeground(Color.BLUE);
		operation[13].setForeground(Color.RED);

		opPanel.setBackground(Color.LIGHT_GRAY);
	}
}

public class Test10 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Calculator calculator = new Calculator("계산기");
	}

}
