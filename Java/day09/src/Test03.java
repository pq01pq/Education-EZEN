
class Outer03 {
	private int a;
	private static int b;
	static {
		b = 20;
	}
	public Outer03() {
		a = 10;
	}
	public void disp() {
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}
	//  중첩 클래스
	static class Inner03 {
		private int c;
		public Inner03() {
			c = 30;
		}
		public void dispIn() {
//			System.out.println("a = " + a);	// Outer03의 static 멤버만 사용 가능
			System.out.println("b = " + b);
			System.out.println("c = " + c);
		}
		
		class Inner02_in {}
	}
}

public class Test03 {
	public static void main(String[] args) {
		//Outer03 outer = new Outer03();
		Outer03.Inner03 inner = new Outer03.Inner03();
		// 외부클래스와 독립적으로 사용함
		
		//outer.disp();
		inner.dispIn();
	}
}
