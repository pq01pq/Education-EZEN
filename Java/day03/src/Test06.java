import java.util.Scanner;

public class Test06 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수 입력 : ");
		int a = scan.nextInt();
		int sum = 0;
		for(int i = 1; i <= a; i++) {
			sum += i;
		}
		
		System.out.println("합 : " + sum);
		scan.close();
	}

}
