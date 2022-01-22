
public class Test04 {

	public static void main(String[] args) {
		/*
		int arr[] = new int[3];
		int arr2[][] = new int[2][2];
		arr2[0][0] = 1;
		arr2[0][1] = 2;
		arr2[1][0] = 3;
		arr2[0][1] = 4;
		int[][] arr3 = new int[2][2];
		int[] arr4[] = new int[2][2];
		*/
		int[][] arr = new int[5][5];
		
		int count = 1;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				arr[i][j] = count;
				count++;
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(arr[4 - i][4 - j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(arr[j][4 - i] + "\t");
			}
			System.out.println();
		}
	}
}
