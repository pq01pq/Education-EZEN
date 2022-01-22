

interface I02 {
	public static final int A = 10;
	public int b = 20;	// JVM이 알아서 final 같다 붙임
	public final int C = 30;
	public int D = 40;
	int E = 50;
//	private int F = 60;
	
	class InnerA{}	// JVM이 알아서 static 갖다 붙임
	
	public abstract void disp1();
//	public abstract void disp2() {
//		System.out.println("aaa");
//	}
	void disp3();
}

public class Test02 {

	public static void main(String[] args) {
		System.out.println(I02.A);
		System.out.println(I02.b);
//		I02.b = 100;
		System.out.println(I02.C);
		
		I02.InnerA ia = new I02.InnerA();
		System.out.println(ia.getClass());
	}

}
