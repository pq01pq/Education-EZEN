import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class Test01 {

	public static void main(String[] args) {
		Frame f1 = new Frame();
		Frame f2 = new Frame("����");	// ���ڿ� ���� Ÿ��Ʋ
		
		f1.setTitle("�����");
		
		// �ȼ������� â ũ�� �ʱ갪 ����
		f1.setSize(300,200);
		f2.setSize(400,300);
		
		// ��ǥ�� â�� �ʱ� ��ġ ����
		f1.setLocation(200,200);
//		f2.setLocation(300,300);
		
		// ȭ���� ����, ���� ���̸� ��ȯ
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		// â�� ��� ���� �� ���� �� ������ ��ǥ�� ������
		int xPos = (int)((screen.getWidth() / 2) - (f2.getWidth() / 2));
		int yPos = (int)((screen.getHeight() / 2) - (f2.getHeight() / 2));
		f2.setLocation(xPos, yPos);
		
		f2.setResizable(false);	// ũ������ ���ɿ��� ����
		
		// ȭ�鿡 ���̴��� ���� ����
		f1.setVisible(true);
		f2.setVisible(true);
		
		
		
		
	}

}
