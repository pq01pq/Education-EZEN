import java.util.Scanner;

public class Test09 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 수 : ");
		int a = scan.nextInt();
		System.out.print("두번째 수 : ");
		int b = scan.nextInt();
		
		if(a > b) {
			System.out.println(a);
		} else if(a < b) {
			System.out.println(b);
		} else {
			System.out.println("두 수는 같음");
		}
		
		scan.close();
	}

}
