import java.io.IOException;
import java.util.Scanner;

public class Test13 {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 수 : ");
		int a = scan.nextInt();
		System.out.print("산술연산자 : ");
		char operator = (char)System.in.read();	System.in.read();System.in.read();
		System.out.print("두번째 수 : ");
		int b = scan.nextInt();
		
		if(operator == '+') {
			System.out.printf("%d %c %d = %d", a, operator, b, a + b);
		} else if(operator == '-') {
			System.out.printf("%d %c %d = %d", a, operator, b, a - b);
		} else if(operator == '*') {
			System.out.printf("%d %c %d = %d", a, operator, b, a * b);
		} else if(operator == '/') {
			System.out.printf("%d %c %d = %f", a, operator, b, (double)a / b);
		} else if(operator == '%') {
			System.out.printf("%d %c %d = %d", a, operator, b, a % b);
		} else {
			System.out.println("산술연산자만 기입");
		}
		
		scan.close();
	}

}
