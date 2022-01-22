import java.util.Scanner;

public class Test09 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("크기 : ");
		int n = scan.nextInt() + 1;
		System.out.println();
		scan.close();
		
		int[][] pascalTriangle = new int[n][n];
		pascalTriangle[0][0] = 1;
		for(int i = 1; i < n; i++) {
			pascalTriangle[i][0] = 1;
			for(int j = 1; j < n; j++) {
				pascalTriangle[i][j] = pascalTriangle[i - 1][j - 1] + pascalTriangle[i - 1][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.printf("[%2d제곱]", i);
			for(int j = 0; j < n; j++) {
				if(pascalTriangle[i][j] != 0) {
					System.out.printf("%10d ", pascalTriangle[i][j]);
				}
			}
			System.out.println();
			System.out.println();
		}
	}

}
