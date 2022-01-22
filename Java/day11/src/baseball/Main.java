package baseball;

import java.util.Scanner;

import manage.Manage;

/**
야구선수 관리 프로그램
구단 : LG, SSG, KIA, 삼성, 롯데, NC, 한화, 두산, KT, 키움
1.입력 2.보기 3.트레이드 4.삭제 5.종료
입력 - 구단명을 입력받고 해당하는 선수의 이름, 연봉을 입력받아 저장
보기 - 구단명을 입력받고 해당하는 구단의 선수를 모두 출력
트레이드 - 구단과 이름을 입력받아 해당하는 선수가 있으면 이동할 구단을 입력받아
			이동할 구단으로 옮김
삭제 - 구단명과 이름을 입력받아 해당하는 선수를 삭제
종료 - 프로그램 종료
 */

public class Main {
	
	static Scanner scan;

	public static void main(String[] args) {
		Manage manage = new ManagePlayer();
		scan = new Scanner(System.in);
		
		management: while(true) {
			System.out.print("1.입력 2.보기 3.트레이드 4.삭제 5.종료 : ");
			switch(scan.nextInt()) {
			case 1:
				manage.insert();
				break;
			case 2:
				manage.view();
				break;
			case 3:
				manage.modify();
				break;
			case 4:
				manage.delete();
				break;
			case 5:
				System.out.println("프로그램 종료");
				break management;
			default:
				System.out.println("잘못 선택");
			}
		}
		
	}

}
