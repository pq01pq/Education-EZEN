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
	
	// 기본 생성자
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
		fileMenu = new Menu("파일");
		menuBar.add(fileMenu);
		
		fOpen = new MenuItem("열기");
		fOpen.addActionListener(this);
		
		fWrite = new MenuItem("저장");
		fWrite.addActionListener(this);
		
		exit = new MenuItem("끝내기");
		exit.addActionListener(this);
		
		fileMenu.add(fOpen);
		fileMenu.add(fWrite);
		fileMenu.add(exit);
		
		fOpenDialog = new FileDialog(this, "열기", FileDialog.LOAD);
		fOpenDialog.setVisible(false);
		
		fWriteDialog = new FileDialog(this, "저장", FileDialog.SAVE);
		fWriteDialog.setVisible(false);
	}
	
	public void setHelpMenu() {
		helpMenu = new Menu("도움말");
		menuBar.add(helpMenu);
		
		versionView = new MenuItem("버전 보기");
		versionView.addActionListener(this);
		helpMenu.add(versionView);
		
		versionDialog = new Dialog(this, "버전 보기", true);
		versionDialog.setVisible(false);
		versionDialog.setLayout(new BorderLayout());
		versionDialog.setTitle(versionView.getLabel());
		
		version = new Label("버전 1.0", Label.CENTER);
		
		okPanel = new Panel(new FlowLayout());
		okButton = new Button("확인");
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
		MyFrame10 mf = new MyFrame10("awt 실습");
	}

}
