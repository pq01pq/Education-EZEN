import java.util.Scanner;

public class Test03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("국어 점수 : ");
		int kor = scan.nextInt();
		System.out.print("영어 점수 : ");
		int eng = scan.nextInt();
		double average = (kor + eng) / 2.0;
		
		System.out.println("점수 : " + average);
		
		String grade;
		switch((int)(average / 10)) {
		case 10:
			grade = "수";	break;
		case 9:
			grade = "수";	break;
		case 8:
			grade = "우";	break;
		case 7:
			grade = "미";	break;
		case 6:
			grade = "양";	break;
		default:
			grade = "가";
		}
		System.out.println("등급 : " + grade);
		
		scan.close();
	}

}
