import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("국어 점수 : ");
		int kor = scan.nextInt();
		System.out.print("영어 점수 : ");
		int eng = scan.nextInt();
		double average = (kor + eng) / 2.0;
		
		System.out.println("점수 : " + average);
		if(average >= 60.0) {
			System.out.println("합격");
		} else {
			System.out.println("불합격");
		}
		
		scan.close();
	}

}
