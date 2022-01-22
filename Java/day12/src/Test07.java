import java.util.Scanner;
import java.util.StringTokenizer;

public class Test07 {

	public static void main(String[] args) {
		String input = "1 fish 2 fish red fish blue fish";
		
		// 자바에서 데이터를 분할하여 관리하는 클래스가 3개있음
		StringTokenizer st = new StringTokenizer(input, "fish");
		// 유산클래스 : 현재는 사용되지 않고 과거에 많이 사용한 클래스
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken().trim());
		}
		
		System.out.println("------------------");
		
		String data[] = input.split("fish");
		for(int i = 0; i < data.length; i++) {
			System.out.println(data[i].trim());
		}
		
		Scanner scan = new Scanner(input).useDelimiter("\\s*fish\\s*");
		// fish를 중심으로 공백이 앞에 0개이상, 뒤에 0개이상 올 수 있음
		System.out.println(scan.nextInt());
		System.out.println(scan.nextInt());
		System.out.println(scan.next());
		System.out.println(scan.next());
		scan.close();
	}

}
