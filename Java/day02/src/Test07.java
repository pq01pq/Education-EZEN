import java.util.Scanner;

public class Test07 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int a = scan.nextInt();
		if(a % 3 == 0) {
			System.out.println("3의 배수");
		} else {
			System.out.println("3의 배수 아님");
		}
		
		scan.close();
	}
}
