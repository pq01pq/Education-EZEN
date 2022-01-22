import java.util.Scanner;

public class Test11 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수 입력 : ");
		int a = scan.nextInt();
		if(a % 5 == 0) {
			if(a % 3 == 0) {
				System.out.println("3과 5의 배수");
			} else {
				System.out.println("5의 배수");
			}
		} else if(a % 3 == 0) {
			System.out.println("3의 배수");
		} else {
			System.out.println("3의 배수도 아니고 5의 배수도 아님");
		}
		
		scan.close();
	}

}
