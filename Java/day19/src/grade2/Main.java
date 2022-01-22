package grade2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyFrame04 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Container con, insertContainer, deleteContainer;
	JButton insertButton, insertConfirmButton, deleteButton, deleteConfirmButton, 
			loadButton, saveButton, exitButton;
	JPanel nameDisplayPanel, korDisplayPanel, engDisplayPanel, totalDisplayPanel, rankDisplayPanel,
			controlPanel, inputPanel, inputTextPanel;
	JTabbedPane displayTab;
	JLabel	inputNameLabel, inputKorLabel, inputEngLabel, deleteNameLabel;
	JDialog inputDialog, deleteDialog;
	JTextField insertNameField, deleteNameField, korField, engField;
	JTextArea nameDisplayArea, korDisplayArea, engDisplayArea, totalDisplayArea, rankDisplayArea;
	
	Manage manage;
	
	// �⺻ ������
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
		switch(e.getActionCommand()) {
		case "insert":
			inputDialog.setVisible(true);
			break;
		case "delete":
			deleteDialog.setVisible(true);
			break;
		case "insert_confirm":
			inputDialog.setVisible(false);
			if(manage.insert(insertNameField.getText(),
					Integer.parseInt(korField.getText()), Integer.parseInt(engField.getText()))) {
				setDisplay();
			} else {
				JOptionPane.showMessageDialog(
						  this, "�����ϴ� �̸�", "�˸�", JOptionPane.INFORMATION_MESSAGE);
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
						this, "���� �̸�", "�˸�", JOptionPane.INFORMATION_MESSAGE);
			}
			deleteNameField.setText("");
			break;
		case "save":
			manage.save();
			break;
		case "load":
			manage.load();
			setDisplay();
			break;
		case "exit":
			System.exit(0);
			default :
				
		}
	}
	
	public void setDisplay() {
		nameDisplayArea.setText(manage.view(nameDisplayArea.getName()));
		korDisplayArea.setText(manage.view(korDisplayArea.getName()));
		engDisplayArea.setText(manage.view(engDisplayArea.getName()));
		totalDisplayArea.setText(manage.view(totalDisplayArea.getName()));
		rankDisplayArea.setText(manage.view(rankDisplayArea.getName()));
	}
	
	public void init() {
		manage = new ManageGrade();
	}
	
	public void setFrame() {
		this.con = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setLayout(new BorderLayout());
		
		// ��� ��Ʈ�� �г�
		controlPanel = new JPanel(new GridLayout(1, 3));
		con.add("North", controlPanel);
		
		insertButton = new JButton("�Է�");
		deleteButton = new JButton("����");
		saveButton = new JButton("����");
		loadButton = new JButton("����");
		exitButton = new JButton("����");
		insertButton.setActionCommand("insert");
		deleteButton.setActionCommand("delete");
		saveButton.setActionCommand("save");
		loadButton.setActionCommand("load");
		exitButton.setActionCommand("exit");
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		exitButton.addActionListener(this);
		controlPanel.add(insertButton);
		controlPanel.add(deleteButton);
		controlPanel.add(saveButton);
		controlPanel.add(loadButton);
		controlPanel.add(exitButton);
		
		// �߽� ȭ�� �г�
		displayTab = new JTabbedPane();
		con.add("Center", displayTab);
		nameDisplayArea = new JTextArea();
		korDisplayArea = new JTextArea();
		engDisplayArea = new JTextArea();
		totalDisplayArea = new JTextArea();
		rankDisplayArea = new JTextArea();
		nameDisplayArea.setName("name");
		korDisplayArea.setName("kor");
		engDisplayArea.setName("eng");
		totalDisplayArea.setName("total");
		rankDisplayArea.setName("rank");
		nameDisplayArea.setEditable(false);
		korDisplayArea.setEditable(false);
		engDisplayArea.setEditable(false);
		totalDisplayArea.setEditable(false);
		rankDisplayArea.setEditable(false);
		
		
		
//		nameDisplayPanel = new JPanel();
//		korDisplayPanel = new JPanel();
//		engDisplayPanel = new JPanel();
//		totalDisplayPanel = new JPanel();
//		rankDisplayPanel = new JPanel();
//		nameDisplayPanel.setName("name");
//		korDisplayPanel.setName("kor");
//		engDisplayPanel.setName("eng");
//		totalDisplayPanel.setName("total");
//		rankDisplayPanel.setName("rank");
		displayTab.add("�̸�", nameDisplayArea);
		displayTab.add("����", korDisplayArea);
		displayTab.add("����", engDisplayArea);
		displayTab.add("����", totalDisplayArea);
		displayTab.add("����", rankDisplayArea);
		
		// �Է� ���̾�α�
		inputDialog = new JDialog(this, "���� �Է�", false);
		inputDialog.setSize(200, 200);
		inputDialog.setLocation(this.getX(), this.getY());
		insertContainer = inputDialog.getContentPane();
		insertContainer.setLayout(new BorderLayout());
		
		inputPanel = new JPanel(new GridLayout(3, 1));
		insertContainer.add("West", inputPanel);
		
		inputNameLabel = new JLabel("�̸�");
		inputKorLabel = new JLabel("����");
		inputEngLabel = new JLabel("����");
		inputPanel.add(inputNameLabel);
		inputPanel.add(inputKorLabel);
		inputPanel.add(inputEngLabel);
		
		inputTextPanel = new JPanel(new GridLayout(3, 1));
		insertContainer.add("Center", inputTextPanel);
		
		insertNameField = new JTextField();
		korField = new JTextField();
		engField = new JTextField();
		inputTextPanel.add("�̸�", insertNameField);
		inputTextPanel.add("����", korField);
		inputTextPanel.add("����", engField);
		
		insertConfirmButton = new JButton("Ȯ��");
		insertConfirmButton.addActionListener(this);
		insertConfirmButton.setActionCommand("insert_confirm");
		insertContainer.add("South", insertConfirmButton);
		
		// ���� ���̾�α�
		deleteDialog = new JDialog(this, "����", false);
		deleteDialog.setSize(200, 120);
		deleteDialog.setLocation(this.getX(), this.getY());
		deleteContainer = deleteDialog.getContentPane();
		deleteContainer.setLayout(new BorderLayout());
		
		deleteNameField = new JTextField();
		deleteContainer.add("Center", deleteNameField);
		
		deleteNameLabel = new JLabel("�̸�");
		deleteContainer.add("West", deleteNameLabel);
		
		deleteConfirmButton = new JButton("Ȯ��");
		deleteConfirmButton.addActionListener(this);
		deleteConfirmButton.setActionCommand("delete_confirm");
		deleteContainer.add("South", deleteConfirmButton);
	}
	
}

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame04 mf = new MyFrame04("swing �ǽ�");
	}

}
