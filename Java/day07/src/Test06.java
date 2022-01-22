
public class Test06 {

	public static void main(String[] args) {
		hello(5);
	}
	
	public static void hello(int n) {
		System.out.println("Hello, Java!!");
		if(n <= 1) {	// 재귀에서는 무조건 탈출조건 만들어줌
			return;
		}
		hello(n - 1);	// 재귀호출
	}

}
