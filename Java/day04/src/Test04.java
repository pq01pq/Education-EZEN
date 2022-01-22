import java.util.Scanner;

public class Test04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int minute;
		while(true) {
			System.out.print("분 입력 : ");
			minute = scan.nextInt();
			if(minute == 0) {
				System.out.println("종료");
				break;
			}
			
			if(minute < 0) {
				System.out.println("음수는 안돼");
			} else {
				System.out.println(minute / 60 + "시간 " + minute % 60 + "분");
			}
		}
		
		scan.close();
	}

}
