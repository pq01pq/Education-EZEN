import java.awt.*;

class MyFrame05 extends Frame {
	private static final long serialVersionUID = 1L;
	
	private MenuBar mb = new MenuBar();
	private Menu file = new Menu("파일");
	private Menu edit = new Menu("편집");
	private Menu help = new Menu("도움말");
	
	private MenuItem fNew = new MenuItem("새 파일");
	private MenuItem fOpen = new MenuItem("열기");
	private MenuItem fSave = new MenuItem("저장");
	private MenuItem fExit = new MenuItem("종료");
	
	private Menu eColor = new Menu("색상");
	private Menu eSize = new Menu("크기");
	
	private CheckboxMenuItem eRed = new CheckboxMenuItem("빨강");
	private CheckboxMenuItem eGreen = new CheckboxMenuItem("초록");
	private CheckboxMenuItem eBlue = new CheckboxMenuItem("파랑");
	
	public void init() {
		this.setMenuBar(mb);
		mb.add(file);
		file.add(fNew);
		file.addSeparator();
		file.add(fOpen);
		file.add(fSave);
		file.addSeparator();
		file.add(fExit);	
		mb.add(edit);
		edit.add(eColor);	// 메뉴 속의 메뉴
		eColor.add(eRed);
		eColor.add(eGreen);
		eColor.add(eBlue);
		edit.addSeparator();
		edit.add(eSize);
		mb.add(help);
	}
	
	// 기본 생성자
	public MyFrame05(String title) {
		super(title);
		
		this.init();
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)(screen.getWidth() / 2) - (this.getWidth() / 2);
		int yPos = (int)(screen.getHeight() / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setVisible(true);
	}
}

public class Test05 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame05 mf = new MyFrame05("awt 실습");
	}

}
