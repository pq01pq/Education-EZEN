package test04;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MyFrame04 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Container con, insertContainer, deleteContainer;
	JButton insertButton, insertConfirmButton, deleteButton, deleteConfirmButton, exitButton;
	JPanel controlPanel, inputPanel, inputTextPanel;
	JTabbedPane displayTab;
	JLabel nameDisplayLabel, korDisplayLabel, engDisplayLabel, totalDisplayLabel, rankDisplayLabel,
			insertNameLabel, korLabel, engLabel, deleteNameLabel;
	JDialog insertDialog, deleteDialog;
	JTextField insertNameField, deleteNameField, korField, engField;
	
	
	Manage manage;
	
	// 기본 생성자
	public MyFrame04(String title) {
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
		if(e.getSource() == insertButton) {
			
		} else if(e.getSource() == deleteButton) {
			
		} else if(e.getSource() == exitButton) {
			System.exit(0);
		} else if(e.getSource() == insertConfirmButton) {
			
		} else if(e.getSource() == deleteConfirmButton) {
			
		}
		
		switch(e.getActionCommand()) {
		case "insert":
			insertDialog.setVisible(true);
			break;
		case "delete":
			deleteDialog.setVisible(true);
			break;
		case "insert_confirm":
			insertDialog.setVisible(false);
			if(manage.insert(insertNameField.getText(),
					Integer.parseInt(korField.getText()), Integer.parseInt(engField.getText()))) {
				setDisplay();
			} else {
				JOptionPane.showMessageDialog(
						  this, "존재하는 이름", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
			insertNameField.setText("");
			korField.setText("");
			engField.setText("");
			break;
		case "delete_confirm":
			if(manage.delete(deleteNameField.getText())) {
				setDisplay();
			} else {
				JOptionPane.showMessageDialog(
						this, "없는 이름", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
			deleteNameField.setText("");
			break;
		case "exit":
			System.exit(0);
			default :
				
		}
	}
	
	public void setDisplay() {
		nameDisplayLabel.setText(manage.view(nameDisplayLabel.getName()));
		korDisplayLabel.setText(manage.view(korDisplayLabel.getName()));
		engDisplayLabel.setText(manage.view(engDisplayLabel.getName()));
		totalDisplayLabel.setText(manage.view(totalDisplayLabel.getName()));
		rankDisplayLabel.setText(manage.view(rankDisplayLabel.getName()));
	}
	
	public void init() {
		manage = new ManageGrade();
	}
	
	public void setFrame() {
		this.con = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setLayout(new BorderLayout());
		
		controlPanel = new JPanel(new GridLayout(1, 3));
		con.add("North", controlPanel);
		
		insertButton = new JButton("입력");
		deleteButton = new JButton("삭제");
		exitButton = new JButton("종료");
		insertButton.setActionCommand("insert");
		deleteButton.setActionCommand("delete");
		exitButton.setActionCommand("exit");
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
		exitButton.addActionListener(this);
		controlPanel.add(insertButton);
		controlPanel.add(deleteButton);
		controlPanel.add(exitButton);
		
		displayTab = new JTabbedPane();
		con.add("Center", displayTab);
		
		nameDisplayLabel = new JLabel();
		korDisplayLabel = new JLabel();
		engDisplayLabel = new JLabel();
		totalDisplayLabel = new JLabel();
		rankDisplayLabel = new JLabel();
		nameDisplayLabel.setName("name");
		korDisplayLabel.setName("kor");
		engDisplayLabel.setName("eng");
		totalDisplayLabel.setName("total");
		rankDisplayLabel.setName("rank");
		displayTab.add("이름", nameDisplayLabel);
		displayTab.add("국어", korDisplayLabel);
		displayTab.add("영어", engDisplayLabel);
		displayTab.add("총점", totalDisplayLabel);
		displayTab.add("순위", rankDisplayLabel);
		
		
		insertDialog = new JDialog(this, "성적 입력", false);
		insertDialog.setSize(200, 200);
		insertDialog.setLocation(this.getX(), this.getY());
		insertContainer = insertDialog.getContentPane();
		insertContainer.setLayout(new BorderLayout());
		
		inputPanel = new JPanel(new GridLayout(3, 1));
		insertContainer.add("West", inputPanel);
		
		insertNameLabel = new JLabel("이름");
		korLabel = new JLabel("국어");
		engLabel = new JLabel("영어");
		inputPanel.add(insertNameLabel);
		inputPanel.add(korLabel);
		inputPanel.add(engLabel);
		
		inputTextPanel = new JPanel(new GridLayout(3, 1));
		insertContainer.add("Center", inputTextPanel);
		
		insertNameField = new JTextField();
		korField = new JTextField();
		engField = new JTextField();
		inputTextPanel.add("이름", insertNameField);
		inputTextPanel.add("국어", korField);
		inputTextPanel.add("영어", engField);
		
		insertConfirmButton = new JButton("확인");
		insertConfirmButton.addActionListener(this);
		insertConfirmButton.setActionCommand("insert_confirm");
		insertContainer.add("South", insertConfirmButton);
		
		deleteDialog = new JDialog(this, "삭제", false);
		deleteDialog.setSize(200, 200);
		deleteDialog.setLocation(this.getX(), this.getY());
		deleteContainer = deleteDialog.getContentPane();
		deleteContainer.setLayout(new BorderLayout());
		
		deleteNameField = new JTextField();
		deleteContainer.add("Center", deleteNameField);
		
		deleteNameLabel = new JLabel("이름");
		deleteContainer.add("West", deleteNameLabel);
		
		deleteConfirmButton = new JButton("확인");
		deleteConfirmButton.addActionListener(this);
		deleteConfirmButton.setActionCommand("delete_confirm");
		deleteContainer.add("South", deleteConfirmButton);
	}
	
}

public class Test04 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame04 mf = new MyFrame04("swing 실습");
	}

}
