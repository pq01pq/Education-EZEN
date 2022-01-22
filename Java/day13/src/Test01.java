import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class Test01 {

	public static void main(String[] args) {
		Frame f1 = new Frame();
		Frame f2 = new Frame("졸려");	// 문자열 인자 타이틀
		
		f1.setTitle("배고파");
		
		// 픽셀값으로 창 크기 초깃값 지정
		f1.setSize(300,200);
		f2.setSize(400,300);
		
		// 좌표로 창의 초기 위치 지정
		f1.setLocation(200,200);
//		f2.setLocation(300,300);
		
		// 화면의 가로, 세로 길이를 반환
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		// 창을 가운데 놓을 때 왼쪽 위 꼭짓점 좌표를 구해줌
		int xPos = (int)((screen.getWidth() / 2) - (f2.getWidth() / 2));
		int yPos = (int)((screen.getHeight() / 2) - (f2.getHeight() / 2));
		f2.setLocation(xPos, yPos);
		
		f2.setResizable(false);	// 크기조절 가능여부 설정
		
		// 화면에 보이는지 여부 설정
		f1.setVisible(true);
		f2.setVisible(true);
		
		
		
		
	}

}
