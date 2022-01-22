import java.util.Scanner;

public class Test03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int sum = 0, count = 0;
		double average;
		
		while(true) {
			System.out.print("수 입력 : ");
			int number = scan.nextInt();
			if(number < 1) {
				break;
			}
			sum += number;
			count++;
		}
		
		average = (double)sum / count;
		System.out.println("총합 : " + sum + ", 평균 : " + average);
		
		scan.close();
	}

}
