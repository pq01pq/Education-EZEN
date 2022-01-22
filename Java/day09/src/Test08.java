
class A08 {
	int x;
	int y;
	public A08() {
		System.out.println("나는야 A");
		x = 10;
		y = 20;
	}
//	public A08(int x) {
//		System.out.println("나는야 A");
//		this.x = x;
//		y = 200;
//	}
}

class B08 extends A08 {
	int y;
	int z;
	public B08() {
		// 부모클래스 생성자 호출
		//super();	// default 생성자. JVM이 작성
		//super(100);
		System.out.println("나는야 B");
		y = 100;
		z = 30;
	}
	public void disp() {
		System.out.println("x = " + x);	// super.x이지만 B의 것이 되었으므로 this.x도 맞음
		System.out.println("y = " + y);	// this.y
		System.out.println("super.y = " + super.y);
		System.out.println("z = " + z);	// this.z만 됨. super.z하면 에러
	}
}

public class Test08 {

	public static void main(String[] args) {
		B08 bp = new B08();
		System.out.println();
		bp.disp();
	}

}
