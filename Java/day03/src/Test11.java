
public class Test11 {

	public static void main(String[] args) {
		int flag = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 11; j++) {
				if(j < 5 - flag || j > 5 + flag) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
			
			if(i < 4) {
				flag++;
			} else {
				flag--;
			}
		}
	}

}
