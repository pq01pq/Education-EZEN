import java.util.Scanner;

class Protoss {
	public void attack() {
		System.out.println("유닛이 공격함");
	}
	public void move() {
		System.out.println("유닛이 이동함");
	}
}

class Zealot extends Protoss {
	public void attack() {
		System.out.println("질럿이 공격함");
	}
	public void move() {
		System.out.println("질럿이 이동함");
	}
}

class Dragoon extends Protoss {
	public void attack() {
		System.out.println("드라군이 공격함");
	}
	public void move() {
		System.out.println("드라군이 이동함");
	}
}

public class Test11 {
	
	static Scanner scan;
	
	public static void main(String[] args) {
		Protoss pro[] = new Protoss[4];
		
		scan = new Scanner(System.in);
		
		for(int i = 0; i < pro.length; i++) {
			System.out.print("1.질럿 2.드라군 : ");
			int select1 = scan.nextInt();
			if(select1 == 1) {
				pro[i] = new Zealot();
			} else {
				pro[i] = new Dragoon();
			}
		}
		
		System.out.println("1.공격 2.이동 : ");
		int select2 = scan.nextInt();
		
		for(int i = 0; i < pro.length; i++) {
			if(select2 == 1) {
				pro[i].attack();
			} else {
				pro[i].move();
			}
		}
	}
}
