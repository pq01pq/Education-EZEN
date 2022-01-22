import java.util.Scanner;

public class Test08 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("수 입력 : ");
		int a = scan.nextInt();
		System.out.print("약수 : ");
		for(int i = 1; i <= a / 2; i++) {
			if(a % i == 0) {
				System.out.print(i + " ");
			}
		}
		System.out.println(a);
		
		scan.close();
	}

}
