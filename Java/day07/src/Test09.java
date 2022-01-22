
class A09 {
	// 멤버필드
	int a;
	int b;
	
	// 생성자
	int c, d, e, f, g;
	//A09() {}	// JVM이 default 생성자를 만들어줌
	
	A09(){
		a = 10;
		b = 20;
		c = 30;
		d = 40;
		e = 50;
		f = 60;
		g = 70;
	}
	
	A09(int b) {
		this();
		this.b = b;
	}
	
	A09(int a, int b) {	
		this(b);	// 생성자 호출. 무조건 생성자의 첫번째 문장
		this.a = a;
//		c = 30;
//		d = 40;
//		e = 50;
//		f = 60;
//		g = 70;
	}
	
	// 멤버 메서드
	void disp() {
		System.out.print("a = " + a + ", ");
		System.out.println("b = " + b);
	}
}

public class Test09 {
	
	public static void main(String[] args) {
		A09 a1 = new A09();
		a1.a = 10;
		a1.b = 10;
		a1.disp();
		
		A09 a2 = new A09(100, 100);
		a2.disp();
		a2.b = 200;
		a2.disp();
	}

}
