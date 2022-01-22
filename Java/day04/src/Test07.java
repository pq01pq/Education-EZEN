import java.util.Scanner;

public class Test07 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int count = 0;
		int start = 1, end = 100;
		game : while(start <= end) {
			int number = (int)((end - start + 1) * Math.random()) + start;
			count++;
			System.out.println("[컴퓨터] 정답이 " + number + "임?");
			System.out.print("[나] 1. up, 2. down, 3.yes : ");
			int myAnswer = scan.nextInt();
			
			switch(myAnswer) {
			case 1:
				start = number + 1;
				break;
			case 2:
				end = number - 1;
				break;
			case 3:
				System.out.println("[컴퓨터] 나이따~");
				System.out.println("[나] " + count + "번만에 맞췄네 ㅎ");
				break game;
			default:
				System.out.println("[컴퓨터] 똑바로 대답해 ㅡㅡ");
			}
		}
		
		if(start > end) {
			System.out.println("[컴퓨터] 아 똑바로 하라고 ㅡㅡ");
		}
		
		scan.close();
	}

}
