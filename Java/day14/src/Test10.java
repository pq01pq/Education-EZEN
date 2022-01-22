import java.awt.*;
import java.awt.event.*;
import java.util.*;

class MyCalculator10 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private TextField formulaField;
	private Panel opPanel;
	private String[] opString;
	private Button[] operation;
	private Font formulaFont, operationFont;
	ArrayList<String> formula;
	String display, answer;
	boolean isCalculated;	// ù ��꿡�� *, /, %�� �տ� ���� ���� ����
	boolean isTyping;	// ó���̳� ��� ���� �Է������� ���θ� ����
	boolean isError;	// ���������� ������ ����
	
	// �⺻ ������
	public MyCalculator10(String title) {
		super(title);
		this.setSize(400, 400);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)( (screen.getWidth() / 2) - (this.getWidth() / 2) );
		int yPos = (int)( (screen.getHeight() / 2) - (this.getHeight() / 2) );
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.setFormulaField();
		this.setOperationPanel();
		this.setOperationButtons();
		
		this.formula = new ArrayList<>();
		this.init();
		this.display = "0";
		formulaField.setText(display);
		isError = false;
	}
	
	public void init() {
		formula.clear();
		answer = "0";
		formula.add(answer);
		isTyping = false;
		isCalculated = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ȭ�鿡 Error ��� �� ���� �Է� �� �ʱ� �Է»��·� ��ȯ
		if(isError) {
			display = "0";
			isError = false;
		}
		
		String input = e.getActionCommand();	// e ��ü�� ���� ���ڿ����� ����
		
		if(input.equals("=")) {
			calculate();
			if(isError) {
				init();	// init() has isTyping = false;
				display = "Error";
			} else {
				isCalculated = true;
				display = answer;
				isTyping = false;
			}
		} else if(isOperator(input)){
			formula.add(input);
			// �Ϲ����� ���
			if(isTyping || isCalculated) {
				display += input;
			// ó�� �Է��� ���
			} else {
				display = input;
			}
			isTyping = true;
		} else {
			int index = formula.size() - 1;
			// ���ڸ� �Է����̾��� ��� ���ڸ� ��� ä������
			if(isTyping) {
				// ������ ������ �Է��� ���
				if(isOperator(formula.get(index))) {
					formula.add(input);
				// �ǿ����ڸ� �Է��ϰ��ִ� ���
				} else {
					formula.set(index, formula.get(index) + input);
				}
				display += input;
			// ���ڸ� ó�� �Է��� ��� ������ ���ڸ� ��ü��
			} else {
				formula.set(0, input);
				display = input;
				isTyping = true;
			}
		}
		
		// ������, �ǿ������� ��� ȭ�鿡 ���, ������ ��� Error ���
		formulaField.setText(display);
	}
	
	public boolean isOperator(String factor) {
		if(factor.equals("+") ||
				factor.equals("-") ||
				factor.equals("*") ||
				factor.equals("/") ||
				factor.equals("%")) {
			return true;
		} else {
			return false;
		}
	}
	
	// isError�� ������������ ������
	public void calculate() {
		String firstFactor = (formula.size() > 1) ? formula.get(1) : "";
		String lastFactor = formula.get(formula.size() - 1);
		
		// start with illegal operator
		if(!isCalculated && (firstFactor.equals("*") ||
							 firstFactor.equals("/") ||
							 firstFactor.equals("%"))) {
			isError = true;
			return;
		}
		// end with operator
		if(isOperator(lastFactor)) {
			isError = true;
			return;
		}
		
		int result;
		
		// !! formula size changes while operation !!
		// consider modulation
		for(int i = 1; i < formula.size(); i++) {
			if(formula.get(i).equals("%")) {
				i--;
				result = operate(i);
				if(isError) {
					return;
				}
				formula.add(i, String.valueOf(result));
			}
		}
		
		// consider multiply, divide
		for(int i = 1; i < formula.size(); i++) {
			if(formula.get(i).equals("*") || formula.get(i).equals("/")) {
				i--;
				result = operate(i);
				if(isError) {
					return;
				}
				formula.add(i, String.valueOf(result));
			}
		}
		
		// plus, minus
		while(formula.size() > 1) {
			result = operate(0);
			if(isError) {
				return;
			}
			formula.add(0, String.valueOf(result));
		}
		
		answer = formula.get(0);
	}
	
	public int operate(int index) {
		int operand1 = Integer.parseInt(formula.remove(index));
		String operator = formula.remove(index);	// remove operator
		int operand2 = Integer.parseInt(formula.remove(index));
		int result = 0;
		switch(operator) {
		case "+":
			result = operand1 + operand2;
			break;
		case "-":
			result = operand1 - operand2;
			break;
		case "*":
			result = operand1 * operand2;
			break;
		case "/":
			if(operand2 == 0) {
				isError = true;
			} else {
				result = operand1 / operand2;
			}
			break;
		case "%":
			result = operand1 % operand2;
			break;
		default :
			isError = true;
		}
		
		return result;
	}
	
	public void setFormulaField() {
		// allocate formula field
		formulaField = new TextField();
		formulaFont = new Font("", Font.PLAIN, 50);
		
		// locate formula field
		this.add("North", formulaField);
		
		formulaField.setText(display);
		formulaField.setEditable(false);
		formulaField.setFont(formulaFont);
		formulaField.setBackground(Color.DARK_GRAY);
		formulaField.setForeground(Color.BLACK);
	}
	
	public void setOperationPanel() {
		// allocate operation panel
		opPanel = new Panel();
		
		// locate operation panel
		this.add("Center", opPanel);
		
		opPanel.setLayout(new GridLayout(4, 4, 20, 20));
		opPanel.setBackground(Color.LIGHT_GRAY);
	}
	
	public void setOperationButtons() {
		// allocate operation buttons
		opString = new String[] {
				"7","8","9","+",
				"4","5","6","-",
				"1","2","3","*",
				"0","=","%","/"
		};
		operation = new Button[opString.length];
		operationFont = new Font("", Font.PLAIN, 30);
		
		for(int i = 0; i < opString.length; i++) {
			operation[i] = new Button(opString[i]);
		}
		
		// locate buttons
		for(int i = 0; i < operation.length; i++) {
			operation[i].addActionListener(this);
			operation[i].setFont(operationFont);
			
			if(operation[i].getLabel().equals("=")) {
				operation[i].setForeground(Color.RED);
			} else if(operation[i].getLabel().equals("+") ||
					operation[i].getLabel().equals("-") ||
					operation[i].getLabel().equals("*") ||
					operation[i].getLabel().equals("/") ||
					operation[i].getLabel().equals("%")) {
				operation[i].setForeground(Color.BLUE);
			} else {
				operation[i].setForeground(Color.BLACK);
			}
			
			operation[i].setBackground(Color.WHITE);
			opPanel.add(operation[i]);
		}
	}
	
}

public class Test10 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyCalculator10 calculator = new MyCalculator10("����");
	}

}
