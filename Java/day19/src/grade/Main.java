package grade;

import java.util.Scanner;

public class Main {
	
	static Scanner scan;

	public static void main(String[] args) {
		Manage manage = new ManageGrade();
		scan = new Scanner(System.in);
		
		management: while(true) {
			System.out.print("1.입력 2.출력 3.삭제 4.수정\n5.저장 6.꺼내기 7.종료 : ");
			switch(scan.nextInt()) {
			case 1:
				manage.insert();
				break;
			case 2: 
				manage.view();
				break;
			case 3:
				manage.delete();
				break;
			case 4:
				manage.modify();
				break;
			case 5:
				manage.save();	// 파일에 학생들의 이름, 국어, 영어점수 저장
				break;
			case 6:
				manage.load();	// 파일에 있는 자료로 초기화
				break;
			case 7:
				System.out.println("프로그램 종료");
				break management;
			default:
				System.out.println("잘못 선택");
			}
		}
	}

}
