import java.util.Scanner;

public class Test13 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String[] name = new String[5];
		int[] kor = new int[5];
		int[] eng = new int[5];
		int[] total = new int[5];
		int[] rank = new int[5];
		
		for(int i = 0; i < 5; i++) {
			System.out.print(i + 1 + "번째 이름 : ");
			name[i] = scan.next();
			System.out.print(i + 1 + "번째 국어점수 : ");
			kor[i] = scan.nextInt();
			System.out.print(i + 1 + "번째 영어점수 : ");
			eng[i] = scan.nextInt();
			total[i] = kor[i] + eng[i];
		}
		
		for(int i = 0; i < 5; i++) {
			rank[i] = 1;
			for(int j = 0; j < 5; j++) {
				if(total[i] < total[j]) {
					rank[i]++;
				}
			}
		}
		
		for(int i = 0; i < 5; i++) {
			System.out.printf("%s님의 총점은 %d점이고, 순위는 %d등 입니다.\n", name[i], total[i], rank[i]);
		}
		
		scan.close();
	}

}
