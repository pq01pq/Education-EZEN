import test01.*;

class A03 extends Ex01 {
	void disp2() {
		System.out.println("a = " + a);
	}
}

public class Test03 {
	public static void main(String[] args) {
		Ex01 ex = new Ex01();
		//ex.a = 100; // 상속관계가 아니고, 다른 패키지에 있음
		ex.disp();
	}
}
