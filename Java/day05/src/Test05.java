
public class Test05 {
	public static void main(String[] args) {
		int side = 5;
		int[][] oddSudoku = new int[side][side];
		
		int number = 1;
		int i = 0, j = side / 2;
		boolean back = false;
		while(number <= side * side) {
			// 자리에 값이 없으면 저장, 있으면 되돌아감
			if (oddSudoku[i][j] == 0) {
				oddSudoku[i][j] = number;
				number++;
			} else {
				i++;
				j--;
				back = true;
			}
			
			// 이미 갔다왔다면 아래로, 아니면 오른쪽 위로
			if(back) {
				i++;
				back = false;
			} else {
				i--;
				j++;
			}
			
			// 배열에 대입하기 전 i, j값 보정
			i = (i + side) % side;
			j = (j + side) % side;
		}
		
		for(i = 0; i < side; i++) {
			for(j = 0; j < side; j++) {
				System.out.printf("%4d ", oddSudoku[i][j]);
			}
			System.out.println();	System.out.println();
		}
		
		
	}
}
