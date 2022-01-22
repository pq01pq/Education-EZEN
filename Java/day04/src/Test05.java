import java.util.Scanner;

public class Test05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int kor;
		do {
			System.out.print("국어점수 : ");
			kor = scan.nextInt();
		} while(kor < 0 || kor > 100);
		
		System.out.println("입력한 국어점수 : " + kor);
		
		scan.close();
	}

}
