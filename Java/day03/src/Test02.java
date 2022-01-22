import java.io.IOException;
import java.util.Scanner;

public class Test02 {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 수 : ");
		int a = scan.nextInt();
		System.out.print("산술연산자 : ");
		char operator = (char)System.in.read();	System.in.read();System.in.read();
		System.out.print("두번째 수 : ");
		int b = scan.nextInt();
		
		switch(operator) {	// byte, char, short, int, String
		case '+':
			System.out.printf("%d %c %d = %d\n", a, operator, b, a + b);	break;
		case '-':
			System.out.printf("%d %c %d = %d\n", a, operator, b, a - b);	break;
		case '*':
			System.out.printf("%d %c %d = %d\n", a, operator, b, a * b);	break;
		case '/':
			System.out.printf("%d %c %d = %d\n", a, operator, b, (double)a / b);	break;
		case '%':
			System.out.printf("%d %c %d = %d\n", a, operator, b, a % b);	break;
		default :
			System.out.println("산술연산자만 입력");
		}
		
		scan.close();
	}

}
