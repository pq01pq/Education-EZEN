import java.util.Scanner;

public class Test05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("크기 : ");
		int n = scan.nextInt();
		System.out.println();
		scan.close();
		
		char[][] tableAlphabet = new char[n][n];
		
		char alphabet = 'A';
		for(int j = n - 1; j >= 0; j--) {
			for(int i = n - 1; i >= 0; i--) {
				tableAlphabet[i][j] = alphabet;
				alphabet++;
				if(alphabet > 'Z') {
					alphabet = 'A';
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(tableAlphabet[i][j] + "  ");
			}
			System.out.println();
		}
	}

}
