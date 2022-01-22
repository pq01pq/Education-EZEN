import java.util.Scanner;

public class Test06 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int a = scan.nextInt();
		if(a % 2 == 0) {
			System.out.println("짝수");
		} else {
			System.out.println("홀수");
		}
		
		scan.close();
	}

}
