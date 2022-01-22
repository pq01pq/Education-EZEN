import java.text.SimpleDateFormat;
import java.util.Date;

class A03 extends Thread {
	
	Date date;
	SimpleDateFormat sdf;
	
	public A03() {
		sdf = new SimpleDateFormat("HH:mm:ss");
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			date = new Date();
			System.out.println("현재시간 : " + sdf.format(date));
			
			// run() 메서드에 throws 선언을 하면 오버라이드가 안됨
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class Test03 {

	public static void main(String[] args) {
		A03 ap = new A03();
		ap.setDaemon(true);	// main 종료시 멀티스레드도 같이 종료
		ap.start();
		try {
			Thread.sleep(5000);	// main을 5초간 쉼
			ap.join(5000);	// ap에게 5초 권한을 줌
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main 끝");
	}

}
