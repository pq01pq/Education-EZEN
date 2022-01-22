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
	boolean isCalculated;	// 첫 계산에서 *, /, %가 앞에 오는 것을 검출
	boolean isTyping;	// 처음이나 계산 직후 입력중인지 여부를 검출
	boolean isError;	// 계산과정에서 에러를 검출
	
	// 기본 생성자
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
		// 화면에 Error 출력 후 다음 입력 시 초기 입력상태로 전환
		if(isError) {
			display = "0";
			isError = false;
		}
		
		String input = e.getActionCommand();	// e 객체가 가진 문자열값을 받음
		
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
			// 일반적인 경우
			if(isTyping || isCalculated) {
				display += input;
			// 처음 입력할 경우
			} else {
				display = input;
			}
			isTyping = true;
		} else {
			int index = formula.size() - 1;
			// 숫자를 입력중이었을 경우 숫자를 계속 채워넣음
			if(isTyping) {
				// 연산자 다음에 입력할 경우
				if(isOperator(formula.get(index))) {
					formula.add(input);
				// 피연산자를 입력하고있는 경우
				} else {
					formula.set(index, formula.get(index) + input);
				}
				display += input;
			// 숫자를 처음 입력할 경우 이전의 숫자를 대체함
			} else {
				formula.set(0, input);
				display = input;
				isTyping = true;
			}
		}
		
		// 연산자, 피연산자일 경우 화면에 출력, 오류일 경우 Error 출력
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
	
	// isError은 계산과정에서만 검출함
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
		MyCalculator10 calculator = new MyCalculator10("계산기");
	}

}
