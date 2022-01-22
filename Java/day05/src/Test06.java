
public class Test06 {
	
	public static void main(String[] args) {
		int side = 5;
		int[][] helix = new int[side][side];
		int number = 1;
		int i, j;
		for(int k = 0; k <= side / 2; k++) {
			i = k;
			for(j = k; j <= side - 1 - k; j++) {
				helix[i][j] = number;
				number++;
			}
			
			j--;
			for(i = k + 1; i <= side - 1 - k; i++) {
				helix[i][j] = number;
				number++;
			}
			
			i--;
			for(j = side - 1 - k - 1; j >= k; j--) {
				helix[i][j] = number;
				number++;
			}
			
			j++;
			for(i = side - 1 - k - 1; i > k; i--) {
				helix[i][j] = number;
				number++;
			}
		}
		
		for(i = 0; i < side; i++) {
			for(j = 0; j < side; j++) {
				System.out.printf("%4d ", helix[i][j]);
			}
			System.out.println();	System.out.println();
		}
	}

}
