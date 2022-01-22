
class Outer02 {
	private int a;
	private static int b;
	static {
		b = 20;
	}
	public Outer02() {
		if(Test02.DEBUG) {
			System.out.println("외부객체 생성");
		}
		a = 10;
	}
	public void disp() {
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}
	// 일반 중첩 클래스
	class Inner02 {
		private int c;
		public Inner02() {
			if(Test02.DEBUG) {
				System.out.println("내부객체 생성");
			}
			c = 30;
		}
		public void dispIn() {
//			System.out.println("a = " + a);
//			System.out.println("b = " + b);
			disp();
			System.out.println("c = " + c);
		}
		
		class Inner02_in {}
	}
}

public class Test02 {
	public static final boolean DEBUG = true;
	public static void main(String[] args) {
		Outer02 outer = new Outer02();
//		Outer02.Inner02 inner = new Outer02.Inner02();
		// 외부객체를 만들지 않고 내부객체를 접근할 수 없음
		Outer02.Inner02 inner = outer.new Inner02();
		outer.disp();
		inner.dispIn();
	}
}
