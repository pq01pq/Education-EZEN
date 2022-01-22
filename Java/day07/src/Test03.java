
public class Test03 {

	public static void main(String[] args) {
		star();
		System.out.println("aaaaaa");
		star(); star(2, 5);
		System.out.println("bbbbbb");
		star(4); star(); star(7); star(); star(5);
		System.out.println("cccccc");
		star();
	}
	
	public static void star() {
		System.out.println("******");
	}
	
	public static void star(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print("*");
		}
		System.out.println();
	}
	
	public static void star(int line, int n) {
		for(int i = 0; i < line; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
