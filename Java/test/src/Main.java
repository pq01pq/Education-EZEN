import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
//		System.out.print("입력 크기:");
		int n = Integer.parseInt(scan.nextLine());
		int[] testCase = new int[n];
		int a, b, lastDigit;
		for (int i = 0; i < n; i++) {
//			System.out.print("인자:");
			String[] nums = scan.nextLine().split(" ");
			a = Integer.parseInt(nums[0]);
			b = Integer.parseInt(nums[1]);
			lastDigit = a;
			for (int j = 0; j < b - 1; j++) {
				lastDigit = (lastDigit * a) % 10;
			}
			testCase[i] = lastDigit;
			
		}
		scan.close();
		for(int i = 0; i < n; i++) {
			System.out.println(testCase[i]);
		}
	}
}