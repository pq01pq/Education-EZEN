import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

class MyCalendar06 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Calendar calendar;
	private Label yearMonth;
	private Button lastYear, lastMonth, nextMonth, nextYear;
	private Panel yearMonthPanel, leftArrows, rightArrows, monthPanel, weekDayPanel, dayPanel;
	private Button[] weekDayButton, dayButton;
	private Font yearMonthFont, weekDayFont, dayFont;
	
	private int xPos, yPos;
	private String[] weekDayString;
	
	// 기본 생성자
	public MyCalendar06(String title, Calendar calendar) {
		super(title);
		
		this.setSize(800, 600);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		this.yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		this.calendar = calendar;
		
		this.setLayout(new BorderLayout());
		
		// allocate year month panel
		yearMonthPanel = new Panel();
		yearMonth = new Label("", Label.CENTER);
		yearMonthFont = new Font("yearMonth", Font.BOLD, 40);
		
		// allocate left arrows
		leftArrows = new Panel();
		lastYear = new Button();
		lastMonth = new Button();
		
		// allocate right arrows
		rightArrows = new Panel();
		nextMonth = new Button();
		nextYear = new Button();
		
		// allocate month panel
		monthPanel = new Panel();
		
		// allocate weekday panel
		weekDayPanel = new Panel();
		weekDayString = new String[] {
				"일","월","화","수","목","금","토"
		};
		weekDayButton = new Button[7];
		weekDayFont = new Font("weekDay", Font.PLAIN, 20);
		
		// allocate day panel
		dayPanel = new Panel();
		dayFont = new Font("day", Font.PLAIN, 20);
		
		this.init();
	}
	
	public void init() {
		// locate year month panel
		yearMonthPanel.setLayout(new BorderLayout());
		this.add("North", yearMonthPanel);
		yearMonthPanel.add("Center", yearMonth);
		
		setYearMonth();
		yearMonth.setFont(yearMonthFont);
		
		// locate left arrows
		leftArrows.setLayout(new GridLayout(1, 2));
		yearMonthPanel.add("West", leftArrows);
		leftArrows.add(lastYear);
		leftArrows.add(lastMonth);
		
		// locate right arrows
		rightArrows.setLayout(new GridLayout(1, 2));
		yearMonthPanel.add("East", rightArrows);
		rightArrows.add(nextMonth);
		rightArrows.add(nextYear);
		
		setArrows();
		
		// locate month panel
		monthPanel.setLayout(new BorderLayout());
		this.add("Center", monthPanel);
		
		// locate week day panel
		weekDayPanel.setLayout(new GridLayout(1, 7));
		monthPanel.add("North", weekDayPanel);
		for(int i = 0; i < 7; i++) {
			weekDayButton[i] = new Button(weekDayString[i]);
			weekDayPanel.add(weekDayButton[i]);
		}
		
		setWeekDayButtons();
		weekDayPanel.setFont(weekDayFont);
		
		// locate day panel
		monthPanel.add("Center", dayPanel);
		
		setDayButtons();
		dayPanel.setFont(dayFont);
	}
	
	public void setYearMonth() {
		yearMonth.setText(
				calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월");
		
	}
	
	public void setArrows() {
		lastYear.setLabel("◀◀");
		lastMonth.setLabel(" ◀ ");
		
		nextMonth.setLabel(" ▶ ");
		nextYear.setLabel("▶▶");
		
		lastYear.addActionListener(this);
		lastMonth.addActionListener(this);
		nextMonth.addActionListener(this);
		nextYear.addActionListener(this);
	}
	
	public void setWeekDayButtons() {
		if(changeWeekDays()) {
			for(int i = 0; i < 7; i++) {
				weekDayButton[i].setLabel(weekDayString[i]);;
			}
		}
		
		for(int i = 0; i < 7; i++) {
			weekDayButton[i].setForeground(Color.BLACK);
			if(weekDayButton[i].getLabel().equals("토")) {
				weekDayButton[i].setForeground(Color.BLUE);
			} else if(weekDayButton[i].getLabel().equals("일")) {
				weekDayButton[i].setForeground(Color.RED);
			}
		}
	}
	
	public boolean changeWeekDays() {
		int firstWeekDay = calendar.getFirstDayOfWeek();
		
		if(firstWeekDay == Calendar.SUNDAY && weekDayString[0].equals("월")) {
			String temp = weekDayString[6];
			for(int i = 0; i < 6; i++) {
				weekDayString[i + 1] = weekDayString[i];
			}
			weekDayString[0] = temp;
			
			return true;
			
		} else if(firstWeekDay == Calendar.MONDAY && weekDayString[0].equals("일")) {
			String temp = weekDayString[0];
			for(int i = 0; i < 6; i++) {
				weekDayString[i] = weekDayString[i + 1];
			}
			weekDayString[6] = temp;
			
			return true;
		}
		
		return false;
	}
	
	public void setDayButtons() {
		int finalWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		dayPanel.removeAll();
		
		dayPanel.setLayout(new GridLayout(finalWeek, 7));
		
		dayButton = new Button[finalWeek * 7];
		
		int startWeekDay = calendar.get(Calendar.DAY_OF_WEEK);
		
		int weekDay = calendar.getFirstDayOfWeek();
		int indexOfDayGrid = 0;
		while(weekDay < startWeekDay) {
			dayButton[indexOfDayGrid] = new Button();
			dayButton[indexOfDayGrid].setEnabled(false);
			weekDay++;
			indexOfDayGrid++;
		}

		int finalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int day = 1;
		while(day <= finalDay) {
			dayButton[indexOfDayGrid] = new Button(String.valueOf(day));
			if(weekDay == Calendar.SATURDAY) {
				dayButton[indexOfDayGrid].setForeground(Color.BLUE);
			} else if(weekDay == Calendar.SUNDAY) {
				dayButton[indexOfDayGrid].setForeground(Color.RED);
			}
			day++;
			weekDay++;
			indexOfDayGrid++;
			if(weekDay % 7 == calendar.getFirstDayOfWeek()) {
				weekDay = calendar.getFirstDayOfWeek();
			}
		}

		while(indexOfDayGrid < dayButton.length) {
			dayButton[indexOfDayGrid] = new Button();
			dayButton[indexOfDayGrid].setEnabled(false);
			indexOfDayGrid++;
		}

		for(int i = 0; i < dayButton.length; i++) {
			dayPanel.add(dayButton[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(lastYear.getLabel())) {
			calendar.set(
					calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), 1);
		} else if(e.getActionCommand().equals(lastMonth.getLabel())) {
			calendar.set(
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
		} else if(e.getActionCommand().equals(nextMonth.getLabel())) {
			calendar.set(
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
		} else if(e.getActionCommand().equals(nextYear.getLabel())) {
			calendar.set(
					calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH), 1);
		}
//		System.out.println(calendar.get(Calendar.YEAR));
//		System.out.println(calendar.get(Calendar.MONTH) + 1);
		
		setYearMonth();
		setDayButtons();
	}
}

public class Test06 {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int year = 2021;
		int month = 4;
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		MyCalendar06 mc = new MyCalendar06("awt 실습", calendar);
	}

}
