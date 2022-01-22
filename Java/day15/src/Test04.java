import java.util.Date;
import java.util.Scanner;

class TimeCounter04 extends Thread {
	
	public Date date;
	public boolean running;
	int seconds;
	
	public TimeCounter04() {
		// TODO Auto-generated constructor stub
		date = new Date();
		running = false;
		seconds = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		running = true;
		while(running) {
			try {
				Thread.sleep(1000);
				seconds++;
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getSeconds() {
		return seconds;
	}
	
}

public class Test04 {
	
	static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		TimeCounter04 time = new TimeCounter04();
		time.setDaemon(true);
		time.start();
		
		int answer = (int)(Math.random() * 100) + 1;
		while(time.running) {
			System.out.print("수 입력: ");
			int number = scan.nextInt();
			
			if(number > answer) {
				System.out.println("down");
			} else if(number < answer) {
				System.out.println("up");
			} else {
				time.running = false;
				System.out.println(time.getSeconds() + "초 걸림");
			}
		}
	}

}
