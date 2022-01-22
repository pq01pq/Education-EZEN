import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame10 extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Dimension screen;
	private MenuBar menuBar;
	private Menu fileMenu, helpMenu;
	private MenuItem fOpen, fWrite, exit, versionView;
	private FileDialog fOpenDialog, fWriteDialog;
	private Dialog versionDialog;
	private Label version;
	private Panel okPanel;
	private Button okButton;
	
	// �⺻ ������
	public MyFrame10(String title) {
		super(title);
		this.setSize(400, 300);
		this.screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
		
		menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		setFileMenu();
		setHelpMenu();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fOpen) {
			fOpenDialog.setVisible(true);
		} else if(e.getSource() == fWrite) {
			fWriteDialog.setVisible(true);
		} else if(e.getSource() == versionView) {
			versionDialog.setVisible(true);
		} else if(e.getSource() == okButton) {
			versionDialog.setVisible(false);
		} else if(e.getSource() == exit) {
			System.exit(0);
		}
	}

	public void setFileMenu() {
		fileMenu = new Menu("����");
		menuBar.add(fileMenu);
		
		fOpen = new MenuItem("����");
		fOpen.addActionListener(this);
		
		fWrite = new MenuItem("����");
		fWrite.addActionListener(this);
		
		exit = new MenuItem("������");
		exit.addActionListener(this);
		
		fileMenu.add(fOpen);
		fileMenu.add(fWrite);
		fileMenu.add(exit);
		
		fOpenDialog = new FileDialog(this, "����", FileDialog.LOAD);
		fOpenDialog.setVisible(false);
		
		fWriteDialog = new FileDialog(this, "����", FileDialog.SAVE);
		fWriteDialog.setVisible(false);
	}
	
	public void setHelpMenu() {
		helpMenu = new Menu("����");
		menuBar.add(helpMenu);
		
		versionView = new MenuItem("���� ����");
		versionView.addActionListener(this);
		helpMenu.add(versionView);
		
		versionDialog = new Dialog(this, "���� ����", true);
		versionDialog.setVisible(false);
		versionDialog.setLayout(new BorderLayout());
		versionDialog.setTitle(versionView.getLabel());
		
		version = new Label("���� 1.0", Label.CENTER);
		
		okPanel = new Panel(new FlowLayout());
		okButton = new Button("Ȯ��");
		okButton.addActionListener(this);
		okPanel.add(okButton);
		
		versionDialog.add("Center", version);
		versionDialog.add("South", okPanel);
		
		versionDialog.setSize(200, 150);
		versionDialog.setLocation(
				(int)(screen.getWidth() / 2) - (versionDialog.getWidth() / 2),
				(int)(screen.getHeight() / 2) - (versionDialog.getHeight() / 2));
	}
}

public class Test10 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame10 mf = new MyFrame10("awt �ǽ�");
	}

}
