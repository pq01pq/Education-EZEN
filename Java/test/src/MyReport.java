import java.util.Scanner;

/**
이름과 전화번호, 사는 곳의 시군구를 출력
 */
public class MyReport {

	static Scanner scan;
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		System.out.print("이름: ");
		String name = scan.next();
		System.out.print("전화번호: ");
		String phone = scan.next();
		System.out.print("시: ");
		String city = scan.next();
		System.out.print("군: ");
		String goon = scan.next();
		System.out.print("구: ");
		String gu = scan.next();
		
		System.out.println();
		
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phone);
		System.out.printf("주소 : %s시 %s군 %s구\n", city, goon, gu);
	}

}
