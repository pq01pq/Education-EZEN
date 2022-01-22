// 객체 앞에 final 쓰면 다른 객체에 상속해줄 수 없음
/*final*/ class A06 {
	
	// 메서드 오버라이딩. 부모클래스의 메서드 선언명과 동일하게 써야함
	// 메서드 앞에 final 쓰면 더이상 다른 객체에서 오버라이드 받을 수 없음
	public /*final*/ String toString() {
		return "A06클래스 상속예제";
	}
}

class B06 extends A06 {
	// 오버라이드
	public String toString() {
		return "B06클래스 상속 예제";
	}
}

public class Test06 {

	public static void main(String[] args) {
		A06 ap = new A06();
		System.out.println("ap.getClass = " + ap.getClass());
		System.out.println("ap.toString = " + ap.toString());
		System.out.println("ap = " + ap);
		System.out.println();
		
		B06 bp = new B06();
		System.out.println("bp.toString = " + bp.toString());
	}

}
