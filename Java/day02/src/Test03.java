import java.util.Scanner;

public class Test03 {

	public static void main(String[] args) {
		System.out.print("이름을 입력 : ");
		Scanner scan = new Scanner(System.in);
//		String name = scan.next();	// 문자열처리, 공백, 탭, 엔터가 나오면 끝남
		String name = scan.nextLine();	// 엔터가 나오면 끝남
//		System.out.println(name + "님 반갑습니다.");
		System.out.print(name + "님의 국어점수 : ");
		int kor = scan.nextInt();
		System.out.println(name + "님의 국어점수는" + kor  + "점입니다.");
		
		scan.close();
	}

}
