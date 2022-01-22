
public class Test07 {
	
	public static long factorial(long n) {
		if(n == 0) {
			return 1;
		} else if(n < 0) {
			return 0;
		}
		return n * factorial(n - 1);
	}

	public static void main(String[] args) {
		for(long i = 0; i <= 30; i++) {
			System.out.println(i + " : " + factorial(i));
		}
	}

}
