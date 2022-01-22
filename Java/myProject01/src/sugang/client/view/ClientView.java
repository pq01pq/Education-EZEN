package sugang.client.view;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import sugang.client.controller.*;
import sugang.model.*;

public class ClientView extends JFrame implements MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private Container container;
	
	private JPanel selectPanel, subjectListPanel, searchPanel;
	private JScrollPane subjectListPane;
	private GridLayout subjectListCells;
	private Choice optionList;
	private JTextField searchField;
	private JButton timeTableButton, searchButton;
	
	private Client client;
	
	public ClientView(String title, Client client) {
		super(title);
		this.client = client;
		this.setFrame();
	}
	
	public void setDisplay(ArrayList<SubjectVO> subjects) {
		subjectListPanel.removeAll();
		subjectListCells.setRows(subjects.size());
		
		for(SubjectVO subject : subjects) {
			subjectListPanel.add(new SubjectPanel(subject, this));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton)e.getSource();
			switch(e.getButton()) {
			case MouseEvent.BUTTON1:
				switch(button.getName()) {
				case StudentVO.REGISTER:
				case StudentVO.CANCEL:
					String code = button.getActionCommand(); // 강의코드로 등록, 삭제
					client.send(button.getName(), optionList.getSelectedItem(), code, searchField.getText());
//					System.out.println(code); // error check
					break;
				case StudentVO.SEARCH: {
					String keyword = searchField.getText();
//					if(keyword.equals("")) {
//						return;
//					}
					if(keyword == null) {
						keyword = "";
					}
					
					client.send(StudentVO.SEARCH, optionList.getSelectedItem(), keyword);
					break;
				}
				case "detail": {
					
					break;
				}
				default :
				} // end of switch(button.getName())
				break;
				
			default :
			} // end of switch(e.getButton())
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER: {
			if(e.getSource() instanceof JTextField) {
				JTextField textField = (JTextField)e.getSource();
				switch(textField.getName()) {
				case StudentVO.SEARCH: {
					String keyword = searchField.getText();
					if(keyword.equals("")) {
						return;
					}
					String option = optionList.getSelectedItem();
					
					client.send(StudentVO.SEARCH, option, keyword);
					break;
				}
				default :
				}
			}
		}
		default :
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void setFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		// upper function area
		selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		container.add(BorderLayout.NORTH, selectPanel);
		
		timeTableButton = new JButton("시간표");
		selectPanel.add(timeTableButton);
		timeTableButton.setName("table");
		timeTableButton.addMouseListener(this);
		
		// center list area
		subjectListCells = new GridLayout(0, 1);
		subjectListPanel = new JPanel(subjectListCells);
		subjectListPane = new JScrollPane(subjectListPanel);
		container.add(BorderLayout.CENTER, subjectListPane);
		
		// lower search area
		searchPanel = new JPanel(new BorderLayout());
		container.add(BorderLayout.SOUTH, searchPanel);
		
		optionList = new Choice();
		searchPanel.add(BorderLayout.WEST, optionList);
		optionList.add("선택");
		optionList.add("강의명");
		optionList.add("강의코드");
		optionList.add("교수명");
		optionList.add("강의실");
		optionList.add("요일");
		
		searchField = new JTextField();
		searchPanel.add(BorderLayout.CENTER, searchField);
		searchField.setName(StudentVO.SEARCH);
		searchField.addKeyListener(this);
		
		searchButton = new JButton("검색");
		searchPanel.add(BorderLayout.EAST, searchButton);
		searchButton.setName(StudentVO.SEARCH);
		searchButton.addMouseListener(this);
	}
	
	private class SubjectPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		JTextArea subjectInfoArea;
		JPanel buttonPanel;
		JButton detailButton, applyAndCancelButton;
		
		SubjectVO subject;
		MouseListener outerMouseListener;
		
		public SubjectPanel(SubjectVO subject, MouseListener outerMouseListener) {
			super();
			this.subject = subject;
			this.outerMouseListener = outerMouseListener;
			this.setFrame();
		}
		
		public void setFrame() {
			this.setLayout(new BorderLayout());
			
			subjectInfoArea = new JTextArea();
			subjectInfoArea.setEditable(false);
			subjectInfoArea.setText(
					subject.getTitle() +
					" (" + subject.getRegNumber() + "/" + subject.getStudents().length + ")"
					);
			this.add(BorderLayout.CENTER, subjectInfoArea);
			
			buttonPanel = new JPanel(new FlowLayout());
			this.add(BorderLayout.EAST, buttonPanel);
			
			detailButton = new JButton("상세보기");
			applyAndCancelButton = new JButton(subject.isRegistered() ? "취소" : "신청");
			buttonPanel.add(detailButton);
			buttonPanel.add(applyAndCancelButton);
			detailButton.setName("detail");
			applyAndCancelButton.setName(subject.isRegistered() ? StudentVO.CANCEL : StudentVO.REGISTER);
			applyAndCancelButton.setActionCommand(subject.getCode()); // 신청과 삭제 키워드는 강의 코드
			detailButton.addMouseListener(outerMouseListener);
			applyAndCancelButton.addMouseListener(outerMouseListener);
		}
		
	}

//	public static void main(String[] args) {
//		new SubjectView("수강신청 시스템", null);
//	}
	
}
