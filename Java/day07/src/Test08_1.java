
public class Test08_1 {
	
	public static long tailFibonacci(long f1, long f2, long n) {
		if(n == 1) {
			return f2;
		}
		if(n < 1) {
			return f1;
		}
		return tailFibonacci(f2, f1 + f2, n - 1);
	}

	public static void main(String[] args) {
		for(long i = 0; i < 91; i++) {
			System.out.println(i + " : " + tailFibonacci(1, 1, i));
		}
	}

}
