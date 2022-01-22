package sugang.server.view;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import sugang.server.controller.Server;
import sugang.model.*;

public class ServerView extends JFrame implements MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private Container container;
	
	private JPanel selectPanel, subjectListPanel, searchPanel;
	private JScrollPane subjectListPane;
	private GridLayout subjectListCells;
	private Choice optionList;
	private JTextField searchField;
	private JButton inputButton, searchButton;
	private InputPopupFrame inputPopup;
	
	private Server server;
	
	public ServerView(String title, Server server) {
		super(title);
		this.server = server;
		this.setFrame();
		setDisplay(server.searchAllSubjects());
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
				case "input": {
					inputPopup = new InputPopupFrame("���� �Է�", this);
					break;
				}
				case SubjectVO.INSERT: {
					try {
						if(inputPopup.insertField[0].getText().equals("") ||
								inputPopup.insertField[1].getText().equals("") ||
								inputPopup.insertField[2].getText().equals("") ||
								inputPopup.insertField[3].getText().equals("") ||
								inputPopup.insertField[4].getText().equals("") ||
								inputPopup.insertField[5].getText().equals("")) {
							JOptionPane.showMessageDialog(inputPopup,
									"��ĭ ���� �Է��Ͻʽÿ�.", "�˸�",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						int limit = Integer.parseInt(inputPopup.insertField[5].getText());
						if(limit < 1) {
							JOptionPane.showMessageDialog(inputPopup,
									"�����ο��� 1�� �̻� �����Ͻʽÿ�.", "�˸�",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						SubjectVO subject = new SubjectVO(
								inputPopup.insertField[0].getText(),
								inputPopup.insertField[1].getText(),
								inputPopup.insertField[2].getText(),
								inputPopup.insertField[3].getText(),
								inputPopup.insertField[4].getText(), limit);
						inputPopup.dispose();
						
						server.insertSubject(subject);
						
						ArrayList<SubjectVO> subjects = server.searchAllSubjects();
						setDisplay(subjects);
						
					} catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(inputPopup,
								"�����ο��� ���ڸ� �Է��Ͻʽÿ�.", "�˸�",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
				case "insert_cancel": {
					inputPopup.dispose();
					break;
				}
				case SubjectVO.SELECT: {
					String keyword = searchField.getText();
//					if(keyword.equals("")) {
//						return;
//					}
					if(keyword == null) {
						keyword = "";
					}
					String option = optionList.getSelectedItem();
					
					ArrayList<SubjectVO> subjects = server.searchSubjects(option, keyword);
					setDisplay(subjects);
					break;
				}
				case SubjectVO.DELETE: {
					String code = button.getActionCommand(); // �����ڵ�� ����
					server.deleteSubject(code);
					
					ArrayList<SubjectVO> subjects = server.searchAllSubjects();
					setDisplay(subjects);
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
				case SubjectVO.SELECT: {
					String keyword = searchField.getText();
					if(keyword.equals("")) {
						return;
					}
					String option = optionList.getSelectedItem();
					
					ArrayList<SubjectVO> subjects = server.searchSubjects(option, keyword);
					setDisplay(subjects);
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
		
		inputButton = new JButton("�Է�");
		selectPanel.add(inputButton);
		inputButton.setName("input");
		inputButton.addMouseListener(this);
		
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
		optionList.add("����");
		optionList.add("���Ǹ�");
		optionList.add("�����ڵ�");
		optionList.add("������");
		optionList.add("���ǽ�");
		optionList.add("����");
		
		searchField = new JTextField();
		searchPanel.add(BorderLayout.CENTER, searchField);
		searchField.setName(SubjectVO.SELECT);
		searchField.addKeyListener(this);
		
		searchButton = new JButton("�˻�");
		searchPanel.add(BorderLayout.EAST, searchButton);
		searchButton.setName(SubjectVO.SELECT);
		searchButton.addMouseListener(this);
	}
	
	private class SubjectPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		JTextArea subjectInfoArea;
		JPanel buttonPanel;
		JButton detailButton, deleteButton;
		
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
			
			detailButton = new JButton("�󼼺���");
			deleteButton = new JButton("����");
			buttonPanel.add(detailButton);
			buttonPanel.add(deleteButton);
			detailButton.setName("detail");
			deleteButton.setName(SubjectVO.DELETE);
			deleteButton.setActionCommand(subject.getCode()); // ���� Ű����� ���� �ڵ�
			detailButton.addMouseListener(outerMouseListener);
			deleteButton.addMouseListener(outerMouseListener);
		}
		
	}
	
	private class InputPopupFrame extends JFrame {
		private static final long serialVersionUID = 1L;
		
		JPanel insertInfoPanel, insertButtonPanel;
		JLabel[] insertLabel = new JLabel[6];
		JTextField[] insertField = new JTextField[insertLabel.length];
		JButton insertButton, insertCancelButton;
		
		MouseListener outerMouseListener;
		
		public InputPopupFrame(String title, MouseListener outerMouseListener) {
			super(title);
			this.outerMouseListener = outerMouseListener;
			this.setFrame();
		}
		
		public void setFrame() {
			this.setSize(300, 400);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			this.setLayout(new BorderLayout());
			
			insertInfoPanel = new JPanel(new GridLayout(insertLabel.length, 2));
			this.add(BorderLayout.CENTER, insertInfoPanel);
			
			
			for(int i = 0; i < insertLabel.length; i++) {
				insertLabel[i] = new JLabel();
				insertInfoPanel.add(insertLabel[i]);
				insertField[i] = new JTextField();
				insertInfoPanel.add(insertField[i]);
			}
			insertLabel[0].setText("���Ǹ�");
			insertLabel[1].setText("�����ڵ�");
			insertLabel[2].setText("��米��");
			insertLabel[3].setText("���ǽ�");
			insertLabel[4].setText("��������");
			insertLabel[5].setText("�ο�����");
			
			insertButtonPanel = new JPanel(new GridLayout(1, 2));
			this.add(BorderLayout.SOUTH, insertButtonPanel);
			insertButton = new JButton("�Է�");
			insertCancelButton = new JButton("���");
			insertButtonPanel.add(insertButton);
			insertButtonPanel.add(insertCancelButton);
			insertButton.addMouseListener(outerMouseListener);
			insertCancelButton.addMouseListener(outerMouseListener);
			insertButton.setName(SubjectVO.INSERT);
			insertCancelButton.setName("insert_cancel");
		}
		
	}

//	public static void main(String[] args) {
//		new SubjectView("������û �ý��� ������ ���", null);
//	}
	
}
