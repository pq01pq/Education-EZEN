import java.util.InputMismatchException;
import java.util.Scanner;

class NonScoreException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public String message;
	
	public NonScoreException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		this.message = super.getMessage();
		return this.message;
	}
	
}

public class Test08 {
	
	static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		//NonScoreException nonScore = new NonScoreException("점수 아님");
		while(true) {
			try {
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {}
				System.out.print("국어 점수: ");
				int kor = scan.nextInt();
				if(kor < 0 || kor > 100) {
					throw new NonScoreException("점수 아님");
				}
				System.out.println(kor + "점");
				break;
			} catch(InputMismatchException e) {
				System.err.println("숫자 아님");
			} catch(NonScoreException e) {
				System.err.println(e.getMessage());
			} finally {	// try 절이 끝나면 바로 불려가서 실행
				System.out.println("국어점수를 입력받아 출력하는 프로그램");
			}
		}
	}

}
