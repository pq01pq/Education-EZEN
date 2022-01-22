package test02;

import java.util.Scanner;

public class Ex03 {
	
	public static final boolean DEBUG = true;
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		ManagePerson manage = new ManagePerson();
		management : while(true) {
			System.out.print("1.입력 2.출력 3.삭제 4.수정 5.종료 : ");
			switch(scan.nextInt()) {
			case 1 :	// 입력
				manage.inputPerson();
				break;
			case 2 :	// 출력
				manage.viewPerson();
				break;
			case 3 :	// 삭제
				manage.delPerson();
				break;
			case 4 :	// 수정
				manage.modPerson();
				break;
			case 5 :	// 종료
				System.out.println("프로그램 종료");
				break management;
			default :
				System.out.println("잘못 입력");
			}
		}
	}

}
