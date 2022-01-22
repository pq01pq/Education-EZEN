package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainView extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	protected Container container;
	protected JPanel buttonPanel ,viewPanel;
	private JButton listButton, addButton, manageButton, exitButton;
	
	private SelectTeamView selectTeamView;
	private InsertMemberView insertMemberView;
	private SelectMemberView selectMemberView;
	
	public void setSelectTeamView(SelectTeamView selectTeamView) {
		this.selectTeamView = selectTeamView;
	}
	public void setInsertMemberView(InsertMemberView insertMemberView) {
		this.insertMemberView = insertMemberView;
	}
	public void setSelectMemberView(SelectMemberView selectMemberView) {
		this.selectMemberView = selectMemberView;
	}
	
	public void display(MainView mainView) {
		display();
	}
	
	public void display() {
		setFrame();
		showMenu();
	}
	
	protected void showMenu() {
		buttonPanel = new JPanel(new GridLayout(1, 4));
		container.add(buttonPanel, BorderLayout.NORTH);
		
		listButton = new JButton("팀 리스트");
		addButton = new JButton("팀원 추가");
		manageButton = new JButton("팀원 관리");
		exitButton = new JButton("종료");
		listButton.setActionCommand("list");
		addButton.setActionCommand("add");
		manageButton.setActionCommand("manage");
		exitButton.setActionCommand("exit");
		buttonPanel.add(listButton);
		buttonPanel.add(addButton);
		buttonPanel.add(manageButton);
		buttonPanel.add(exitButton);
		listButton.addMouseListener(this);
		addButton.addMouseListener(this);
		manageButton.addMouseListener(this);
		exitButton.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton)e.getSource();
			switch(button.getActionCommand()) {
			case "list":{
				selectTeamView.display(this);
				break;
			}
			case "add":{
				insertMemberView.display(this);
				break;
			}
			case "manage":{
				selectMemberView.display(this);
				break;
			}
			case "exit":{
				System.exit(0);
				break;
			}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	// 키보드로부터 입력받기 위한 메서드
	protected String getKeyInputString(String field) {
		String input = JOptionPane.showInputDialog(field + "입력");
		return input;
	}
	
	// 메세지 박스 출력
	protected void msg(String str) {
		JOptionPane.showMessageDialog(this, str);
	}
	
	public void setFrame() {
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)((screen.getWidth() / 2.0) - (this.getWidth() / 2.0));
		int yPos = (int)((screen.getHeight() / 2.0) - (this.getHeight() / 2.0));
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container = this.getContentPane();
		container.removeAll();
		container.setLayout(new BorderLayout());
		
		viewPanel = new JPanel(new BorderLayout());
		container.add(viewPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		GenericApplicationContext ac = new GenericXmlApplicationContext("appContext.xml");
		MainView mv = (MainView)ac.getBean("mainView");
		mv.display(null);
	}

}
