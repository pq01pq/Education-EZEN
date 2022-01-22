
class A02 extends Thread {

	@Override
	public void run() {
		super.run();
		System.out.println("A02.currentThread = " + Thread.currentThread());
	}
	
}


public class Test02 {

	public static void main(String[] args) {
		System.out.println("main.currentThread = " + Thread.currentThread());
		A02 ap = new A02();
		ap.setName("A02 class");
		ap.setPriority(10);
		ap.start();
	}

}
