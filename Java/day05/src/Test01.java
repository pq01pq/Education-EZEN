import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("방 관리 프로그램");
		int[] occupied = new int[3];
		program : while(true) {
			System.out.print("1.입실 2.퇴실 3.보기 4.종료 : ");
			int index = scan.nextInt();
			
			int room;
			switch(index) {
			case 1 :
				System.out.print("입실할 방의 번호 : ");
				room = scan.nextInt();
				if(room <= 0 || room >= 4) {
					System.out.println("없는 방번호");
					break;
				}
				
				if(occupied[room - 1] == 0) {
					System.out.println(room + "호실 입실");
					occupied[room - 1]++;
				} else {
					System.out.println(room + "호실 사용중이므로 입실 불가");
				}
				break;
			case 2 :
				System.out.print("퇴실할 방의 번호 : ");
				room = scan.nextInt();
				if(room <= 0 || room >= 4) {
					System.out.println("없는 방번호");
					break;
				}
				
				if(occupied[room - 1] > 0) {
					System.out.println(room + "호실 퇴실");
					occupied[room - 1]--;
				} else {
					System.out.println(room + "호실은 빈방");
				}
				break;
			case 3 :
				for(int i = 0; i < occupied.length; i++) {
					if(occupied[i] == 0) {
						System.out.println((i + 1) + "호실 : 빈방");
					} else {
						System.out.println((i + 1) + "호실 : 사용중");
					}
				}
				break;
			case 4 :
				System.out.println("프로그램 종료");
				break program;
			default :
				System.out.println("잘못된 인덱스");
			}
		}
		
		scan.close();
	}

}
