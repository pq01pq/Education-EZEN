
class A07 {
	int a = 10;
}

class B07 extends A07 {
	int b = 20;
//	A07 ap = new A07();
	public void disp() {
		System.out.println("getClass = " + this.getClass());
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}
}

class C07 extends B07 {
	int c = 30;
//	B07 bp = new B07();
	public void disp() {
		System.out.println("getClass = " + this.getClass());
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
	}
}

public class Test07 {

	public static void main(String[] args) {
		C07 cp = new C07();
		cp.disp();
	}

}
