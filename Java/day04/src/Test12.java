import java.util.Scanner;

public class Test12 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String[] name = new String[5];
		String[] tel =  new String[5];
		
		for(int i = 0; i < name.length; i++) {
			System.out.print(i + 1 + "번째 이름 : ");
			name[i] = scan.next();
			System.out.print("전화번호 : ");
			tel[i] = scan.next();
		}
		
		for(int i = 0; i < name.length; i++) {
			System.out.println(name[i] + "님의 전화번호는" + tel[i] + "번 입니다.");
		}

		scan.close();
	}

}
