import java.util.Scanner;

public class Test02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("인원수 : ");
		int number = scan.nextInt();
		
		String name[] = new String[number];
		
		for(int i = 0; i < number; i++) {
			System.out.print(i + 1 + "번째 사람 이름 : ");
			name[i] = scan.next();
		}
		
		for(int i = 0; i < number; i++) {
			System.out.println(name[i]);
		}
		
		scan.close();
	}

}
