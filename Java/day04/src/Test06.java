import java.util.Scanner;

public class Test06 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int answer = (int)(100 * Math.random()) + 1;
		int count = 0;
		while(true) {
			System.out.print("수 입력 : ");
			int number = scan.nextInt();
			count++;
			
			if(number == answer) {
				System.out.println("정답");
				break;
			}
			
			if(number > answer) {
				System.out.println("down");
			} else {
				System.out.println("up");
			}
		}
		System.out.println(count + "번만에 맞춤");
		
		scan.close();
	}

}
