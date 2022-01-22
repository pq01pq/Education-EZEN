
class A05 {
	private int a;
	private static int b = 10;
	// static 구문
	static {
		System.out.println("ststic 구문 실행");
	}
	// 일반구문은 공통된 초기값을 줄 때 사용 ????
	{
		System.out.println("일반 구문 실행");
	}
	{
		System.out.println("일반 구문 실행");
	}
	public A05() {
		System.out.println("A05() 생성자 실행");
	}
	public A05(int a) {
		this.a = a;
		System.out.println("A05(int a) 생성자 실행");
	}
	public void aaa() {
		System.out.println(a);
	}
	public static void bbb(){
//		System.out.println(a);
		System.out.println(b);
	}
	// aaa()와 b는 메모리에 올라갔지만 a는 메모리에 올라가지 않았기 때문에 에러
	
	{
		System.out.println("일반 구문 실행");
	}
	{
		System.out.println("일반 구문 실행");
	}
	public void disp() {
		System.out.println("aaa");
	}
}

public class Test05 {

	public static void main(String[] args) {
		System.out.println("-----------------------------------");
		A05 ap = new A05();
		System.out.println("-----------------------------------");
		A05 bp = new A05(100);
		System.out.println("-----------------------------------");
		
		ap.disp();
		bp.disp();
	}

}
