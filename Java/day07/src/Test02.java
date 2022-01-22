import java.util.Scanner;

/**
2개의 수를 입력받아 그 합을 구하는 프로그램
 */
public class Test02 {
	
	static Scanner scan;

	public static void main(String[] args) {
		intro();
		int num1 = input();
		int num2 = input();
		int total = sum(num1, num2);
		output(num1, num2, total);
	}
	
	// 반환값과 매개변수가 다 없음
	public static void intro() {
		System.out.println("두개의 수를 입력받아 그 합을 구하는 프로그램");
	}
	
	// 반환값은 있고 매개변수는 없음
	public static int input() {
		scan = new Scanner(System.in);
		System.out.print("수 입력 : ");
		int num = scan.nextInt();
		
		return num;
	}
	
	// 반환값과 매개변수가 다 있음
	public static int sum(int a, int b) {
		return a + b;
	}
	
	// 반환값은 없고 매개변수는 있음
	public static void output(int a, int b, int c) {
		System.out.printf("%d + %d = %d\n", a, b, c);
	}

}
