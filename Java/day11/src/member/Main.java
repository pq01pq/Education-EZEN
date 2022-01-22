package member;

import java.util.Scanner;

import manage.Manage;

public class Main {
	
	static Scanner scan;
	
	public static void main(String[] args) {
		Manage manage = new ManageMember();
		scan = new Scanner(System.in);
		management : while(true) {
			System.out.print("1.입력 2.출력 3.삭제 4.수정 5.종료 : ");
			switch(scan.nextInt()) {
			case 1 :	// 입력
				manage.insert();
				break;
			case 2 :	// 출력
				manage.view();
				break;
			case 3 :	// 삭제
				manage.delete();
				break;
			case 4 :	// 수정
				manage.modify();
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
