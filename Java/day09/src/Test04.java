
class Outer04 {
	private int a;
	private static int b;
	static {
		b = 20;
	}
	public Outer04() {
		a = 10;
	}
	public void disp() {
		// 지역 중첩 클래스
		class Inner04{
			private int c;
			public Inner04() {
				c = 30;
			}
			public void dispIn() {
				System.out.println("a = " + a);
				System.out.println("b = " + b);
				System.out.println("c = " + c);
			}
		}
		// 생성도 지역적으로밖에 할 수 없음
		Inner04 inner = new Inner04();
		inner.dispIn();
	}
	
	public void disp2() {
		// 지역 중첩 클래스
		class Inner04{
			private int c;
			public Inner04() {
				c = 30;
			}
			public void dispIn() {
				System.out.println("a = " + a);
				System.out.println("b = " + b);
				System.out.println("c = " + c);
			}
		}
		// 생성도 지역적으로밖에 할 수 없음
		Inner04 inner = new Inner04();
		inner.dispIn();
	}
}

public class Test04 {

	public static void main(String[] args) {
		Outer04 outer = new Outer04();
		outer.disp();
		outer.disp2();
	}

}
