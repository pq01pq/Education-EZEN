
public class Test08 {
	
	public static int fibonacci(int f) {
		if(f == 1 || f == 0) {
			return 1;
		}
		return fibonacci(f - 1) + fibonacci(f - 2);
	}

	public static void main(String[] args) {
		for(int i = 0; i < 20; i++) {
			System.out.println(i + " : " + fibonacci(i));
		}
	}

}
