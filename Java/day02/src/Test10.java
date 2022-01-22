import java.util.Scanner;

public class Test10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 수 : ");
		int a = scan.nextInt();
		System.out.print("두번째 수 : ");
		int b = scan.nextInt();
		System.out.print("세번째 수 : ");
		int c = scan.nextInt();
		
		int temp = 0;
		// 최댓값 결정
		if(a < b) {
			temp = b;
			b = a;
			a = temp;
		}
		if(a < c) {
			temp = c;
			c = a;
			a = temp;
		}
		// 나머지 크기 결정
		if(b < c) {
			temp = c;
			c = b;
			b = temp;
		}
		
		System.out.printf("%d %d %d\n", a, b, c);
		
		scan.close();
	}

}
