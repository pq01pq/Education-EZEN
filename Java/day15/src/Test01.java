
class A01 extends Thread {

	@Override
	public void run() {
		super.run();
		System.out.println("A01 클래스");
	}
}

class B01 implements Runnable {

	@Override
	public void run() {
		System.out.println("B01 클래스");
	}
	
}

public class Test01 {

	public static void main(String[] args) {
		System.out.println("main 스레드 실행");
		A01 ap = new A01();
		//ap.run();	// 단일스레드로 실행됨
		ap.start();
		// start() 메서드는 Thread에 있는 메서드로 실행시 스레드를 하나 만들어서
		// 그 스레드에 run() 메서드를 실행시켜주도록 만들어진 메서드
		
		B01 bp = new B01();
		Thread th = new Thread(bp);
		th.start();
		
		for(int i = 1; i <= 100; i++) {
			System.out.print(i + "\t");
			if(i % 10 == 0) {
				System.out.println();
			}
		}
		System.out.println("main 스레드 끝");
	}

}
