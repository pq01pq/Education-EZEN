package test08;

public class Test08 {
	
	public static void main(String[] args) {
		Manage manage = new ManagePerson();
		management : while(true) {
			switch(manage.function()) {
			case 1 :	// 입력
				manage.input();
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
