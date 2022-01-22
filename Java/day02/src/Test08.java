import java.util.Scanner;

public class Test08 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수를 입력 : ");
		int a = scan.nextInt();
		
		if(a > 10) {
			System.out.println("10보다 큰 수");
		} else if(a < 10) {
			System.out.println("10보다 작은 수");
		} else {
			System.out.println("10임");
		}
		
		scan.close();
	}

}
