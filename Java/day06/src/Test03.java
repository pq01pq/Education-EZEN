import java.util.Scanner;

public class Test03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("배열 크기 : ");
		int n = scan.nextInt();
		
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		
		System.out.println("배열 1");
		for(int i = 0; i < n; i++) {
			System.out.printf("수 " + (i + 1) + " : ");
			arr1[i] = scan.nextInt();
		}
		System.out.println("배열 2");
		for(int i = 0; i < n; i++) {
			System.out.printf("수 " + (i + 1) + " : ");
			arr2[i] = scan.nextInt();
		}
		System.out.println();
		scan.close();
		
		
		for(int i = 0; i < n; i++) {
			int number1 = arr1[i], number2 = arr2[i];
			int[] hint1 = new int[n];
			int[] hint2 = new int[n];
			for(int j = 0; j < n; j++) {
				if(number1 % 2 == 1) {
					hint1[n - j - 1] = 1;
				} else {
					hint1[n - j - 1] = 0;
				}
				number1 /= 2;
				
				if(number2 % 2 == 1) {
					hint2[n - j - 1] = 1;
				} else {
					hint2[n - j - 1] = 0;
				}
				number2 /= 2;
			}
			
			for(int j = 0; j < n; j++) {
				if((hint1[j] | hint2[j]) == 1) {
					System.out.print("# ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}

}
