import java.util.Scanner;

public class Test05 {
	
	static Scanner scan;
	
	public static int input(Scanner scan) {
		System.out.print("수 입력 : ");
		return scan.nextInt();
	}
	
	public static int absolute(int a) {
		if(a < 0) {
			return -a;
		} else {
			return a;
		}
	}
	
	public static int subtract(int a, int b) {
		return a;
	}

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		int num1 = input(scan);
		int num2 = input(scan);
		
		int abs1 = absolute(num1);
		int abs2 = absolute(num2);
		if(abs1 < abs2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
			
			temp = abs1;
			abs1 = abs2;
			abs2 = temp;
		}
		
		System.out.printf("%d - %d = %d\n", num1, num2, subtract(num1, num2));
	}

}
