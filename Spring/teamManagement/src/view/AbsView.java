package view;

import java.util.Scanner;

import javax.swing.JOptionPane;

public abstract class AbsView {
	// 키보드로부터 입력받기 위한 메서드
	protected String getKeyInputString(String field) {
		String input = JOptionPane.showInputDialog(field + "입력");
		return input;
	}
	
	// 입력을 받기위해 대기하도록 하는 메서드
	@SuppressWarnings("resource")
	protected String getEnter() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	// 메세지 박스 출력
	protected void msg(String str) {
		JOptionPane.showMessageDialog(null, str);
	}
	
	// 메뉴 구성은 그때그때 달라지므로 자식클래스가 알아서 설정
	public abstract void show();
}
