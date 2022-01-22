import java.util.Scanner;

public class Test08 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("크기 : ");
		int n = scan.nextInt();
		System.out.println();
		scan.close();
		
		int[][] sum = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			sum[0][i] = (i + 1) % 2;
		}
		
		for(int i = 1; i < n; i++) {
			sum[i][0] = sum[i - 1][1];
			sum[i][n - 1] = sum[i - 1][n - 2];
			for(int j = 1; j < n - 1; j++) {
				sum[i][j] = sum[i - 1][j - 1] + sum[i - 1][j + 1];
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.printf("%4d ", sum[i][j]);
			}
			System.out.println();
			System.out.println();
		}
	}

}
