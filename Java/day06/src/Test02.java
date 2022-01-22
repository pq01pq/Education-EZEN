import java.io.IOException;

public class Test02 {

	public static void main(String[] args) throws IOException {
		int[] baseball = new int[3];
		for(int i = 0; i < baseball.length; i++) {
			baseball[i] = (int)(Math.random() * 9) + 1;
			for(int j = 0; j < i; j++) {
				if(baseball[i] == baseball[j]) {
					i--;
					break;
				}
			}
		}
		//System.out.println(Arrays.toString(baseball));
		
		int count = 0;
		while(true) {
			int[] myBaseball = new int[3];
			int strike = 0, ball = 0;
			System.out.print("수 입력 : ");
			for(int i = 0; i < myBaseball.length; i++) {
				myBaseball[i] = System.in.read() - '0';
			}
			System.in.skip(5);	// 뒤의 내용은 버림
			//System.out.println(Arrays.toString(myBaseball));
			
			for(int i = 0; i < baseball.length; i++) {
				for(int j = 0; j < myBaseball.length; j++) {
					if(baseball[i] == myBaseball[j]) {
						if(i == j) {
							strike++;
						}
						else {
							ball++;
						}
					}
				}
			}
			count++;
			
			if(strike == 3) {
				System.out.println(count + "번만에 맞춤");
				break;
			} else if(strike + ball > 0) {
				System.out.println(strike + " strike  " + ball + " ball");
			} else {
				System.out.println("out");
			}
		}
	}

}
