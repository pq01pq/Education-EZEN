import java.util.Scanner;

public class Test14 {

	public static void main(String[] args) {
		int i, j;
		
		int[] lotto = new int[6];
		int count = 0;
		while(count < lotto.length) {
			lotto[count] = (int)(45 * Math.random()) + 1;
			for(i = 0; i < count; i++) {
				if(lotto[i] == lotto[count]) {
					count--;
					break;
				}
			}
			count++;
		}
		
		int[] myLotto = new int[6];
		Scanner scan = new Scanner(System.in);
		count = 0;
		while(count < myLotto.length) {
			System.out.print(count + 1 + "번째 수 입력 : ");
			myLotto[count] = scan.nextInt();
			if(myLotto[count] < 0 || myLotto[count] > 45) {
				continue;
			}
			for(i = 0; i < count; i++) {
				if(myLotto[i] == myLotto[count]) {
					System.out.println("중복된 수");
					count--;
					break;
				}
			}
			count++;
		}
		
		scan.close();
		
		count = 0;
		for(i = 0; i < lotto.length; i++) {
			for(j = 0; j < myLotto.length; j++) {
				if(lotto[i] == myLotto[j]) {
					count++;
				}
			}
		}
		
		System.out.println("맞은 갯수 : " + count);
	}

}
