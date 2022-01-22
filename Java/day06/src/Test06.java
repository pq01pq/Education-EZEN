import java.util.Scanner;

public class Test06 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("크기 : ");
		int n = scan.nextInt();
		System.out.println();
		scan.close();
		
		char alphabet = 'A';
		int k = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(j < ((n - 1) / 2) - k || j > (n / 2) + k) {
					System.out.print("   ");
				} else {
					System.out.print(alphabet + "  ");
					alphabet++;
					if(alphabet > 'Z') {
						alphabet = 'A';
					}
				}
			}
			if(i < (n - 1) / 2) {
				k++;
			} else if(i >= n / 2){
				k--;
			}
			System.out.println();
		}
	}

}
