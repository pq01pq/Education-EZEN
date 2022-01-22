import java.util.Scanner;

public class Test04 {
	
	static Scanner scan;
	
	public static int input(Scanner scan) {
		System.out.print("수 입력 :");
		return scan.nextInt();
	}
	
	public static int subtract(int a, int b) {
		if(a > b) {
			return a - b;
		} else {
			return b - a;
		}
	}

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		int num1 = input(scan);
		int num2 = input(scan);
		
		scan.close();
		
		if(num1 < num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		int result = subtract(num1, num2);
		
		System.out.printf("%d - %d = %d\n", num1, num2, result);
	}

}
