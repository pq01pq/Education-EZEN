
public class Test01 {

	public static void main(String[] args) {
		int side = 7;
		int[][] zigzag = new int[side][side];
		
		int number = 1;
		int i = 0, j;
		while(i < side) {
			if(i % 2 == 0) {	// 0, 2, 4, ... 행
				for(j = 0; j < side; j++) {
					zigzag[i][j] = number;
					number++;
				}
			} else {	// 1, 3, 5, ... 행
				for(j = side - 1; j >= 0; j--) {
					zigzag[i][j] = number;
					number++;
				}
			}
			i++;
		}
		
		for(i = 0; i < side; i++) {
			for(j = 0; j < side; j++) {
				System.out.printf("%4d ", zigzag[i][j]);
			}
			System.out.println();	System.out.println();
		}
	}

}
