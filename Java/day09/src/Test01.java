
class A01{
	// 1. 선언문이 제일먼저 읽힘
	final int a;	// final은 객체를 생성하면서 반드시 초기값 지정
	int b;
	
	// 2. 일반구문이 다음으로 읽힘 
	{
		a = 100;
	}
	
	// 3. 생성자가 나중에 읽힘
	public A01() {
		//a = 1000;
	}
	public A01(int a, int b) {
		//this.a = a;
		//this.b = b;
	}
}

public class Test01 {

	public static void main(String[] args) {
		A01 ap = new A01();
		System.out.println("a = " + ap.a);
		ap.b = 100;
		//ap.a = 100;
	}

}
