

class A03{}
interface B03 {}
// 클래스는 클래스를 상속받을 수 있음
class C03 extends A03 {}
// 클래스는 인터페이스를 상속받을 수 있음
class D03 implements B03 {}
// 클래스는 클래스를 상속받고 인터페이스를 구현할 수 있음
class E03 extends A03 implements B03 {}
// 인터페이스는 인터페이스를 상속받을 수 있음
interface F03 extends B03 {}
// 인터페이스는 인터페이스를 다중상속받을 수 있음
interface G03 extends B03, F03 {}	// 다중상속시 ',' 연산자로 나열
// 인터페이스는 클래스를 상속받지 못함
// 클래스는 인터페이스를 다중상속 받을 수 있음
class H03 implements B03, F03, G03 {}
// 클래스는 클래스를 상속받고 인터페이스를 다중상속받을 수 있음
class I03 extends A03 implements B03, F03 {}

public class Test03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
