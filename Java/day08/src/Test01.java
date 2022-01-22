import java.util.Scanner;

class RoomManage {
	Scanner scan;
	int countRoom;
	boolean room[];
	RoomManage(int countRoom) {
		scan = new Scanner(System.in);
		this.countRoom = countRoom;
		room = new boolean[countRoom];
	}
	// 입실
	void input() {
		System.out.print("입실할 방의 번호 : ");
		int roomNum = scan.nextInt();
		if(room[roomNum - 1]) {
			System.out.println(roomNum + "호실은 사용중");
		} else {
			System.out.println(roomNum + "호실에 입실");
		}
	}
	
	// 퇴실
	void output() {
		System.out.print("퇴실할 방의 번호 : ");
		int roomNum = scan.nextInt();
		if(!room[roomNum - 1]) {
			System.out.println(roomNum + "호실은 빈방");
		} else {
			System.out.println(roomNum + "호실에 입실");
		}
	}
	
	// 방 보여주기
	void view() {
		for(int i = 0; i < countRoom; i++) {
			if(room[i]) {
				System.out.println((i + 1) + "호실 사용중");
			} else {
				System.out.println((i + 1) + "호실 사용 가능");
			}
		}
	}
	
	// 종료
	void exit() {
		System.out.println("프로그램 종료");
		System.exit(0);
	}
}

public class Test01 {
	
	static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.print("방의 갯수 : ");
		int countRoom = scan.nextInt();
		
		RoomManage room = new RoomManage(countRoom);
		
		while(true) {
			System.out.print("1.입실 2.퇴실 3.보기 4.종료 : ");
			int select = scan.nextInt();
			switch(select) {
			case 1 :
				room.input();
				break;
			case 2 :
				room.output();
				break;
			case 3 :
				room.view();
				break;
			case 4 :
				room.exit();
				break;
			default :
				System.out.println("잘못누름");
			}
		}
	}

}
