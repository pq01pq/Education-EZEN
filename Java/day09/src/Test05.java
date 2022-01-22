
class Outer05 {
	public void aaa() {
		System.out.println("aaa method");
	}
	public void bbb() {
		System.out.println("욜라뽕따이");
	}
	public void ccc() {
		System.out.println("ccc method");
	}
}

public class Test05 {

	public static void main(String[] args) {
		Outer05 outer = new Outer05() {
			// 오버라이드
			public void bbb() {
				System.out.println("bbb method");
			}
		};
		outer.aaa();
		outer.bbb();
		outer.ccc();
	}

}
