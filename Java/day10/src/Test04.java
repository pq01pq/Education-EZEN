

interface C04 {}

class A04 implements C04 {}

class B04 implements C04 {}

public class Test04 {

	public static void main(String[] args) {
		C04[] ap = new C04[2];
		// 다른 클래스를 다형성을 이용하여 하나로 묶을 수 있음
		ap[0] = new A04();
		ap[1] = new B04();
	}

}
