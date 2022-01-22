import java.util.Scanner;

/*
\n : 줄개행
\t : tab
 */

public class Test09 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("단수 입력 : ");
		int base = scan.nextInt();
		for(int i = 1; i <= 9; i++) {
			System.out.printf("%d x %d = %d\n", base, i, base * i);
		}
		
		scan.close();
	}

}
