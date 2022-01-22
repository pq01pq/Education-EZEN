import java.util.InputMismatchException;
import java.util.Scanner;

public class Test05 {
	
	static Scanner scan;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		System.out.print("국어점수를 입럭: ");
		try {
			int kor = scan.nextInt();
			System.out.println("입력한 점수는 " + kor + "점");
			double total = kor / 0;
		} catch (InputMismatchException e) {
			System.err.println("숫자만 입력");
		} catch (ArithmeticException e) {
			System.err.println("0으로 나누지 마");
		}
		
	}

}
