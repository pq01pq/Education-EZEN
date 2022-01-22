import java.util.Scanner;

public class Test07 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("크기 : ");
		int n = scan.nextInt();
		System.out.println();
		scan.close();
		
		char[][] tableAlphabet = new char[n][n];
		
		char alphabet = 'A';
		for(int k = 0; k <= (n - 1) / 2; k++) {
			// 좌상단 
			int i = k, j = (n - 1) / 2;
			while(j >= k) {
				tableAlphabet[i][j] = alphabet;
				alphabet++;
				if(alphabet > 'Z') {
					alphabet = 'A';
				}
				i++;
				j--;
			}
			j++;
			if(n % 2 == 1) {
				j++;
			}
			
			// 좌하단
			while(i < n - k) {
				tableAlphabet[i][j] = alphabet;
				alphabet++;
				if(alphabet > 'Z') {
					alphabet = 'A';
				}
				i++;
				j++;
			}
			i--;
			if(n % 2 == 1) {
				i--;
			}
			
			// 우하단
			while(j < n - k) {
				tableAlphabet[i][j] = alphabet;
				alphabet++;
				if(alphabet > 'Z') {
					alphabet = 'A';
				}
				i--;
				j++;
			}
			j--;
			if(n % 2 == 1) {
				j--;
			}
			
			// 우상단
			while(j >= (n + 1) / 2) {
				tableAlphabet[i][j] = alphabet;
				alphabet++;
				if(alphabet > 'Z') {
					alphabet = 'A';
				}
				i--;
				j--;
			}
			
		}
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(tableAlphabet[i][j] == 0) {
					System.out.print("   ");
				} else {
					System.out.print(tableAlphabet[i][j] + "  ");
				}
			}
			System.out.println();
		}
	}

}
