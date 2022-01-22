
public class Test07 {

	public static void main(String[] args) {
		int oddSum = 0, evenSum = 0;
		for(int i = 1; i <= 100; i++) {
			if(i % 2 == 0) {
				evenSum += i;
			} else {
				oddSum += i;
			}
		}
		
		System.out.println("홀수 합 : " + oddSum);
		System.out.println("짝수 합 : " + evenSum);
	}

}
